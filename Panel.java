import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;

public class Panel
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("game of life");
        frame.setSize(1500,1200);
        frame.setLocation(200,200);
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new panel3());
        frame.setVisible(true);

        
    }
    
}



