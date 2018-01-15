
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

import  sun.audio.*;    //import the sun.audio package
import  java.io.*;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;








public class buttonPanel extends JPanel {
	private JTextField textFieldHour,textFieldMinute,textFieldSecond,messageField;
	private JButton buttonCalendar, buttonSetTime,buttonSay,buttonSetAlarm;
	private JLabel labelAlarm;
	private JRadioButton radioFast,radioBack,radioStop,radioReStart,radioForward;
	static boolean  alarmOn=false;
	static boolean  sayOn=false;
	static boolean  alarmStopperOn=false;
	static int aHour,aMinute;
	private StillClock clock;
	  String aMessage="ALARM! ";
	  String customMessage1="ALARM! ";
	  String customMessage2="GET UP DUDE! ";
	  String privateMessage="";
	 String nullMessage="";
	private String printMessage="";
	private String timeSay="";


	public buttonPanel(StillClock c)
	{
		setSize(50,50);

		textFieldHour = new JTextField(2);
		textFieldMinute = new JTextField(2);
		textFieldSecond = new JTextField(2);
		clock=c;





		buttonCalendar = new JButton("Calendar Time");
		buttonSetTime = new JButton("Set Time");
		buttonSay = new JButton("Say Time");
		buttonSetAlarm = new JButton("Set Alarm");
		
		messageField = new JTextField(13);
		add(messageField);

		radioFast = new JRadioButton("Speed Up", true);
		radioForward = new JRadioButton("Forward", true);
		radioBack = new JRadioButton("Backwards", true);
		radioStop = new JRadioButton("Stop", true);
		radioReStart = new JRadioButton("Restart", true);

		add(textFieldHour);
		add(textFieldMinute);
		add(textFieldSecond);

		add(buttonCalendar);
		add(buttonSetTime);
		add(buttonSay);
		add(buttonSetAlarm);

		add(radioFast);
		add(radioBack);
		add(radioForward);
		add(radioStop);
		add(radioReStart);


		
		CButtonActionListener Clistener = new CButtonActionListener(radioFast,radioBack,radioForward,radioStop,radioReStart,clock);
		buttonCalendar.addActionListener(Clistener);


		SetButtonActionListener Slistener = new SetButtonActionListener(textFieldHour,textFieldMinute,textFieldSecond,clock);
		buttonSetTime.addActionListener(Slistener);

		SayButtonActionListener Saylistener = new SayButtonActionListener(this);
		buttonSay.addActionListener(Saylistener);

		AlarmButtonActionListener Alistener = new AlarmButtonActionListener(textFieldHour,textFieldMinute,textFieldSecond,messageField,this);
		buttonSetAlarm.addActionListener(Alistener);

		SpeedButtonActionListener Speedlistener = new SpeedButtonActionListener(clock);
		radioFast.addActionListener(Speedlistener);

		BackButtonActionListener Backlistener = new BackButtonActionListener(radioForward,clock);
		radioBack.addActionListener(Backlistener);

		ForwardButtonActionListener Forwardlistener = new ForwardButtonActionListener(radioBack,clock);
		radioForward.addActionListener(Forwardlistener);

		StopButtonActionListener Stoplistener = new StopButtonActionListener(radioReStart,clock);
		radioStop.addActionListener(Stoplistener);

		ResButtonActionListener Reslistener = new ResButtonActionListener(radioStop,clock);
		radioReStart.addActionListener(Reslistener);


		ButtonGroup group1 = new ButtonGroup();
		group1.add(buttonCalendar);
		group1.add(buttonSetTime);
		group1.add(buttonSay);
		group1.add(buttonSetAlarm);

		radioFast.setSelected(false);
		radioBack.setSelected(false);
		radioForward.setSelected(false);
		radioStop.setSelected(false);
		radioReStart.setSelected(false);

		Timer alarmTimer=new Timer(1,new AlarmListener());
		alarmTimer.start();


		Timer stopTimer=new Timer(1,new AlarmStopListener());
		stopTimer.setDelay(20000);
		stopTimer.start();

	}


