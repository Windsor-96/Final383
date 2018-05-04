package cs383.wc.edu.walker.sprites;

import cs383.wc.edu.walker.activities.MainActivity;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.bitmaps.BitmapSequence;
import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.game_models.Vec2d;
import cs383.wc.edu.walker.game_models.World;

public class BirdSprite extends Sprite {
    private World world;
    private int velocityX = -20;
    private int velocityY = 0;
    private boolean isDead;
    private BitmapSequence deadSequence;


    public BirdSprite(Vec2d p, World w) {
        super(p);
        world = w;
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

    boolean isDead() {
        return isDead;
    }

    @Override
    public void tick(double dt) {
        super.tick(dt);
        setPosition(getPosition().add(new Vec2d(velocityX*dt,velocityY*dt)));
        if(getPosition().getY() > MainActivity.HEIGHT && isDead())
            world.removeBird(this);
    }

    void makeDead() {
        isDead = true;
        setBitmaps(deadSequence);
        velocityY = 300;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
