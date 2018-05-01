package cs383.wc.edu.walker.sprites;




import cs383.wc.edu.walker.bitmaps.BitmapSequence;
import cs383.wc.edu.walker.game_models.Vec2d;

/**
 * Sprite class used to make a sprite that will scroll in the background of the screen
 * After the entire sprite is off screen it will send itself to the beginning of the screen to keep itself moving
 */

public class ScrollingSprite extends PhysicalSprite {


    public ScrollingSprite(Vec2d v, BitmapSequence bitmap) {
        super(v);

    }


    @Override
    public boolean isActive() {
        return false;
    }
}
