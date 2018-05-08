package cs383.wc.edu.walker.sprites;

import android.util.Log;

import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.activities.MainActivity;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.bitmaps.BitmapSequence;
import cs383.wc.edu.walker.game_models.Collision;
import cs383.wc.edu.walker.game_models.Vec2d;
import cs383.wc.edu.walker.game_models.World;

@SuppressWarnings("FieldCanBeLocal")
public class PlayerSprite extends Sprite {

    private static int VELOCITY = 150;
    private static float UP_ACCELERATION = 250;
    private static float DOWN_ACCELERATION = -250;
    private World world;
    private boolean dead;
    private BitmapSequence deadSequence;
    private static final Vec2d acceleration = new Vec2d(VELOCITY, 0f);
    private long score;

    public PlayerSprite(Vec2d v, World containerWorld) {
        super(v);
        world = containerWorld;
        dead = false;
        loadBitmaps();
        score = 0;
    }

    private void loadBitmaps() {
        BitmapRepo r = BitmapRepo.getInstance();
        r.setContext(world.getContext());
        BitmapSequence s = new BitmapSequence();
        s.addImage(r.getImage(R.drawable.plane), 60);
        setBitmaps(s);

        deadSequence = new BitmapSequence();
        deadSequence.addImage(r.getImage(R.drawable.plane_death1), 0.2);
        deadSequence.addImage(r.getImage(R.drawable.plane_death2), 0.2);
        deadSequence.addImage(r.getImage(R.drawable.plane_death3), 0.2);
        deadSequence.addImage(r.getImage(R.drawable.plane_death4), 0.6);

    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void tick(double dt) {
        super.tick(dt);
        setPosition(getPosition().add(new Vec2d((acceleration.getX() * dt), acceleration.getY() * dt)));
        if (getPosition().getY() + 100 > MainActivity.HEIGHT || getPosition().getY() < 0)
            moveStraight();

    }


    @Override
    public void resolve(Collision collision, Sprite other) {
        if (other instanceof BirdSprite && !((BirdSprite) other).isDead()) {
            VELOCITY = 0;
            if (!dead) makeDead();
            ((BirdSprite) other).makeDead();
        } else if (other instanceof BoostSprite) {
            //we only have one boost so we're just gonna tell the boost to remove itself, and then tell the world to boost us
            world.removeBoostSprite((BoostSprite) other);
            //Is it ridiculous to casually double the player's score? Probably
            //But it's also straight forward and gives the player other things to do then shoot at twitter birds
            score *= 2;
        }
    }

    public long getScore() {
        return score;
    }

    private void makeDead() {
        dead = true;
        setBitmaps(deadSequence);
        world.onPlayerDeath();
    }

    public void addTimeSurvived(int time) {
        score += time;
    }

    public float getY() {
        return getPosition().getY();
    }

    public float getX() {
        return getPosition().getX();
    }

    public void moveUp() {
        acceleration.setY(UP_ACCELERATION);
    }

    public void moveDown() {
        acceleration.setY(DOWN_ACCELERATION);
    }

    public void moveStraight() {
        acceleration.setY(0f);
    }

    void onBirdHit(BulletSprite bullet, boolean isAlreadyDead) {
        if (!isAlreadyDead) {
            score += 10;
            Log.d("SCORE: ", "" + score);
        }
        world.removeBulletSprite(bullet);
    }

    public boolean isDead() {
        return dead;
    }


}
