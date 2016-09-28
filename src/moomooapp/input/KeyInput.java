package moomooapp.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import moomooapp.MooMooApp;
import moomooapp.entity.Entity;

public class KeyInput implements KeyListener
{

    @Override
    public void keyPressed(KeyEvent ke)
    {
        int key = ke.getKeyCode();
        for(Entity en: MooMooApp.handler.entity)
        {
        switch(key)
        {
            case KeyEvent.VK_SPACE:
                if(!en.jumping) 
                {
                    en.jumping = true;
                    en.gravity = 10.0;
                }
                break;
            case KeyEvent.VK_LEFT:
                en.setVelX(-5);
                break;
            case KeyEvent.VK_RIGHT:
                en.setVelX(5);
                break;
        }
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) 
    {
        int key = ke.getKeyCode();
    
     for(Entity en: MooMooApp.handler.entity)
        {
        switch(key)
        {
            case KeyEvent.VK_SPACE:
                en.setVelY(0);
                break;
            case KeyEvent.VK_LEFT:
                en.setVelX(0);
                break;
            case KeyEvent.VK_RIGHT:
                en.setVelX(0);
                break;
        }
       
        }
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) 
    {
        
    }

    private void setVelX(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
