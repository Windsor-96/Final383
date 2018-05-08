package cs383.wc.edu.walker.game_models;

import android.graphics.Bitmap;

import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.activities.GameActivity;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.sprites.BirdSprite;
import cs383.wc.edu.walker.sprites.PlayerSprite;

public class LevelOneWorld extends World
{

        public LevelOneWorld(GameActivity g) {
            super(g);
            setPlayer(new PlayerSprite(new Vec2d(50,getMid()), this));
            addSprite(getPlayer());
            addSprite(new BirdSprite(new Vec2d(2000, getMid()), this));
            addSprite(new BirdSprite(new Vec2d(2000, getHigh()), this));
            addSprite(new BirdSprite(new Vec2d(1800, getMid()), this));
            addSprite(new BirdSprite(new Vec2d(2400, getMid()), this));
            addSprite(new BirdSprite(new Vec2d(3000, getMid()), this));
            addSprite(new BirdSprite(new Vec2d(3400, getMid()), this));
            addSprite(new BirdSprite(new Vec2d(3800, getMid()), this));
            addSprite(new BirdSprite(new Vec2d(4000, getLow()), this));
            addSprite(new BirdSprite(new Vec2d(4200, getHigh()),this));
        }

}
