package cs383.wc.edu.walker.sprites;

import android.support.annotation.NonNull;

import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.activities.LevelOneActivity;
import cs383.wc.edu.walker.activities.MainActivity;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.bitmaps.BitmapSequence;
import cs383.wc.edu.walker.game_models.Vec2d;
import cs383.wc.edu.walker.game_models.World;

@SuppressWarnings("FieldCanBeLocal")
public class BirdSprite extends Sprite implements Comparable<BirdSprite> {
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
        s.addImage(r.getImage(R.drawable.bird_1), 0.05);
        s.addImage(r.getImage(R.drawable.bird_2_4), 0.05);
        s.addImage(r.getImage(R.drawable.bird_3), 0.05);
        s.addImage(r.getImage(R.drawable.bird_2_4), 0.05);

        deadSequence = new BitmapSequence();
        deadSequence.addImage(r.getImage(R.drawable.bird_dead), 200);
        setBitmaps(s);

    }

    boolean isDead() {
        return isDead;
    }

    @Override
    public void tick(double dt) {
        super.tick(dt);
        setPosition(getPosition().add(new Vec2d(velocityX * dt, velocityY * dt)));
        if ((getPosition().getY() > MainActivity.HEIGHT && isDead()) || getPosition().getX() + 100 < world.getPlayerX())
            world.removeBirdSprite(this);
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

    @Override
    public int compareTo(@NonNull BirdSprite o) {
        return this.equals(o) ? 0 : 1;
    }
}
