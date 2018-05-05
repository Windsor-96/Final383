package cs383.wc.edu.walker.game_models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorEvent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.activities.MainActivity;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.game_models.Collision;
import cs383.wc.edu.walker.game_models.Vec2d;
import cs383.wc.edu.walker.sprites.BirdSprite;
import cs383.wc.edu.walker.sprites.BulletSprite;
import cs383.wc.edu.walker.sprites.PlayerSprite;
import cs383.wc.edu.walker.sprites.Sprite;

public class World {
    //The rate of shots is 1/BULLET_RATE ticks
    private static int BULLET_RATE = 45;
    private List<Sprite> sprites;
    private PlayerSprite player;
    private int tickCounter, shotCounter;

    /**
     * The remove queue exists to avoid ConcurrentModificationExceptions caused by removing a sprite
     * from the sprite list while we're iterating through it
     * whenever a sprite needs taken off it will put itself on the queue, and then once we are done iterating
     * we will remove all sprites in the queue
     * IF THIS IS REMOVED WE WILL GET THAT EXCEPTION ANYTIME A BULLET OR BIRD ATTEMPTS TO REMOVE ITSELF
     */
    private PriorityQueue<Sprite> removeQueue;
    private MainActivity activity;

    /**
     *
     * @param mainActivity the activity hosting the world
     *                     This is required for sound since we need a context to play a sound, or at least I haven't found another way
     */
    public World(MainActivity mainActivity) {
        sprites = new ArrayList<>();
        sprites.add(player = new PlayerSprite(new Vec2d(960,540), this));
        sprites.add(new BirdSprite(new Vec2d(2000, 540), this));
        removeQueue = new PriorityQueue<>();
        tickCounter = shotCounter = 0;
        activity = mainActivity;
    }

    public void tick(double dt) {
        grabTouchEvents();
        grabRotationEvents();
        ++tickCounter;
        if(tickCounter == BULLET_RATE) {
            sprites.add(new BulletSprite(new Vec2d(player.getX() + 30, player.getY() + 30), player, this));
            //TODO remove this line when we get pre-generated maps
            sprites.add(new BirdSprite(new Vec2d(player.getX() + 2000, player.getY()), this));
            tickCounter = 0;

        }
        for(Sprite s: sprites)
            s.tick(dt);
        resolveCollisions();
        if(!removeQueue.isEmpty()) {
            sprites.removeAll(removeQueue);
            removeQueue.clear();
        }
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

    public void removeSprite(Sprite sprite) {
        removeQueue.add(sprite);
    }

    public void playMedia(int resource) {
        activity.playMedia(resource);
    }

}
