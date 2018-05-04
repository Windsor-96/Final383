package cs383.wc.edu.walker.sprites;

import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.bitmaps.BitmapSequence;
import cs383.wc.edu.walker.game_models.Collision;
import cs383.wc.edu.walker.game_models.Vec2d;

@SuppressWarnings("FieldCanBeLocal")
public class PlayerSprite extends Sprite {

    private static int VELOCITY = 300;
    private static float UP_ACCELERATION = 500;
    private static float DOWN_ACCELERATION = -500;
    private boolean dead;
    private BitmapSequence deadSequence;
    private Vec2d acceleration;
    private int points;

    public PlayerSprite(Vec2d v) {
        super(v);
        dead = false;
        loadBitmaps();
        acceleration = new Vec2d((float) VELOCITY, 0f);
        points = 0;
    }

    private void loadBitmaps() {
        BitmapRepo r = BitmapRepo.getInstance();
        BitmapSequence s = new BitmapSequence();
        //TODO modify the time between each frame accordingly
        s.addImage(r.getImage(R.drawable.player_plane1), 0.1);
        s.addImage(r.getImage(R.drawable.player_plane2), 0.1);
        s.addImage(r.getImage(R.drawable.player_plane3), 0.1);
        s.addImage(r.getImage(R.drawable.player_plane4), 0.1);
        s.addImage(r.getImage(R.drawable.player_plane5), 0.1);
        s.addImage(r.getImage(R.drawable.player_plane6), 0.1);
        s.addImage(r.getImage(R.drawable.player_plane7), 0.1);
        s.addImage(r.getImage(R.drawable.player_plane8), 0.1);

        setBitmaps(s);

        deadSequence = new BitmapSequence();
        deadSequence.addImage(r.getImage(R.drawable.plane_death1), 0.6);
        deadSequence.addImage(r.getImage(R.drawable.plane_death2), 0.6);
        deadSequence.addImage(r.getImage(R.drawable.plane_death3), 0.6);
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

    public int getPoints() {return points;}

    public void addTimeSurvived(int time) { points += time;}

    public float getX() {
        return getPosition().getX();
    }

    public void moveUp() {
        acceleration.setY(UP_ACCELERATION);
    }

    public void moveDown() {
        acceleration.setY(DOWN_ACCELERATION);
    }

    public void levelOut() {
        acceleration.setY(0f);
    }

    void onBirdHit() {
        points += 10;
    }
}
