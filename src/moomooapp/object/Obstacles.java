package moomooapp.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import moomooapp.Handler;
import moomooapp.ID;


public abstract class Obstacles 
{
    public int x;
    public int y;
    public int width;
    public int height;
    
    public boolean solid;
    
    public int velX;
    public int velY;
    
    public ID id;
    public Handler handler;
    
    public Obstacles(int x, int y, int width, int height, boolean solid, ID id, Handler handler)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;
        this.handler= handler;
        
    }
    
    public abstract void render(Graphics g);
    
    public abstract void tick();
    
    public void die()
    {
        handler.removeObstacle(this);
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSolid() {
        return solid;
    }

    public ID getId()
    {
        return id;
    }
    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
        public Rectangle getBounds()
    {
        return new Rectangle(getX(),getY(),width,height);
    }
}
