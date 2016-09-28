package moomooapp.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import moomooapp.Handler;
import moomooapp.ID;
import moomooapp.MooMooApp;
import moomooapp.object.Obstacles;

public class Player extends Entity
{
    Image moomoo;
    
    public Player(int x, int y, int width, int height, boolean solid, ID id, Handler handler)
    {
        super(x, y, width, height, solid, id, handler);
        //setVelX(5);
        
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(MooMooApp.moomoo.getBufferedImage(), x, y, width, height, null);
    }

    @Override
    public void tick()
    {
       x += velX;
       y += velY;
       
       if(x<=0)
           x = 0;
       
       if(x+width >=1080) x = 1080 - width;
       if(y+height >=771) y = 771 - height;
       
       for(Obstacles o:handler.obstacles)
       {
           if(!o.solid)
           break;
           if(o.getId()== ID.rock)
           {
               if(getBoundsTop().intersects(o.getBounds()))
               {
                   setVelY(0);
                  
               }
               
               if(getBoundsBottom().intersects(o.getBounds()))
               {
                   setVelY(0);
                   if(falling) falling = false;
                   
               }
               
               if(getBoundsLeft().intersects(o.getBounds()))
               {
                   setVelX(0);
                   x = o.getX() + o.width;
               }
               if(getBoundsRight().intersects(o.getBounds()))
               {
                   setVelX(0);
                   x = o.getX() - o.width;
               }
           }
       }
       
       if(jumping)
       {
           gravity-=0.1;
           setVelY((int)-gravity);
           
           if(gravity <= 0.0)
           {
               jumping = false;
               falling = true;
           }
       }
           
           if(falling)
           {
               gravity+=0.1;
               setVelY((int)gravity);
           }
       }
       
    }
    
