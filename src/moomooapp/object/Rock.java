package moomooapp.object;

import java.awt.Color;
import java.awt.Graphics;
import moomooapp.Handler;
import moomooapp.ID;

public class Rock extends Obstacles
{
    public Rock(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    @Override
    public void render(Graphics g) 
    {
        g.setColor(Color.gray);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void tick() 
    {
        
    }

    
}
