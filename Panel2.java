import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import java.awt.image.*;
import java.awt.event.*;

public class Panel2 extends JPanel implements MouseMotionListener
{
    private BufferedImage myImage;
    private Graphics myBuffer;
    public int[][] grid = new int[100][100];
    public int[][] grid2 = new int[100][100];
    public int[][] cleared = new int[100][100];
    public int mouseX = 0;
    public int mouseY = 0;
    public boolean gridT = true;
    public boolean active = false;
    public int pops = 0;
    private Timer timer;
    
    public Panel2()
    {
        //setPreferredSize(new Dimension(1000,1000));
        addMouseMotionListener(this);
        addMouseListener(new mouse());
        myImage = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        timer = new Timer(100, new Listener());
        //myBuffer.setColor(Color.BLACK);
        //myBuffer.fillRect(0,0,1000,1000);
        //repaint();
        //myBuffer.setColor(Color.GRAY);
        //for(int i = 10; i < 1000; i = i + 10)
       // {
           // myBuffer.drawLine(i,0,i,1000);
           // myBuffer.drawLine(0,i,1000,i);
        //}
        //repaint();
        draw(gridT);
    }

    public void paintComponent(Graphics g)
   {
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }

   public void addCell()
   {
        if(grid[(int)Math.ceil(mouseX / 10)][(int)Math.ceil(mouseY / 10)] == 0)
            grid[(int)Math.ceil(mouseX / 10)][(int)Math.ceil(mouseY / 10)] = 1;
        else
            grid[(int)Math.ceil(mouseX / 10)][(int)Math.ceil(mouseY / 10)] = 0;
        draw(gridT);
   }

   public void draw(boolean GridT)
   {
    myBuffer.setColor(Color.BLACK);
        myBuffer.fillRect(0,0,1000,1000);
        repaint();
        if(GridT)
        {
            myBuffer.setColor(Color.GRAY);
            for(int i = 10; i < 1000; i = i + 10)
            {
                myBuffer.drawLine(i,0,i,1000);
                myBuffer.drawLine(0,i,1000,i);
            }
        }
    myBuffer.setColor(Color.BLUE);    
    for(int k = 0; k < grid.length; k++)
        {
            for(int l = 0; l < grid[k].length; l++)
            {
                if(grid[k][l] == 1)
                {
                    myBuffer.fillRect((int)Math.ceil(k * 10),(int)Math.ceil(l * 10),10,10);
                    //System.out.println("" + (int)(Math.ceil(k * 10)) +  "| |" +(int)(Math.ceil(l * 10)) + "| |" + mouseX + "||" + mouseY);
                }
            }
        }
        //myBuffer.fillOval(mouseX,mouseY,10,10);
        repaint();
   }

   public void startStop()
   {
        if(!active)
        {
            active = true;
            timer.start();
        }
        else
        {
            active = false;
            timer.stop();
        }
   }

   public void play()
   {
        boolean start = false;
        boolean limit = false;
        int o = 0;
        int p = 0;
        pops = 0;
        //while(active)
       // {
            //pops = 0;
            for(int k = 0; k < grid.length; k++)
            {
                for(int l = 0; l < grid[k].length; l++)
                {
                    pops = 0;
                    if(k == 0)
                    {
                        start = true;
                    }
                    else
                    {
                        start = false;
                    }
                    if(k == 99)
                    {
                        limit = true;
                    }
                    else
                    {
                        limit = false;
                    }
                    // checks top of pixel as long as its not in the first row
                    if(l != 0)
                    {
                        if(start)
                            o = k;
                        else
                            o = k - 1;
                        if(limit)
                            p = k;
                        else
                            p = k + 1;
                        for(;o <= p; o++)
                        {
                            if(grid[o][l-1] == 1)
                            {
                                pops++;
                            }
                        }
                    }
                    // checks both sides of pixel as long as not against an edge
                    if(!start)
                    {
                        if(grid[k - 1][l] == 1)
                            pops++;
                    }
                    if(!limit)
                    {
                        if(grid[k+1][l] == 1)
                            pops++;
                    }
                    //checks bottom of pixel as long as not in the last row
                    if(l != 99)
                    {
                        if(start)
                            o = k;
                        else
                            o = k -1;
                        if(limit)
                            p = k;
                        else
                            p = k + 1;
                        for(;o <= p; o++)
                        {
                            if(grid[o][l+1] == 1)
                            {
                                pops++;
                            }
                        }
                    }
                    if(pops < 2)
                        grid2[k][l] = 0;
                    if(pops > 3)
                        grid2[k][l] = 0;
                    if(pops == 3)
                        grid2[k][l] = 1;
                    if(grid[k][l] == 1 && pops >= 2 && pops <= 3)
                        grid2[k][l] = 1;
                    //if(pops != 0)
                        //System.out.println(pops);
                    
                }
            }
            //grid = grid2;
            //grid2 = cleared;
            for(int k = 0; k < grid.length -1; k++)
            {
                for(int l = 0; l < grid[k].length; l++)
                {
                    grid[k][l] = grid2[k][l];
                }
            }

            draw(gridT);
            //System.out.println("cycle");
        //}
   }

   public void gridToggle()
   {
       if(gridT)
        gridT = false;
       else
        gridT = true;
       draw(gridT);
   }

   public void reset()
   {
        for(int k = 0; k < grid.length -1; k++)
        {
            for(int l = 0; l < grid[k].length; l++)
            {
                grid[k][l] = 0;
            }
        }
        for(int k = 0; k < grid.length -1; k++)
        {
            for(int l = 0; l < grid[k].length; l++)
            {
                grid2[k][l] = 0;
            }
        }
        draw(gridT); 
   }

   public void mouseMoved(java.awt.event.MouseEvent e)
   {
     mouseX = e.getX();
     mouseY = e.getY();
     //System.out.println("mouse" + e.getX() + e.getY());
     draw(gridT);
   }
   public void mouseDragged(java.awt.event.MouseEvent e)
   {

   }

   private class Listener implements ActionListener
   {
        public void actionPerformed(ActionEvent e)
        {
            play();
            //startStop();
        }
   }

   private class mouse implements MouseListener{

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
       // System.out.println("clicked!");
        addCell();
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
        
   }
}