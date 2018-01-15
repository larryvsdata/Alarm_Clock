import java.awt.Color;

import javafx.beans.value.ObservableValue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RedListener implements ChangeListener{
    private StillClock panel;
    
    public RedListener(StillClock panel) {
        this.panel = panel;
    }
    
    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider)e.getSource();
        int k=slider.getValue();
        
        Color currentColor=panel.clockColor;
      //  System.out.println(currentColor.getBlue());
        Color myColor=new Color(k,currentColor.getGreen(),currentColor.getBlue());
        panel.clockColor=myColor;
        panel.repaint();
    }

}