	public class AlarmStopListener  implements ActionListener 
	{

		@Override
		public void actionPerformed (ActionEvent e)  {

			if(alarmStopperOn)
			{




				printMessage=nullMessage;
				alarmOn=false;
				repaint();





				alarmStopperOn=false;
			}


		}

	}


	public class AlarmListener  implements ActionListener 
	{

		@Override
		public void actionPerformed (ActionEvent e)  {

			if(alarmOn && aHour==clock.getHour() && aMinute==clock.getMinute())
			{
				printMessage=aMessage;
				repaint();
				alarmStopperOn=true;


			}


		}

	}



	/*	public static void main(String[] args) throws Exception {
		JFrame app = new JFrame();
		app.setLayout(new FlowLayout());
		app.add(new buttonPanel());

		app.setSize(200, 200);
		app.setVisible(true);
	}*/

	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);


		boolean AM,past,oclock=false;
		boolean midnight=false,noon=false;
		int hourSay,minuteSay;
		String mString,mHour;
		int mDigits;




		if(alarmOn){
			//	FontMetrics fm=g.getFontMetrics();

			int xCoordinate=(int)(getWidth()*0.3);
			int yCoordinate=(int)(getHeight()*0.9);

			g.drawString(printMessage, xCoordinate, yCoordinate);

		}

		if(sayOn)
		{
			timeSay="It is ";
			hourSay=this.clock.getHour();
			minuteSay=this.clock.getMinute();

			if(minuteSay>30)
			{
				past=false;
				minuteSay=60-minuteSay;
			}else
			{	past=true;
			}



			if(minuteSay<20)
			{
				switch(minuteSay){
				case 0: oclock=true; break;
				case 1: timeSay+="one ";break;
				case 2: timeSay+="two ";break;
				case 3: timeSay+="three ";	break;
				case 4: timeSay+="four ";break;
				case 5: timeSay+="five ";break;
				case 6: timeSay+="six ";break;
				case 7: timeSay+="seven ";break;
				case 8: timeSay+="eight ";break;
				case 9: timeSay+="nine ";	break;
				case 10: timeSay+="ten ";break;
				case 11: timeSay+="eleven ";break;
				case 12: timeSay+="twelve ";break;
				case 13: timeSay+="thirteen ";break;
				case 14: timeSay+="fourteen ";break;
				case 15: timeSay+="quarter ";break;
				case 16: timeSay+="sixteen ";break;
				case 17: timeSay+="seventeen ";break;
				case 18: timeSay+="eighteen ";break;
				case 19: timeSay+="nineteen ";	break;
				}

			}	else if(minuteSay<30)
			{
				timeSay+="twenty ";
				int remainder=minuteSay-20;
				switch(remainder){
				case 0:  break;
				case 1: timeSay+="one ";break;
				case 2: timeSay+="two ";break;
				case 3: timeSay+="three ";	break;
				case 4: timeSay+="four ";break;
				case 5: timeSay+="five ";break;
				case 6: timeSay+="six ";break;
				case 7: timeSay+="seven ";break;
				case 8: timeSay+="eight ";break;
				case 9: timeSay+="nine ";	break;
				}


			}else{timeSay+="half ";}


			if(!oclock)
			{ if(past)
				timeSay+="past ";
			else
				timeSay+="to ";
			}

			if(!past)
			{	++hourSay;}


			if(hourSay>12)
			{
				AM=false;
				hourSay=-12+hourSay;
			}else
			{	AM=true;
			}



			switch(hourSay){
			case 0: if(oclock)
			{
				midnight=true;
				timeSay+="midnight ";
			}else{timeSay+="midnight ";
			midnight=true;
			}

			break;
			case 1: timeSay+="one ";break;
			case 2: timeSay+="two ";break;
			case 3: timeSay+="three ";	break;
			case 4: timeSay+="four ";break;
			case 5: timeSay+="five ";break;
			case 6: timeSay+="six ";break;
			case 7: timeSay+="seven ";break;
			case 8: timeSay+="eight ";break;
			case 9: timeSay+="nine ";	break;
			case 10: timeSay+="ten ";break;
			case 11: timeSay+="eleven ";break;
			case 12: 
				if(AM)
				{if(oclock)
				{
					noon=true;
					timeSay+="noon ";

				}else{timeSay+="noon "; noon=true;}}
				else if(!AM)
				{
					midnight=true;
					timeSay+="midnight ";
				}
				break;


			}

			if(!noon && !midnight)
			{
				if(oclock)
				timeSay+="o'clock ";
				
				if(AM && !noon && !midnight)
					timeSay+="AM ";
				else if( !noon && !midnight)
					timeSay+="PM ";


			}

			timeSay+=".";

			int xCoor=(int)(5);
			int yCoor=(int)(getHeight()*0.7);
			g.drawString(timeSay, xCoor, yCoor);

		}



	}

}

