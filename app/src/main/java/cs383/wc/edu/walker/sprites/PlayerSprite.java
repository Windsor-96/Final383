package cs383.wc.edu.walker.sprites;

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
    private Vec2d acceleration;
    private int points;

    public PlayerSprite(Vec2d v, World containerWorld) {
        super(v);
        world = containerWorld;
        dead = false;
        loadBitmaps();
        acceleration = new Vec2d( VELOCITY, 0f);
        points = 0;
    }

    private void loadBitmaps() {
        BitmapRepo r = BitmapRepo.getInstance();
        BitmapSequence s = new BitmapSequence();
        //TODO modify the time between each frame accordingly
        s.addImage(r.getImage(R.drawable.plane), 60);
//        s.addImage(r.getImage(R.drawable.player_plane2), 0.1);
//        s.addImage(r.getImage(R.drawable.player_plane3), 0.1);
//        s.addImage(r.getImage(R.drawable.player_plane4), 0.1);
//        s.addImage(r.getImage(R.drawable.player_plane5), 0.1);
//        s.addImage(r.getImage(R.drawable.player_plane6), 0.1);
//        s.addImage(r.getImage(R.drawable.player_plane7), 0.1);
//        s.addImage(r.getImage(R.drawable.player_plane8), 0.1);

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
        if(getPosition().getY() > MainActivity.HEIGHT || getPosition().getY() < 0)
            moveStraight();

    }


    @Override
    public void resolve(Collision collision, Sprite other) {
        if (other instanceof BirdSprite && !((BirdSprite) other).isDead()) {
            VELOCITY = 0;
            if (!dead) makeDead();
            ((BirdSprite) other).makeDead();
        }
    }

    private void makeDead() {
        dead = true;
        setBitmaps(deadSequence);
    }

    public int getPoints() {
        return points;
    }

    public void addTimeSurvived(int time) { points += time;}

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

    void onBirdHit(BulletSprite bullet) {
        points += 10;
        world.removeBullet(bullet);
    }
}
