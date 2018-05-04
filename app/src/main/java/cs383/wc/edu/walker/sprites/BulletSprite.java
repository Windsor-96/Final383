package cs383.wc.edu.walker.sprites;

import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.bitmaps.BitmapSequence;
import cs383.wc.edu.walker.game_models.Collision;
import cs383.wc.edu.walker.game_models.Vec2d;

/**
 * TODO DOCUMENTATION
 */

public class BulletSprite extends PhysicalSprite {
    private PlayerSprite owner;
    private static final float VELOCITY = 400f;

    public BulletSprite(Vec2d v, PlayerSprite owner) {
        super(v);
        BitmapSequence s = new BitmapSequence();
        s.addImage(BitmapRepo.getInstance().getImage(R.drawable.bullet), 100);
        setBitmaps(s);
        setAcceleration(new Vec2d(VELOCITY, 0f));
        this.owner = owner;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void resolve(Collision collision, Sprite other) {
        super.resolve(collision, other);
        if (other instanceof BirdSprite) {
            ((BirdSprite) other).makeDead();
            owner.onBirdHit(this);
        }
    }
}