class CButtonActionListener implements ActionListener {

	JRadioButton radioSpeed,radioRestart;
	JRadioButton radioBackwards,radioForward,radioStop;
	StillClock clock;
	public CButtonActionListener(JRadioButton rSpeed, JRadioButton rBack, JRadioButton rForward, JRadioButton rStop, JRadioButton rRestart,StillClock c) {

		this.clock=c;
		radioSpeed=rSpeed;
		radioBackwards=rBack;
		radioForward=rForward;
		radioStop=rStop;
		radioRestart=rRestart;
	}

	public void actionPerformed(ActionEvent e) {

		clock.setCalendarClock();
		clock.setRun();
		clock.setForward();
		clock.setNormalPace();

		radioSpeed.setSelected(false);
		radioBackwards.setSelected(false);
		radioForward.setSelected(false);
		radioStop.setSelected(false);
		radioRestart.setSelected(false);



	}



}

class SetButtonActionListener implements ActionListener {

	JTextField hour,minute,second;
	StillClock clock;
	int hNum,mNum,sNum;
	

	public SetButtonActionListener(JTextField h, JTextField m, JTextField s,StillClock c) {

		this.clock=c;
		hour=h;
		minute=m;
		second=s;




	}

	public void actionPerformed(ActionEvent e) {
		boolean error=false;
		boolean firstError=false;
		try {

			hNum=Integer.parseInt(hour.getText());
			mNum=Integer.parseInt(minute.getText());
			sNum=Integer.parseInt(second.getText());
		}catch (Exception ee) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Enter Something Meaningful", "Alert", JOptionPane.ERROR_MESSAGE);
			error=true;
			firstError=true;
		}

		if(hNum<24 &&0-1<hNum &&mNum<60 &&-1<mNum &&sNum<60 &&-1<sNum && !error)
		{
			clock.setCurrentTime(hNum,mNum,sNum);
			clock.setRunClock();
		}else
		{	if(!firstError)
			JOptionPane.showMessageDialog(null, "Enter Something Meaningful", "Alert", JOptionPane.ERROR_MESSAGE);
		}


		hour.setText("");
		minute.setText("");
		second.setText("");




	}



}


class SayButtonActionListener implements ActionListener {

	buttonPanel thePanel;


	public SayButtonActionListener(buttonPanel p) {

		thePanel=p;

	}

	public void actionPerformed(ActionEvent e) {


		buttonPanel.sayOn=true;
		thePanel.repaint();
		//System.out.println("Say Time  ");




	}



}

class AlarmButtonActionListener implements ActionListener {

	JTextField hour,minute,second,message;
	int hNum,mNum;
	
	buttonPanel thePanel;
	
	public AlarmButtonActionListener(JTextField h, JTextField m, JTextField s, JTextField mes,buttonPanel p) {


		hour=h;
		minute=m;
		second=s;
		message=mes;
		thePanel=p;
	}

