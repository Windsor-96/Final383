package cs383.wc.edu.walker.game_models;

import java.util.Random;

import cs383.wc.edu.walker.activities.GameActivity;
import cs383.wc.edu.walker.activities.MainActivity;
import cs383.wc.edu.walker.sprites.BirdSprite;
import cs383.wc.edu.walker.sprites.BoostSprite;

public class InfiniteBirdsWorld extends World {
    private Random rng;
    private int ticks;

    public InfiniteBirdsWorld(GameActivity g) {
        super(g);
        rng = new Random(System.currentTimeMillis());
    }

    @Override
    void tick(double dt) {
        super.tick(dt);
        ticks++;
        float x = getPlayerX();
        if (ticks < 1000) {
            if (ticks % 101 == 0)
            {
                addSprite(new BirdSprite(new Vec2d(x + 2400, rng.nextInt(MainActivity.HEIGHT - 200)), this));
            }
            if (ticks % 153 == 0) {
                addSprite(new BirdSprite(new Vec2d(x + 2400, rng.nextInt(MainActivity.HEIGHT - 200)), this));
                addSprite(new BirdSprite(new Vec2d(x + 2400, rng.nextInt(MainActivity.HEIGHT - 200) - 300), this));
            }
        } else if (ticks < 2000) {
                if (ticks % 93 == 0) {
                    addSprite(new BirdSprite(new Vec2d(x + 2600, rng.nextInt(MainActivity.HEIGHT - 200)), this));
                }
                if (ticks % 137 == 0) {
                    addSprite(new BirdSprite(new Vec2d(x + 2200, rng.nextInt(MainActivity.HEIGHT - 200)), this));
                    addSprite(new BirdSprite(new Vec2d(x + 2300, rng.nextInt(MainActivity.HEIGHT - 200) + 200), this));
                }
                if (ticks % 492 == 0)
                    addSprite(new BoostSprite(new Vec2d(x + 2200, rng.nextInt(MainActivity.HEIGHT - 200))));
        } else {
                if (ticks % 57 == 0) {
                    addSprite(new BirdSprite(new Vec2d(x + 2400, rng.nextInt(MainActivity.HEIGHT - 200)), this));
                }
                if (ticks % 71 == 0) {
                    addSprite(new BirdSprite(new Vec2d(x + 2400, rng.nextInt(MainActivity.HEIGHT - 200)), this));
                    addSprite(new BirdSprite(new Vec2d(x + 2400, rng.nextInt(MainActivity.HEIGHT - 200) + 500), this));
                }
                if (ticks % 1087 == 0)
                    addSprite(new BoostSprite(new Vec2d(x + 2400, rng.nextInt(MainActivity.HEIGHT - 200))));
        }
    }
}
