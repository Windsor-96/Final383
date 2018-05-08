package cs383.wc.edu.walker.game_models;

import cs383.wc.edu.walker.activities.GameActivity;
import cs383.wc.edu.walker.sprites.BirdSprite;
import cs383.wc.edu.walker.sprites.PlayerSprite;

public class LevelTwoWorld extends World
{
    public LevelTwoWorld(GameActivity g) {
        super(g);
        setPlayer(new PlayerSprite(new Vec2d(50,getMid()), this));
        addSprite(getPlayer());
        addSprite(new BirdSprite(new Vec2d(1900, getLow()), this));
        addSprite(new BirdSprite(new Vec2d(2000, getLow()), this));
        addSprite(new BirdSprite(new Vec2d(2000, getHigh()), this));
        addSprite(new BirdSprite(new Vec2d(1800, getMid()), this));
        addSprite(new BirdSprite(new Vec2d(2400, getMid()), this));
        addSprite(new BirdSprite(new Vec2d(2400, getMid()), this));
        addSprite(new BirdSprite(new Vec2d(2700, getMid()), this));
        addSprite(new BirdSprite(new Vec2d(3000, getLow()), this));
        addSprite(new BirdSprite(new Vec2d(3000, getMid()), this));
        addSprite(new BirdSprite(new Vec2d(3000, getHigh()), this));
        addSprite(new BirdSprite(new Vec2d(3400, getMid()), this));
        addSprite(new BirdSprite(new Vec2d(3600, getLow()), this));
        addSprite(new BirdSprite(new Vec2d(3600, getMid()), this));
        addSprite(new BirdSprite(new Vec2d(3600, getHigh()), this));
        addSprite(new BirdSprite(new Vec2d(3800, getMid()), this));
        addSprite(new BirdSprite(new Vec2d(4000, getLow()), this));
        addSprite(new BirdSprite(new Vec2d(4200, getHigh()),this));
        addSprite(new BirdSprite(new Vec2d(4300, getHigh()),this));
        addSprite(new BirdSprite(new Vec2d(4400, getHigh()),this));
        addSprite(new BirdSprite(new Vec2d(4400, getLow()),this));
        addSprite(new BirdSprite(new Vec2d(4500, getHigh()),this));
        addSprite(new BirdSprite(new Vec2d(4600, getHigh()),this));
        addSprite(new BirdSprite(new Vec2d(4700, getHigh()),this));
    }
}
