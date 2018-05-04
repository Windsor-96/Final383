package cs383.wc.edu.walker;

public class BirdSprite extends Sprite {

    private static final int velocityX = 0;
    private static int velocityY = 0;
    private boolean isDead;
    private BitmapSequence deadSequence;


    public BirdSprite(Vec2d p) {
        super(p);
        loadBitmaps();
        isDead = false;

    }

    private void loadBitmaps() {
        BitmapRepo r = BitmapRepo.getInstance();
        BitmapSequence s = new BitmapSequence();
        s.addImage(r.getImage(R.drawable.bird_1), 0.1);
        s.addImage(r.getImage(R.drawable.bird_2_4), 0.1);
        s.addImage(r.getImage(R.drawable.bird_3), 0.1);
        s.addImage(r.getImage(R.drawable.bird_2_4), 0.1);

        deadSequence = new BitmapSequence();
        deadSequence.addImage(r.getImage(R.drawable.birtd_dead), 200);
        setBitmaps(s);

    }

    boolean isDead() {return isDead;}

    @Override
    public void tick(double dt) {
        super.tick(dt);
        setPosition(getPosition().add(new Vec2d(velocityX*dt,velocityY*dt)));
    }

    void makeDead() {
        isDead = true;
        setBitmaps(deadSequence);
        velocityY = 20;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
