import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;

public class panel3  extends JPanel
{
    public panel3()
    {
        Panel2 game = new Panel2();
        setLayout(new FlowLayout());
        JButton button = new JButton("start / stop");
        button.addActionListener(new Listener(game));
        add(button);
        JButton gridButton = new JButton("Grid Toggle");
        //Panel2 game = new Panel2();
        gridButton.addActionListener(new GridListener(game));
        add(gridButton);
        JButton resetButton = new JButton("reset");
        resetButton.addActionListener(new resetListener(game));
        add(resetButton);
        game.setPreferredSize(new Dimension(1000,1000));
        add(game);
    }
    private class Listener implements ActionListener
    {
        Panel2 game;
        public Listener(Panel2 panel)
        {
            game = panel;
        }
        public void actionPerformed(ActionEvent e)
        {
            game.startStop();
        }
    }
    private class GridListener implements ActionListener
    {
        Panel2 game;
        public GridListener(Panel2 panel)
        {
           game = panel;
        }
        public void actionPerformed(ActionEvent e)
        {
            game.gridToggle();
        }
    }
    private class resetListener implements ActionListener
    {
        Panel2 game;
        public resetListener(Panel2 panel)
        {
            game = panel;
        }
        public void actionPerformed(ActionEvent e)
        {
            game.reset();
        }
    }
}
