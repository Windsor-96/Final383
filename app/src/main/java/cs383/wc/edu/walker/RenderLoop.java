package cs383.wc.edu.walker;

import android.graphics.Canvas;
import android.view.TextureView;

public class RenderLoop implements Runnable {
    private static final int FPS = 30;
    private final World world;
    private final TextureView textureView;

    public RenderLoop(TextureView textureView, World world) {
        this.textureView = textureView;
        this.world = world;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            world.tick(1.0/FPS);
            drawWorld();
            try {
                delay();
            } catch (InterruptedException ex) {

            }
        }
    }

    private void delay() throws InterruptedException {
        //I doubled this so we could run it
        Thread.sleep((long)(1.0/FPS * 2000));
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
