package cs383.wc.edu.walker.game_models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.SensorEvent;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.activities.GameActivity;
import cs383.wc.edu.walker.activities.LevelOneActivity;
import cs383.wc.edu.walker.activities.LevelTwoActivity;
import cs383.wc.edu.walker.activities.MainActivity;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.sprites.BirdSprite;
import cs383.wc.edu.walker.sprites.BoostSprite;
import cs383.wc.edu.walker.sprites.BulletSprite;
import cs383.wc.edu.walker.sprites.PlayerSprite;
import cs383.wc.edu.walker.sprites.Sprite;

@SuppressWarnings("FieldCanBeLocal")
public class World {
    //The rate of shots is 1/BULLET_RATE ticks
    private static int BULLET_RATE = 45;
    private List<Sprite> sprites;
    private PlayerSprite player;
    private int tickCounter;
    private boolean isGameOver;
    private Paint paint;
    private int worldHeight;
    private int screenLength;
    private int lowTier;
    private int midTier;
    private int highTier;
    /**
     * The remove queue exists to avoid ConcurrentModificationExceptions caused by removing a sprite
     * from the sprite list while we're iterating through it
     * whenever a sprite needs taken off it will put itself on the queue, and then once we are done iterating
     * we will remove all sprites in the queue
     * IF THIS IS REMOVED WE WILL GET THAT EXCEPTION ANYTIME A BULLET OR BIRD ATTEMPTS TO REMOVE ITSELF
     */
    private PriorityQueue<Sprite> removeQueue;
    private GameActivity activity;

    /**
     * @param gameActivity the activity hosting the world
     *                     This is required for sound since we need a context to play a sound, or at least I haven't found another way
     */
    World(GameActivity gameActivity) {
        sprites = new ArrayList<>();
        activity = gameActivity;
        isGameOver = false;
        paint = new Paint();
        paint.setColor(0xFFFFFFFF);
        paint.setTextSize(100);
        sprites.add(player = new PlayerSprite(new Vec2d(960, 540), this));

        if (gameActivity instanceof LevelOneActivity) {
            sprites.add(new BirdSprite(new Vec2d(2000, 300), this));
            sprites.add(new BirdSprite(new Vec2d(2000 * 1.8, 450), this));
            sprites.add(new BoostSprite(new Vec2d(2200 * 1.8, 900)));
            sprites.add(new BirdSprite(new Vec2d(1800 * 1.8, 300), this));
            sprites.add(new BirdSprite(new Vec2d(2400, 300), this));
            sprites.add(new BirdSprite(new Vec2d(3000 * 1.8, 300), this));
            sprites.add(new BirdSprite(new Vec2d(3400, 300), this));
            sprites.add(new BoostSprite(new Vec2d(3500, 100)));
            sprites.add(new BirdSprite(new Vec2d(3800, 300), this));
            sprites.add(new BirdSprite(new Vec2d(4000, 150), this));
            sprites.add(new BirdSprite(new Vec2d(4200, 450), this));

        } else if (gameActivity instanceof LevelTwoActivity) {
            sprites.add(new BirdSprite(new Vec2d(2000, 300), this));
            sprites.add(new BirdSprite(new Vec2d(2000, 450), this));
            sprites.add(new BoostSprite(new Vec2d(2200, 900)));
            sprites.add(new BirdSprite(new Vec2d(1800, 300), this));
            sprites.add(new BirdSprite(new Vec2d(2400, 200), this));
            sprites.add(new BirdSprite(new Vec2d(3000, 500), this));
            sprites.add(new BirdSprite(new Vec2d(3400, 100), this));
            sprites.add(new BoostSprite(new Vec2d(2500, 100)));
            sprites.add(new BirdSprite(new Vec2d(3800, 700), this));
            sprites.add(new BirdSprite(new Vec2d(4000, 850), this));
            sprites.add(new BirdSprite(new Vec2d(4200, 450), this));
            sprites.add(new BirdSprite(new Vec2d(1800, 300), this));
            sprites.add(new BirdSprite(new Vec2d(2400, 200), this));
            sprites.add(new BirdSprite(new Vec2d(3000, 500), this));
            sprites.add(new BirdSprite(new Vec2d(3400, 100), this));
        }

        sprites.add(new BirdSprite(new Vec2d(2000, 540), this));
        removeQueue = new PriorityQueue<>();

    }

