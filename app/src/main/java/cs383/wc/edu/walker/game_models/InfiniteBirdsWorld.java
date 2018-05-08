package cs383.wc.edu.walker.game_models;

import cs383.wc.edu.walker.activities.GameActivity;
import cs383.wc.edu.walker.sprites.BirdSprite;
import cs383.wc.edu.walker.sprites.PlayerSprite;

public class InfiniteBirdsWorld extends World
{
    public InfiniteBirdsWorld(GameActivity g)
    {
        super(g);
        setPlayer(new PlayerSprite(new Vec2d(50,getMid()), this));
        addSprite(getPlayer());
    }

    private int ticks;

    @Override
    void tick(double dt)
    {

        super.tick(dt);
        ticks++;
        float x = getPlayerX();
        if (ticks < 1000)
        {
            if (ticks % 101 == 0)
            {
                addSprite(new BirdSprite(new Vec2d(x + 2000, getMid()), this));
            }
            if (ticks % 153 == 0)
            {
                addSprite(new BirdSprite(new Vec2d(x + 2000, getLow()), this));
                addSprite(new BirdSprite(new Vec2d(x + 2000, getHigh()), this));
            }
        }
        else if (ticks < 2000)
        {
            if (ticks % 93 == 0)
            {
                addSprite(new BirdSprite(new Vec2d(x + 2000, getMid()), this));
            }
            if (ticks % 137 == 0)
            {
                addSprite(new BirdSprite(new Vec2d(x + 2000, getLow()), this));
                addSprite(new BirdSprite(new Vec2d(x + 2000, getHigh()), this));
            }
        }
        else
        {
            if (ticks % 57 == 0)
            {
                addSprite(new BirdSprite(new Vec2d(x + 2000, getMid()), this));
            }
            if (ticks % 71 == 0)
            {
                addSprite(new BirdSprite(new Vec2d(x + 2000, getLow()), this));
                addSprite(new BirdSprite(new Vec2d(x + 2000, getHigh()), this));
            }
        }
    }
}
