package cs383.wc.edu.walker;


/**
 *
 */

public class CloudSprite extends Sprite {


    public CloudSprite(Vec2d v) {
        super(v);
        BitmapSequence s = new BitmapSequence();
        s.addImage(BitmapRepo.getInstance().getImage(R.drawable.cloud), 100);
        setBitmaps(s);
    }

    /**
     *
     * @param dt change in time since the last tick
     * It's a cloud, it never needs to do anything besides stay there and look like a cloud
     */
    @Override
    public void tick(double dt) {}

    @Override
    public boolean isActive() {
        return false;
    }
}
