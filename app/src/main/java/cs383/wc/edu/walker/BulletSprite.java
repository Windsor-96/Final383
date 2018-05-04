package cs383.wc.edu.walker;

/**
 * TODO DOCUMENTATION
 */

public class BulletSprite extends PhysicalSprite {
    private PlayerSprite owner;
    private static final float VELOCITY = 20f;

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
            owner.onBirdHit();
        }
    }
}