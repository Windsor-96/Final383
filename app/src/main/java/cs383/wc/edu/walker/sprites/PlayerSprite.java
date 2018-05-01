package cs383.wc.edu.walker.sprites;

import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.bitmaps.BitmapSequence;
import cs383.wc.edu.walker.game_models.Collision;
import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.game_models.Vec2d;

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
        deadSequence.addImage(r.getImage(R.drawable.plane_death1), 0.3);
        deadSequence.addImage(r.getImage(R.drawable.plane_death2), 0.3);
        deadSequence.addImage(r.getImage(R.drawable.plane_death3), 0.3);
        deadSequence.addImage(r.getImage(R.drawable.plane_death4), 0.3);

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
