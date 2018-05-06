package cs383.wc.edu.walker;

import android.graphics.Canvas;
import android.hardware.Sensor;
import android.view.TextureView;
import android.widget.TextView;

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
        Thread.sleep((long)(1.0/FPS * 1000));
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
