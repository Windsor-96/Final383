package cs383.wc.edu.walker.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.SurfaceTexture;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import cs383.wc.edu.walker.R;
import cs383.wc.edu.walker.bitmaps.BitmapRepo;
import cs383.wc.edu.walker.game_models.LevelTwoWorld;
import cs383.wc.edu.walker.game_models.RenderLoop;
import cs383.wc.edu.walker.game_models.SensorEventQueue;
import cs383.wc.edu.walker.game_models.TouchEventQueue;
import cs383.wc.edu.walker.game_models.World;

//only warnings are for textureview listener and string literal in Button.setText
@SuppressWarnings("All")
public class LevelTwoActivity extends GameActivity implements SensorEventListener {

    private TextureView textureView;
    private Thread renderLoopThread;
    private Button exitButton;
    private Sensor mSensor;
    private SensorManager mSensorManager;
    private World world;


    /**
     * solve sample not ready issue. This may be solved already with loading screens, but for now I'm going back to MediaPlayer
     */
    //private SoundPool soundPool;
    private MediaPlayer bulletImpact, bulletLaunch, EAGLE;

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
            if (renderLoopThread != null) {
                renderLoopThread.join();
            }
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        world = new LevelTwoWorld(this);
        BitmapRepo.getInstance().setContext(this);
        textureView = findViewById(R.id.texture_view);
        textureView.setSurfaceTextureListener(textureListener);
        textureView.setOnTouchListener((View v, MotionEvent event) -> {
            TouchEventQueue.getInstance().enqueue(event);
            return true;
        });

        exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(v -> backToMenu());

        //make the music here and play it, we can change the music but for now you're stuck with Kenny Loggins,
//        soundPool = new SoundPool.Builder().setMaxStreams(10).build();
//        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
//            @Override
//            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//                if(sampleId == R.raw.danger_zone)
//                    soundPool.play(sampleId, 1f, 1f, 1, -1, 1);
//            }
//        });
//        soundPool.load(this, R.raw.bullet_launch, 1);
//        soundPool.load(this, R.raw.bullet_impact, 1);
//        soundPool.load(this, R.raw.danger_zone, 1);
//        soundPool.play(R.raw.danger_zone, 1f, 1f, 1, -1, 1f);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (mSensorManager != null) {
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        }

        bulletImpact = MediaPlayer.create(this, R.raw.bullet_impact);
        bulletLaunch = MediaPlayer.create(this, R.raw.bullet_launch);
        EAGLE = MediaPlayer.create(this, R.raw.eagle);
        EAGLE.setLooping(true);
        EAGLE.start();

        goFullScreen();
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

    public void playMedia(int resource) {
        //soundPool.play(resource, 1f, 1f, 1, 0, 1f);
        switch (resource) {
            case R.raw.bullet_impact:
                bulletImpact.start();
                break;
            case R.raw.bullet_launch:
                bulletLaunch.start();
                break;
        }
    }


    @Override
    public void promptLevelEnd(long score) {
        runOnUiThread(() -> {
            exitButton.setText("Points: " + score + "\nBack To Menu");
            exitButton.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void backToMenu() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (soundPool != null)
//            soundPool.autoPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bulletLaunch.pause();
        bulletImpact.pause();
        EAGLE.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
//        if (soundPool != null)
//            soundPool.autoResume();
        bulletLaunch.start();
        bulletImpact.start();
        EAGLE.start();
    }

    @Override
    public void onSensorChanged(SensorEvent e) {
        SensorEventQueue.getInstance().enqueue(e);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
