package cs383.wc.edu.walker.sprites;

import android.util.Log;

import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.activities.LevelOneActivity;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.bitmaps.BitmapSequence;
import cs383.wc.edu.walker.game_models.Collision;
import cs383.wc.edu.walker.game_models.Vec2d;
import cs383.wc.edu.walker.game_models.World;

@SuppressWarnings("FieldCanBeLocal")
public class PlayerSprite extends Sprite {

    private static int VELOCITY = 150;
    private World world;
    private boolean dead;
    private BitmapSequence deadSequence;
    private Vec2d acceleration;
    private long score;

    public PlayerSprite(Vec2d v, World containerWorld) {
        super(v);
        world = containerWorld;
        dead = false;
        loadBitmaps();
        acceleration = new Vec2d( VELOCITY, 0f);
        score = 0;
    }

    private void loadBitmaps() {
        BitmapRepo r = BitmapRepo.getInstance();
        r.setContext(world.getContext());
        BitmapSequence s = new BitmapSequence();
        //TODO modify the time between each frame accordingly

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
        if(getPosition().getY() + 100 > LevelOneActivity.HEIGHT || getPosition().getY() < 0)
            moveStraight();

    }


    @Override
    public void resolve(Collision collision, Sprite other) {
        if (other instanceof BirdSprite && !((BirdSprite) other).isDead()) {
            VELOCITY = 0;
            if (!dead) makeDead();
            ((BirdSprite) other).makeDead();
        }
        else if(other instanceof BoostSprite) {
            //we only have one boost so we're just gonna tell the boost to remove itself, and then tell the world to boost us
            world.removeBulletSprite((BulletSprite)other);
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

    public void addTimeSurvived(int time) { score += time;}

    public float getY() {
        return getPosition().getY();
    }
    public float getX() {
        return getPosition().getX();
    }

    public void moveStraight() {
        acceleration.setY(0f);
    }

    void onBirdHit(BulletSprite bullet, boolean isAlreadyDead) {
        if(!isAlreadyDead) {
            score += 10;
            Log.d("SCORE: ", "" + score);
        }
        world.removeBulletSprite(bullet);
    }

    public boolean isDead() {
        return dead;
    }


}
