package cs383.wc.edu.walker.game_models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorEvent;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.game_models.Collision;
import cs383.wc.edu.walker.game_models.Vec2d;
import cs383.wc.edu.walker.sprites.BirdSprite;
import cs383.wc.edu.walker.sprites.BulletSprite;
import cs383.wc.edu.walker.sprites.PlayerSprite;
import cs383.wc.edu.walker.sprites.Sprite;

public class World {
    private List<Sprite> sprites;
    private PlayerSprite player;
    private int tickCounter;

    public World() {
        sprites = new ArrayList<>();
        sprites.add(player = new PlayerSprite(new Vec2d(960,540), this));
        sprites.add(new BirdSprite(new Vec2d(2000, 540), this));
        tickCounter = 0;
    }

    public void tick(double dt) {
        grabTouchEvents();
        grabRotationEvents();
        ++tickCounter;
        if(tickCounter == 45) {
            sprites.add(new BulletSprite(new Vec2d(player.getX() + 30, player.getY() + 30), player));
            sprites.add(new BirdSprite(new Vec2d(player.getX() + 2000, player.getY()), this));
            tickCounter = 0;
        }
        for(Sprite s: sprites)
            s.tick(dt);
        resolveCollisions();

    }

    private void grabRotationEvents() {
        SensorEvent sensorEvent = SensorEventQueue.getInstance().dequeue();
        while (sensorEvent != null) {
            handleSensorEvent(sensorEvent);
            sensorEvent = SensorEventQueue.getInstance().dequeue();
        }
    }

    private void grabTouchEvents() {
        MotionEvent motionEvent = TouchEventQueue.getInstance().dequeue();
        while (motionEvent != null) {
            handleMotionEvent(motionEvent);
            motionEvent = TouchEventQueue.getInstance().dequeue();
        }
    }

    private void handleSensorEvent(SensorEvent e) {
        Log.d("ACCELEROMETER", "0: "+e.values[0]
                +"   1: "+e.values[1]+"   2: "+e.values[2]);
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
            if(e.getY() > player.getY())
                player.moveUp();
            else
                player.moveDown();
        }
        else if(e.getAction() == MotionEvent.ACTION_UP)
            player.moveStraight();

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

    public void removeBullet(BulletSprite bullet) {
        sprites.remove(bullet);
    }

    public void removeBird(BirdSprite birdSprite) {
        sprites.remove(birdSprite);
    }
}
