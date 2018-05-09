package cs383.wc.edu.walker.game_models;

import cs383.wc.edu.walker.activities.GameActivity;
import cs383.wc.edu.walker.sprites.BirdSprite;
import cs383.wc.edu.walker.sprites.BoostSprite;

public class LevelTwoWorld extends World {
    public LevelTwoWorld(GameActivity g) {
        super(g);
        addSprite(new BirdSprite(new Vec2d(3900, 821), this));
        addSprite(new BirdSprite(new Vec2d(2000, 821), this));
        addSprite(new BirdSprite(new Vec2d(6100, 200), this));
        addSprite(new BirdSprite(new Vec2d(1800, 500), this));
        addSprite(new BirdSprite(new Vec2d(2400, 800), this));
        addSprite(new BirdSprite(new Vec2d(2400, 200), this));
        addSprite(new BirdSprite(new Vec2d(3900, 700), this));
        addSprite(new BirdSprite(new Vec2d(3000, 900), this));
        addSprite(new BirdSprite(new Vec2d(3500, 700), this));
        addSprite(new BirdSprite(new Vec2d(5000, 200), this));
        addSprite(new BirdSprite(new Vec2d(7000, 700), this));
        addSprite(new BirdSprite(new Vec2d(3600, 900), this));
        addSprite(new BirdSprite(new Vec2d(4700, 700), this));
        addSprite(new BirdSprite(new Vec2d(6500, 200), this));
        addSprite(new BirdSprite(new Vec2d(3800, 700), this));
        addSprite(new BirdSprite(new Vec2d(4000, 900), this));
        addSprite(new BirdSprite(new Vec2d(4200, 300), this));
        addSprite(new BirdSprite(new Vec2d(4300, 200), this));
        addSprite(new BirdSprite(new Vec2d(6200, 500), this));
        addSprite(new BirdSprite(new Vec2d(4400, 821), this));
        addSprite(new BirdSprite(new Vec2d(5500, 200), this));
        addSprite(new BirdSprite(new Vec2d(4600, 600), this));
        addSprite(new BirdSprite(new Vec2d(4900, 200), this));
        addSprite(new BoostSprite(new Vec2d(6400, 289)));
        addSprite(new BoostSprite(new Vec2d(6400, 589)));
    }
}
