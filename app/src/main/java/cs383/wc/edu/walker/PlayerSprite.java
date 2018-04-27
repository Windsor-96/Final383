package cs383.wc.edu.walker;

public class PlayerSprite extends Sprite {

    private static final int velocity = 0;
    private boolean dead;
    private BitmapSequence deadSequence;

    public PlayerSprite(Vec2d v) {
        super(v);
        dead = false;
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

        deadSequence = new BitmapSequence();
        deadSequence.addImage(r.getImage(R.drawable.dead_stickman), 100);
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void tick(double dt) {
        super.tick(dt);
        checkForTouch();
        setPosition(getPosition().add(new Vec2d((float)(velocity*dt), 0)));
    }

    private void checkForTouch() {
    }

    @Override
    public void resolve(Collision collision, Sprite other) {
        if (!dead) makeDead();
    }

    private void makeDead() {
        dead = true;
        setBitmaps(deadSequence);
    }
}
