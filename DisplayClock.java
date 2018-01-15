import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DisplayClock extends JFrame{
	
	private StillClock clock;
	
	
	public DisplayClock() throws InterruptedException{
		clock=new StillClock();
		
		clock.setFont(new Font("Courie",Font.BOLD,14));
	
		setSize(800,800);
		ClockPanel CP=new ClockPanel(clock);
		buttonPanel BP=new buttonPanel(clock);
		
		clock.setForeground(Color.blue);
		CP.setCentered(true);
		CP.setForeground(Color.blue);
		CP.setFont(new Font("Courie",Font.BOLD,75));
		
		BP.setFont(new Font("Courie",Font.BOLD,30));
		
		
		setLayout(new GridLayout(2,2));
		
		add(clock);
		add(CP,BorderLayout.SOUTH);
		add(BP);
		

	
		
		
	}

	public	StillClock  returnClock()
	{
		return clock;
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		DisplayClock frame=new DisplayClock();
		frame.setTitle("ERMAN JAVA PROJECT");
		frame.setSize(600,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		StillClock clock=frame.returnClock();
		
		clock.setCalendarClock();
		
	//	System.out.println(Integer.parseInt("123")+22);

	/*	clock.setCalendarClock();
		Thread.sleep(3000);
		
		clock.setCurrentTime(1,1,1);
		
		clock.setRunClock();

		/*	clock.setStop();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clock.setRun();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clock.setCurrentTime(1,1,1);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clock.setForward();  */
		
	/*	clock.setNormalPace();
		clock.setRun();
		
		clock.setCurrentTime(11,12,12);
		Thread.sleep(3000);
	
		
		clock.setNormalPace();
		clock.setRun();
		Thread.sleep(3000);
		clock.setFastPace();
		Thread.sleep(3000);
		clock.setNormalPace();
		Thread.sleep(3000);
		clock.setStop();
		Thread.sleep(3000);
		clock.setRun();
		Thread.sleep(3000);
		clock.setCalendarClock();
		Thread.sleep(3000);
		clock.setRunClock();
		clock.setCurrentTime(23,12,12);
		Thread.sleep(3000);
		
		
		*/
		
		
		
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(500,500);
	}
	
	
}



