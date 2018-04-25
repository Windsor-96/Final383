package cs383.wc.edu.walker;

public class PlayerSprite extends Sprite {

    private static final int velocity = -900;

    public PlayerSprite(Vec2d v) {
        super(v);
        loadBitmaps();
    }

    private void loadBitmaps() {
        BitmapRepo r = BitmapRepo.getInstance();
        BitmapSequence s = new BitmapSequence();
        s.addImage(r.getImage(R.drawable.stickman1), 0.1);
        s.addImage(r.getImage(R.drawable.stickman2), 0.1);
        s.addImage(r.getImage(R.drawable.stickman3), 0.1);
        s.addImage(r.getImage(R.drawable.stickman2), 0.1);
        setBitmaps(s);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void tick(double dt) {
        super.tick(dt);
        setPosition(getPosition().add(new Vec2d((float)(velocity*dt), 0)));
    }
}
