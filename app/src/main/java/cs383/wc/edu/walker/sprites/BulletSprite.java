package cs383.wc.edu.walker.sprites;

import android.support.annotation.NonNull;

import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.bitmaps.BitmapSequence;
import cs383.wc.edu.walker.game_models.Collision;
import cs383.wc.edu.walker.game_models.Vec2d;
import cs383.wc.edu.walker.game_models.World;

/**
 * Sprite class to simulate bullets/missiles
 * When colliding it will check if it has hit a bird, and if so call back to the player that it hit a bird
 * And then play its impact sound
 */

public class BulletSprite extends PhysicalSprite implements Comparable<BulletSprite> {
    private static final float VELOCITY = 400f;
    private PlayerSprite owner;
    private World world;

    public BulletSprite(Vec2d v, PlayerSprite owner, World _world) {
        super(v);
        BitmapSequence s = new BitmapSequence();
        s.addImage(BitmapRepo.getInstance().getImage(R.drawable.bullet), 100);
        setBitmaps(s);
        setAcceleration(new Vec2d(VELOCITY, 0f));
        this.owner = owner;
        world = _world;
        world.playMedia(R.raw.bullet_launch);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void resolve(Collision collision, Sprite other) {
        super.resolve(collision, other);
        if (other instanceof BirdSprite) {
            boolean alreadyDead = ((BirdSprite) other).isDead();
            ((BirdSprite) other).makeDead();
            owner.onBirdHit(this, alreadyDead);
            world.playMedia(R.raw.bullet_impact);
        }
    }

    @Override
    public int compareTo(@NonNull BulletSprite o) {
        return this.equals(o) ? 0 : 1;
    }
}