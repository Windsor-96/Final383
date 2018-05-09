package cs383.wc.edu.walker.game_models;

import cs383.wc.edu.walker.activities.GameActivity;
import cs383.wc.edu.walker.sprites.BirdSprite;
import cs383.wc.edu.walker.sprites.BoostSprite;

public class LevelOneWorld extends World {

    public LevelOneWorld(GameActivity g) {
        super(g);
        addSprite(new BirdSprite(new Vec2d(2000, 300), this));
        addSprite(new BirdSprite(new Vec2d(2000, 1000), this));
        addSprite(new BirdSprite(new Vec2d(1800, 500), this));
        addSprite(new BirdSprite(new Vec2d(2400, 300), this));
        addSprite(new BirdSprite(new Vec2d(3000, 500), this));
        addSprite(new BirdSprite(new Vec2d(3400, 650), this));
        addSprite(new BirdSprite(new Vec2d(3800, 590), this));
        addSprite(new BirdSprite(new Vec2d(4000, 300), this));
        addSprite(new BirdSprite(new Vec2d(4200, 900), this));
        addSprite(new BoostSprite(new Vec2d(3600, 560)));
        addSprite(new BoostSprite(new Vec2d(2000, 300)));
    }

}
