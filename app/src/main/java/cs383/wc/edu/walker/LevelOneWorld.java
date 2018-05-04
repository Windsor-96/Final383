package cs383.wc.edu.walker;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

class LevelOneWorld extends World
{
    private List<Sprite> sprites;
    private PlayerSprite player;

    double screenWidth;
    double screenHeight;

    LevelOneWorld() {
        Bitmap bg = BitmapRepo.getInstance().getImage(R.drawable.background);
        screenHeight = bg.getHeight();
        screenWidth = bg.getWidth();
        this.sprites = new ArrayList<>();
        sprites.add(player = new PlayerSprite(new Vec2d(screenWidth/2.0,300)));
        sprites.add(new BirdSprite(new Vec2d(screenWidth, screenHeight)));
        sprites.add(new BirdSprite(new Vec2d(screenWidth, 0)));
    }

}
