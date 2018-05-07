package cs383.wc.edu.walker.game_models;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.TextureView;

import cs383.wc.edu.walker.activities.GameActivity;
import cs383.wc.edu.walker.activities.LevelOneActivity;

public class RenderLoop implements Runnable {
    private static final int FPS = 30;
    private final World world;
    private final TextureView textureView;

    public RenderLoop(TextureView textureView, GameActivity gameActivity) {
        this.textureView = textureView;
        world = new World(gameActivity);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            world.tick(1.0 / FPS);
            drawWorld();
            try {
                delay();
            } catch (InterruptedException ignored) {

            }
        }
    }

    private void delay() throws InterruptedException {
        Thread.sleep((long) (1.0 / FPS * 1000));
    }

    private void drawWorld() {
        Canvas c = textureView.lockCanvas();
        try {
            world.draw(c);
        } finally {
            textureView.unlockCanvasAndPost(c);
        }
    }
}