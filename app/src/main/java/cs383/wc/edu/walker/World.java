package cs383.wc.edu.walker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

class World {
    private List<Sprite> sprites;
    private PlayerSprite player;

    public World() {
        sprites = new ArrayList<>();
        sprites.add(player = new PlayerSprite(new Vec2d(100,1000)));
        sprites.add(new AlienSprite(new Vec2d(500, 100)));
        sprites.add(new AlienSprite(new Vec2d(300, 100)));
        sprites.add(new AlienSprite(new Vec2d(-200, 100)));
        sprites.add(new AlienSprite(new Vec2d(700, 100)));
    }

    public void tick(double dt) {
        MotionEvent e = TouchEventQueue.getInstance().dequeue();
        if (e != null)
            handleMotionEvent(e);
        for(Sprite s: sprites)
            s.tick(dt);
    }

    /**
     * When the user touches the screen, this message is sent.  Probably you
     * want to tell the player to do something?
     *
     * @param e the MotionEvent corresponding to the touch
     */
    private void handleMotionEvent(MotionEvent e) {

    }

    public void draw(Canvas c) {
        Bitmap bg = BitmapRepo.getInstance().getImage(R.drawable.background);
        float x = player.getPosition().getX();
        c.translate(-x+100, 0);
        int backgroundNumber = (int)(x / bg.getWidth());
        c.drawBitmap(bg, bg.getWidth()*(backgroundNumber-1), 0,  null);
        c.drawBitmap(bg, bg.getWidth()*backgroundNumber, 0,  null);
        c.drawBitmap(bg, bg.getWidth()*(backgroundNumber+1), 0,  null);
        for(Sprite s: sprites)
            s.draw(c);
    }
}
