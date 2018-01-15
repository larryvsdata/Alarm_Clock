import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class StillClock extends JPanel{

	private int hour;
	private int minute;
	private int second;
	int interv=1000;
	Timer timer;
	public boolean calendarClock=false;
	public boolean goForward=true;
	public boolean run=true;
	private JSlider sliderRed,sliderGreen,sliderBlue;
	public Color clockColor= Color.BLACK;
	
	
	
	public StillClock() throws InterruptedException
	{
		setSize(200,200);
		setCurrentTime();
		timer=new Timer(getInterval(),new TimerListener());
		this.timer.start();
		
		
		sliderRed=new JSlider();
		
		sliderRed.setMaximum(255);
		sliderRed.setMinimum(0);
		sliderRed.setValue(0);
		
		add(sliderRed,BorderLayout.NORTH);
		
		sliderGreen=new JSlider();
		
		sliderGreen.setMaximum(255);
		sliderGreen.setMinimum(0);
		sliderGreen.setValue(0);
		
		add(sliderGreen,BorderLayout.NORTH);
		
		sliderBlue=new JSlider();
		
		sliderBlue.setMaximum(255);
		sliderBlue.setMinimum(0);
		sliderBlue.setValue(0);
		
		add(sliderBlue,BorderLayout.NORTH);
		
		
		
		sliderRed.addChangeListener(new RedListener(this));
		sliderBlue.addChangeListener(new BlueListener(this));
		sliderGreen.addChangeListener(new GreenListener(this));
		
	}

	public StillClock(int hour,int minute,int second)
	{
		this.hour=hour;
		this.minute=minute;
		this.second=second;
		this.timer=new Timer(getInterval(),new TimerListener());
		this.timer.start();

	}


	public class TimerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(getRun())
				if(getCalendarClockStatus())
				{setCurrentTime(); repaint();

				}else
				{

					if(getForward())
					{
						try {
							runTheClock(); 
							repaint();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						try {
							runTheClockback();
							repaint();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}


				}
		}

	}


	public int getHour()
	{
		return hour;
	}

	public void setHour(int hour)
	{
		this.hour=hour;
		repaint();
	}

	public int getMinute()
	{
		return minute;
	}

	public void setMinute(int minute)
	{
		this.minute=minute;
		repaint();
	}

	public int getSecond()
	{
		return second;
	}

	public void setSecond(int second)
	{
		this.second=second;
		repaint();
	}


	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		double radiusRatio=0.4;
		double squareEdgeRatio=0.5;


		int clockRadius=(int)(Math.min(getWidth(), getHeight()*radiusRatio));
		int xCenter=getWidth()/2;
		int yCenter=getHeight()/2;
		int roundEdge= (int )((1+squareEdgeRatio)*clockRadius);
		int frinding=(int )(squareEdgeRatio*clockRadius);


		g.setColor(clockColor);
		g.fillRoundRect(xCenter-roundEdge, yCenter-roundEdge, 2*roundEdge,2* roundEdge, frinding, frinding);
		g.setColor(Color.white);
		g.fillOval(xCenter-clockRadius, yCenter-clockRadius, 2*clockRadius, 2*clockRadius);
		/*g.drawString("12", xCenter-5, yCenter-clockRadius+12);

		g.drawString("9", xCenter-clockRadius+3, yCenter+5);
		g.drawString("3", xCenter+clockRadius-10, yCenter+3);
		g.drawString("6", xCenter-3, yCenter+clockRadius-3); */

		//Graphics2D g2d = (Graphics2D) g.create();



		int sLength=(int)(clockRadius*0.8);
		int xSecond=(int)(xCenter+sLength*Math.sin(second*(2*Math.PI/60)));
		int ySecond=(int)(yCenter-sLength*Math.cos(second*(2*Math.PI/60)));

		//g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

		g.setColor(Color.red);
		g.drawLine(xCenter, yCenter,xSecond, ySecond);

		int mLength=(int)(clockRadius*0.65);
		int xMinute=(int) (xCenter+mLength*Math.sin(minute*(2*Math.PI/60)));
		int yMinute=(int) (yCenter-mLength*Math.cos(minute*(2*Math.PI/60)));

		g.setColor(Color.blue);
		g.drawLine(xCenter, yCenter,xMinute, yMinute);

		int hLength=(int)(clockRadius*0.5);
		int xHour=(int) (xCenter+hLength*Math.sin((hour%12+minute/60.0)*(2*Math.PI/12)));
		int yHour=(int) (yCenter-hLength*Math.cos((hour%12+minute/60.0)*(2*Math.PI/12)));

		g.setColor(Color.green);
		g.drawLine(xCenter, yCenter,xHour, yHour);
	}

	public void setCurrentTime()
	{
		Calendar calender=new GregorianCalendar();
		this.hour=calender.get(Calendar.HOUR_OF_DAY);
		this.minute=calender.get(Calendar.MINUTE);
		this.second=calender.get(Calendar.SECOND);


	}

	public void setCurrentTime(int hr,int min,int sec)
	{
		
		if(hr<24 &&-1<hr)
			if(min<60 && -1<min)
				if(sec<60 && -1<sec)
				{
					this.hour=hr;
					this.minute=min;
					this.second=sec;
				}
		
	}
	
	

	public void runTheClock() throws InterruptedException
	{


		if(this.second<59)
		{	//try { Thread.sleep(interv); }
			//catch (InterruptedException e) {}
			++this.second; 
			//repaint();
			//System.out.println(second);
		}else{

			this.second=0;
			//repaint();
			if(this.minute<59)
			{	++this.minute; //repaint();
			//System.out.println(minute);
			}
			else
			{
				this.setMinute(0);
				this.setHour((this.hour+1)%24);
				//repaint();

			}


		}

	}

	public void runTheClockback() throws InterruptedException
	{


		if(this.second>0)
		{	//try { Thread.sleep(interv); }
			//catch (InterruptedException e) {}
			--this.second; 
			//repaint();
			//System.out.println(second);
		}else{

			this.second=59;
			//repaint();
			if(this.minute>0)
			{	--this.minute; //repaint();
			//System.out.println(minute);
			}
			else
			{
				this.minute=59;
				this.hour=((this.hour+23)%24);
				//repaint();

			}


		}

	}

	public void setCalendarClock()
	{
		this.calendarClock=true;
	}

	public void setRunClock()
	{
		this.calendarClock=false;
	}

	public boolean getCalendarClockStatus()
	{
		return this.calendarClock;
	}

	public void setForward()
	{
		this.goForward=true;
	}

	public boolean getForward()
	{
		return goForward;
	}

	public void setBack()
	{
		this.goForward=false;
	}


	public void setRun()
	{
		this.run=true;
	}

	public boolean getRun()
	{
		return this.run;
	}

	public void setStop()
	{
		this.run=false;
	}

	public void setInterval(int inter)
	{
		this.interv=inter;
	}
	
	public int getInterval()
	{
		return this.interv;
	}
	
	public void setNormalPace()
	{this.interv=1000;
		this.timer.setDelay(this.interv);
		
		
	
	}
	
	public void setFastPace()
	{
		
		this.interv=10;
		this.timer.setDelay(this.interv);
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(300,300);
	}


}
