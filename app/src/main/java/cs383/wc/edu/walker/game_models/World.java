package cs383.wc.edu.walker.game_models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.sprites.BirdSprite;
import cs383.wc.edu.walker.sprites.PlayerSprite;
import cs383.wc.edu.walker.sprites.Sprite;

class World {
    private List<Sprite> sprites;
    private PlayerSprite player;

    World() {
        sprites = new ArrayList<>();
        sprites.add(player = new PlayerSprite(new Vec2d(100, 500)));
        sprites.add(new BirdSprite(new Vec2d(500, 100)));

    }

    void tick(double dt) {
        MotionEvent e = TouchEventQueue.getInstance().dequeue();
        if (e != null)
            handleMotionEvent(e);
        for(Sprite s: sprites)
            s.tick(dt);
        resolveCollisions();
    }

    private void resolveCollisions() {
        ArrayList<Collision> collisions = new ArrayList<>();
        for(int i=0; i < sprites.size()-1; i++)
            for(int j=i+1; j < sprites.size(); j++) {
                Sprite s1 = sprites.get(i);
                Sprite s2 = sprites.get(j);

                if (s1.collidesWith(s2))
                    collisions.add(new Collision(s1, s2));
            }

        for(Collision c: collisions) c.resolve();
    }


    /**
     * When the user touches the screen, this message is sent.  Probably you
     * want to tell the player to do something?
     *
     * @param e the MotionEvent corresponding to the touch
     */
    private void handleMotionEvent(MotionEvent e) {
        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            float eventX = e.getX();
            float playerX = player.getX();
            if(eventX < playerX)
                player.moveDown();
            else if(eventX > playerX)
                player.moveUp();
        }
        else if(e.getAction() == MotionEvent.ACTION_UP)
            player.levelOut();
    }

    void draw(Canvas c) {
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
