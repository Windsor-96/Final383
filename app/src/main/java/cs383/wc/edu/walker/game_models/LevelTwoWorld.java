package cs383.wc.edu.walker.game_models;

import cs383.wc.edu.walker.activities.GameActivity;
import cs383.wc.edu.walker.sprites.BirdSprite;
import cs383.wc.edu.walker.sprites.BoostSprite;

public class LevelTwoWorld extends World {
    public LevelTwoWorld(GameActivity g) {
        super(g);
        addSprite(new BirdSprite(new Vec2d(3900, 821), this));
        addSprite(new BirdSprite(new Vec2d(2000, 821), this));
        addSprite(new BirdSprite(new Vec2d(2000, 200), this));
        addSprite(new BirdSprite(new Vec2d(1800, 500), this));
        addSprite(new BirdSprite(new Vec2d(2400, 800), this));
        addSprite(new BirdSprite(new Vec2d(2400, 200), this));
        addSprite(new BirdSprite(new Vec2d(2700, 700), this));
        addSprite(new BirdSprite(new Vec2d(3000, 900), this));
        addSprite(new BirdSprite(new Vec2d(35000, 700), this));
        addSprite(new BirdSprite(new Vec2d(3000, 200), this));
        addSprite(new BirdSprite(new Vec2d(3400, 700), this));
        addSprite(new BirdSprite(new Vec2d(3600, 900), this));
        addSprite(new BirdSprite(new Vec2d(3600, 700), this));
        addSprite(new BirdSprite(new Vec2d(3600, 200), this));
        addSprite(new BirdSprite(new Vec2d(3800, 700), this));
        addSprite(new BirdSprite(new Vec2d(4000, 900), this));
        addSprite(new BirdSprite(new Vec2d(4200, 300), this));
        addSprite(new BirdSprite(new Vec2d(4300, 200), this));
        addSprite(new BirdSprite(new Vec2d(4400, 500), this));
        addSprite(new BirdSprite(new Vec2d(4400, 821), this));
        addSprite(new BirdSprite(new Vec2d(4500, 200), this));
        addSprite(new BirdSprite(new Vec2d(4600, 600), this));
        addSprite(new BirdSprite(new Vec2d(4700, 200), this));
        addSprite(new BoostSprite(new Vec2d(2600, 289)));
        addSprite(new BoostSprite(new Vec2d(3800, 589)));
    }
}
