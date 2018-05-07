package cs383.wc.edu.walker.game_models;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import cs383.wc.edu.walker.*;
import cs383.wc.edu.walker.activities.GameActivity;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.sprites.BirdSprite;
import cs383.wc.edu.walker.sprites.PlayerSprite;

public class LevelOneWorld extends cs383.wc.edu.walker.game_models.World
{

    double midPoint;
    double top;
    double bottom;
    double screenLength;

    public LevelOneWorld(GameActivity g) {
        super(g);
        Bitmap bg = BitmapRepo.getInstance().getImage(R.drawable.background);
        midPoint = bg.getHeight()/2.0;
        screenLength = bg.getWidth();
        setPlayer(new PlayerSprite(new Vec2d(50,300), this));
        addSprite(getPlayer());
        addSprite(new BirdSprite(new Vec2d(2000, 300), this));
        addSprite(new BirdSprite(new Vec2d(2000, 450), this));
        addSprite(new BirdSprite(new Vec2d(1800, 300), this));
        addSprite(new BirdSprite(new Vec2d(2400, 300), this));
        addSprite(new BirdSprite(new Vec2d(3000, 300), this));
        addSprite(new BirdSprite(new Vec2d(3400, 300), this));
        addSprite(new BirdSprite(new Vec2d(3800, 300), this));
        addSprite(new BirdSprite(new Vec2d(4000, 150), this));
        addSprite(new BirdSprite(new Vec2d(4200, 450), this));
    }
}