    void tick(double dt) {
        if (!isGameOver) {
            grabTouchEvents();
            grabRotationEvents();
            ++tickCounter;
            if (tickCounter == BULLET_RATE && !player.isDead()) {
                sprites.add(new BulletSprite(new Vec2d(player.getX() + 80, player.getY() + 30), player, this));
                tickCounter = 0;

            }
            for (Sprite s : sprites)
                s.tick(dt);
            resolveCollisions();
            if (!removeQueue.isEmpty()) {
                sprites.removeAll(removeQueue);
                removeQueue.clear();
                isGameOver = !isBirdsRemaining();
            }
        }
        //TODO handle level being over, probably just tell the activity to finish or call a routine that prompts to mover on
        else {
            activity.promptLevelEnd(player.getScore());
        }
    }

    private boolean isBirdsRemaining() {
        for (Sprite s : sprites)
            if (s instanceof BirdSprite)
                return true;
        return false;
    }

    private void grabTouchEvents() {
        MotionEvent motionEvent = TouchEventQueue.getInstance().dequeue();
        while (motionEvent != null) {
            handleMotionEvent(motionEvent);
            motionEvent = TouchEventQueue.getInstance().dequeue();
        }
    }

    private void grabRotationEvents() {
        SensorEvent e = SensorEventQueue.getInstance().dequeue();
        while (e != null) {
            handleSensorEvent(e);
            e = SensorEventQueue.getInstance().dequeue();
        }
    }

    private void handleSensorEvent(SensorEvent e) {
        Log.d("Accelerometer", "x: " + e.values[0] + "y: " + e.values[1] + "z: " + e.values[2]);
        if (Math.abs(e.values[2]) > .07) {
            if (e.values[2] < 0) {
                player.moveUp();
            } else {
                player.moveDown();
            }
        } else {
            player.moveStraight();
        }
    }

    private void resolveCollisions() {
        ArrayList<Collision> collisions = new ArrayList<>();
        for (int i = 0; i < sprites.size() - 1; i++)
            for (int j = i + 1; j < sprites.size(); j++) {
                Sprite s1 = sprites.get(i);
                Sprite s2 = sprites.get(j);

                if (s1.collidesWith(s2))
                    collisions.add(new Collision(s1, s2));
            }

        for (Collision c : collisions) c.resolve();

    }


    /**
     * When the user touches the screen, this message is sent.  Probably you
     * want to tell the player to do something?
     *
     * @param e the MotionEvent corresponding to the touch
     */
    private void handleMotionEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            if (e.getY() > player.getY())
                player.moveUp();
            else
                player.moveDown();
        } else if (e.getAction() == MotionEvent.ACTION_UP)
            player.moveStraight();

    }

    void draw(Canvas c) {
        Bitmap bg = BitmapRepo.getInstance().getImage(R.drawable.background);
        float x = player.getPosition().getX();
        c.translate(-x + 100, 0);
        int backgroundNumber = (int) (x / bg.getWidth());
        c.drawBitmap(bg, bg.getWidth() * (backgroundNumber - 1), 0, null);
        c.drawBitmap(bg, bg.getWidth() * backgroundNumber, 0, null);
        c.drawBitmap(bg, bg.getWidth() * (backgroundNumber + 1), 0, null);
        for (Sprite s : sprites)
            s.draw(c);
        c.drawText("Points: " + player.getScore(), player.getX(), MainActivity.HEIGHT * .08f, paint);
    }


    public void playMedia(int resource) {
        activity.playMedia(resource);
    }


    public float getPlayerX() {
        return player.getX();
    }

    public void onPlayerDeath() {
        isGameOver = true;
    }

    public void removeBulletSprite(BulletSprite bulletSprite) {
        removeQueue.add(bulletSprite);
    }

    public void removeBirdSprite(BirdSprite birdSprite) {
        removeQueue.add(birdSprite);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void onGameOver() {
        activity.promptLevelEnd(player.getScore());
    }

    public void removeBoostSprite(BoostSprite other) {
        removeQueue.add(other);
    }

    void addSprite(Sprite s)
    {
        sprites.add(s);
    }

    void setPlayer(PlayerSprite p)
    {
        player = p;
    }

    PlayerSprite getPlayer()
    {
        return player;
    }

    void goDown()
    {
        Vec2d pos = player.getPosition();
        if (pos.getY() < worldHeight)
            pos.setY(pos.getY()+15);
    }

    void goUp()
    {
        Vec2d pos = player.getPosition();
        if (pos.getY() > 15)
            pos.setY(pos.getY()-15);
    }

    public GameActivity getContext()
    {
        return activity;
    }

    void setTiers()
    {
        midTier = worldHeight/2;
        highTier = midTier + worldHeight/4;
        lowTier = midTier - worldHeight/4;
    }

    public int getLow()
    {
        return lowTier;
    }

    public int getMid()
    {
        return midTier;
    }

    public int getHigh()
    {
        return highTier;
    }
}
