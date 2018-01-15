import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.Timer;




public class ClockPanel extends MessagePanel{
	
	private StillClock clock;
	
	
	public ClockPanel(StillClock cc){
		clock=cc;
		setMessage();

		Timer timer=new Timer(1,new TimerListener());
		timer.start();
		
		setSize(300,100);
		
	}

	
	public void setMessage()
	{
		String hLabel="";
		String mLabel="";
		String sLabel="";
		
		if(clock.getHour()/10==0)
			hLabel+=""+"0"+clock.getHour();
		else
			hLabel+=clock.getHour();
		
		
		if(clock.getMinute()/10==0)
			mLabel+=""+"0"+clock.getMinute();
		else
			mLabel+=clock.getMinute();
		
		
		if(clock.getSecond()/10==0)
			sLabel+=""+"0"+clock.getSecond();
		else
			sLabel+=clock.getSecond();
		
		 
		
		this.message=""+hLabel+":"+mLabel+":"+sLabel;
	}
	
	
	public class TimerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			setMessage(); repaint();
			
		}
		
	}
	public Dimension getPreferredSize()
	{
		return new Dimension(30,30);
	}
	
}
