package moomooapp;

import java.awt.Graphics;
import java.util.LinkedList;
import moomooapp.entity.Entity;
import moomooapp.object.Obstacles;
import moomooapp.object.Rock;

/**
 *
 * @author Syd
 */
public class Handler 
{
    public LinkedList<Entity> entity = new LinkedList<Entity>();
    public LinkedList<Obstacles> obstacles = new LinkedList<Obstacles>();
    
    public Handler()
    {
        createLevel();
    }
    public void render(Graphics g)
    {
        for(Entity en:entity)
        {
            en.render(g);
        }
        for(Obstacles ob: obstacles)
        {
            ob.render(g);
        }
    }
    
    public void tick()
    {
       for(Entity en:entity)
        {
            en.tick();
        }
        for(Obstacles ob: obstacles)
        {
            ob.tick();
        }
    }
    
    public void addEntity(Entity en)
    {
        entity.add(en);
    }
    
    public void removeEntity(Entity en)
    {
        entity.remove(en);
    }
    
    public void addObstacle(Obstacles ob)
    {
        obstacles.add(ob);   
    }
    
    public void removeObstacle(Obstacles ob)
    {
        obstacles.remove(ob);
    }
    
    public void createLevel()
    {
        for(int i = 0; i < MooMooApp.WIDTH * MooMooApp.SCALE / 64+1; i++)
        {
            addObstacle(new Rock(i*64, MooMooApp.HEIGHT * MooMooApp.SCALE - 40,64,50,true,ID.rock, this));

        }
    }

}
