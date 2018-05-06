package cs383.wc.edu.walker;
import android.app.ActionBar;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;

import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.BitmapRepo;
import cs383.wc.edu.walker.RenderLoop;
import cs383.wc.edu.walker.TouchEventQueue;

public class LevelOneActivity extends AppCompatActivity implements SensorEventListener
{
    private World world;
    private TextureView textureView;
    private Thread renderLoopThread;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    private TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
            startThreads();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            stopThreads();
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

        }
    };

    private void stopThreads() {
        if (renderLoopThread != null) {
            renderLoopThread.interrupt();
        }
        try {
            renderLoopThread.join();
        } catch (InterruptedException e) {
            // don't really care
        }
        renderLoopThread = null;
    }

    private void startThreads() {
        RenderLoop renderLoop = new RenderLoop(textureView, world);
        renderLoopThread = new Thread(renderLoop);
        renderLoopThread.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_level);
        BitmapRepo.getInstance().setContext(this);
        world = new LevelOneWorld();
        textureView = findViewById(R.id.level_one_textureview);
        textureView.setSurfaceTextureListener(textureListener);
        textureView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TouchEventQueue.getInstance().enqueue(event);
                return true;
            }
        });

        mSensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        goFullScreen();
    }

    @Override
    public void onSensorChanged(SensorEvent e)
    {
        double x = e.values[0];
        double y = e.values[1];

        if (Math.abs(x) > Math.abs(y))
        {
            //I think I calculated this to be 10 degrees using their formula?
            if (x < -0.09)
            {
                //tilting right
                world.goUp();
            }
            else if (x > 0.09)
            {
                //tilting left
                world.goDown();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }


    // See https://developer.android.com/training/system-ui/status
    private void goFullScreen() {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getActionBar();
        if (actionBar != null) actionBar.hide();
    }
}