	public void actionPerformed(ActionEvent e) {
		boolean error=false;

		try {
			hNum=Integer.parseInt(hour.getText());
			mNum=Integer.parseInt(minute.getText());
		}catch (Exception ee) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Enter Something Meaningful", "Alert", JOptionPane.ERROR_MESSAGE);
			error=true;
		}

		if(hNum<24 &&-1<hNum &&mNum<60 &&-1<mNum  && !error)
		{
			buttonPanel.aHour=hNum;
			buttonPanel.aMinute=mNum;
			buttonPanel.alarmOn=true;
			
		

		}
		
	/*	else if(error)
		{ 
			JOptionPane.showMessageDialog(null, "Enter Something Meaningful", "Alert", JOptionPane.ERROR_MESSAGE);
		} */
		
		if(buttonPanel.alarmOn && !error)
		{
		if(message.getText().equals(""))
		{
			thePanel.aMessage=thePanel.customMessage1+thePanel.customMessage2;
			thePanel.repaint();
		}else
		{
			thePanel.aMessage=thePanel.customMessage1+message.getText();
			thePanel.repaint();
		}
		
		}
		
		hour.setText("");
		minute.setText("");
		second.setText("");


		


		
		message.setText("");
	}



}


class SpeedButtonActionListener implements ActionListener {

	JRadioButton radioFast;
	StillClock clock;

	public SpeedButtonActionListener(StillClock c) {

		this.clock=c;

	}

	public void actionPerformed(ActionEvent e) {
		radioFast=(JRadioButton)e.getSource();

		if(radioFast.isSelected())
		{	
			clock.setFastPace();
		}
		else
		{

			clock.setNormalPace();
		}
	}
}

class BackButtonActionListener implements ActionListener {

	JRadioButton radioBackwards,radioForward;
	StillClock clock;

	public BackButtonActionListener(JRadioButton rF,StillClock c) {

		radioForward=rF;
		this.clock=c;
	}

	public void actionPerformed(ActionEvent e) {
		radioBackwards=(JRadioButton)e.getSource();

		if(radioBackwards.isSelected())
		{	
			radioForward.setSelected(false);
			clock.setBack();
		} else
		{

			clock.setForward();
		}

	}
}


class ForwardButtonActionListener implements ActionListener {

	JRadioButton radioBackwards,radioForward;
	StillClock clock;

	public ForwardButtonActionListener(JRadioButton rB,StillClock c) {

		radioBackwards=rB;
		this.clock=c;
	}

	public void actionPerformed(ActionEvent e) {
		radioForward=(JRadioButton)e.getSource();

		if(radioForward.isSelected())
		{	
			clock.setForward();
			radioBackwards.setSelected(false);

		} else
		{
			clock.setBack();
		}

	}
}


class StopButtonActionListener implements ActionListener {

	JRadioButton radioStop,radioReStart;
	StillClock clock;

	public StopButtonActionListener(JRadioButton rReS,StillClock c) {

		radioReStart=rReS;
		this.clock=c;
	}

	public void actionPerformed(ActionEvent e) {
		radioStop=(JRadioButton)e.getSource();

		if(radioStop.isSelected())
		{	
			clock.setStop();
			radioReStart.setSelected(false);
		} else
		{
			clock.setRun();
		}

	}
}

class ResButtonActionListener implements ActionListener {

	JRadioButton radioStop,radioReStart;
	StillClock clock;

	public ResButtonActionListener(JRadioButton rStop,StillClock c) {

		radioStop=rStop;
		this.clock=c;
	}

	public void actionPerformed(ActionEvent e) {
		radioReStart=(JRadioButton)e.getSource();

		if(radioReStart.isSelected())
		{	
			clock.setRun();
			radioStop.setSelected(false);
		}else
		{
			clock.setStop();
		}

	}
}

