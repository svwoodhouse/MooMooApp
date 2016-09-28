package moomooapp;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import moomooapp.entity.Player;
import moomooapp.graphics.Sprite;
import moomooapp.graphics.SpriteSheet;
import moomooapp.input.KeyInput;
import moomooapp.object.Rock;

public class MooMooApp extends Canvas implements Runnable
{
    public static final String title = "Moo Moo Run";
    private Thread thread;
    private boolean running = false;
    public static Handler handler;
    public static SpriteSheet sheet;
    public static Sprite moomoo;
    
    public static final int WIDTH = 170;
    public static final int HEIGHT = WIDTH/14 * 10;
    //public static final int HEIGHT = 300;
    public static final int SCALE = 4;
    
    public MooMooApp()
    {
        Dimension size = new Dimension(WIDTH * SCALE ,HEIGHT * SCALE);
       // Dimension size = new Dimension(WIDTH,HEIGHT);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }
    
    private  void init()
    {
      handler = new Handler();
      sheet = new SpriteSheet("/baldsprite.png");
      addKeyListener(new KeyInput());
      moomoo = new Sprite(sheet,1,4);
      handler.addEntity(new Player(0,380,64,64,true,ID.player,handler));
      handler.addObstacle(new Rock(70,380,64,64,true,ID.rock,handler));
    }
    private synchronized void start()
    {
        if(running)
            return;
        running = true;
        thread = new Thread(this, "Thread");
        thread.start();
    }
    
    private synchronized void stop()
    {
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MooMooApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void run() 
    {
        init();
        requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0.0;
        double ns = 1000000000.0/60.0;
        int frames = 0;
        int ticks = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime = now;
            while(delta >= 1)
            {
                tick();
                ticks++;
                delta--;
            }
          render();
          frames++;
          if(System.currentTimeMillis()-timer > 1000)
          {
              timer +=1000;
              System.out.println(frames + " Frames per second " + ticks + " Updates Per Second");
              frames = 0;
              ticks = 0;
          }
        }
        stop();
    }
    
    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        Graphics g  = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        handler.render(g);
        g.dispose();
        bs.show();
      }
    
    //Update function
    public void tick()
    {
     handler.tick();
    }
    public static void main(String[] args) 
    {
        MooMooApp game = new MooMooApp();
        
        //JFrame mechanics
        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);  
        game.start();
    }
}
