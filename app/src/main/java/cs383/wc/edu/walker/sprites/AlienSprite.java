package cs383.wc.edu.walker.sprites;

import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.bitmaps.BitmapSequence;
import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.game_models.Vec2d;

public class AlienSprite extends Sprite {

    private static final int velocityX = -300;
    private static final int velocityY = 300;


    public AlienSprite(Vec2d p) {
        super(p);
        loadBitmaps();
    }

    private void loadBitmaps() {
        BitmapRepo r = BitmapRepo.getInstance();
        BitmapSequence s = new BitmapSequence();
        s.addImage(r.getImage(R.drawable.aliengreen_walk1), 0.1);
        s.addImage(r.getImage(R.drawable.aliengreen_walk2), 0.1);
        setBitmaps(s);

    }


    @Override
    public void tick(double dt) {
        super.tick(dt);
        setPosition(getPosition().add(new Vec2d(velocityX*dt,velocityY*dt)));
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
