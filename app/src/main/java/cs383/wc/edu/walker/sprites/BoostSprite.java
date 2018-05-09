package cs383.wc.edu.walker.sprites;

import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.bitmaps.BitmapSequence;
import cs383.wc.edu.walker.game_models.Vec2d;

/**
 * @author David Windsor
 *         BoostSprite is really nice because all it does is sit there and double points when you collide.
 *         Nothing needs done in here, the player will check to see if it hit a boost and if it did, tell the world to remove it
 *         And then double its own points
 */

public class BoostSprite extends Sprite {
    public BoostSprite(Vec2d v) {
        super(v);
        BitmapSequence s = new BitmapSequence();
        s.addImage(BitmapRepo.getInstance().getImage(R.drawable.boost), 100);
        setBitmaps(s);
    }

    @Override
    public boolean isActive() {
        return false;
    }

}
