package cs383.wc.edu.walker;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

class LevelOneWorld extends World
{

    double midPoint;
    double top;
    double bottom;
    double screenLength;

    LevelOneWorld() {
        Bitmap bg = BitmapRepo.getInstance().getImage(R.drawable.background);
        midPoint = bg.getHeight()/2.0;
        screenLength = bg.getWidth();
        setPlayer(new PlayerSprite(new Vec2d(0,300)));
        addSprite(getPlayer());
        addSprite(new BirdSprite(new Vec2d(2000, 300)));
        addSprite(new BirdSprite(new Vec2d(2000, 450)));
        addSprite(new BirdSprite(new Vec2d(1800, 300)));
        addSprite(new BirdSprite(new Vec2d(2400, 300)));
        addSprite(new BirdSprite(new Vec2d(3000, 300)));
        addSprite(new BirdSprite(new Vec2d(3400, 300)));
        addSprite(new BirdSprite(new Vec2d(3800, 300)));
        addSprite(new BirdSprite(new Vec2d(4000, 150)));
        addSprite(new BirdSprite(new Vec2d(4200, 450)));
    }
}
