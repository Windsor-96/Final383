package cs383.wc.edu.walker.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cs383.wc.edu.walker.R;

/**
 * TODO DOCUMENTATION
 */

public class MainActivity extends AppCompatActivity {
    public static final int HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static final int WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;

    Button levelOneButton;
    Button levelTwoButton;
    MediaPlayer themeMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        themeMusic = MediaPlayer.create(this, R.raw.menu_music);
        themeMusic.start();

        levelOneButton = findViewById(R.id.main_level_one_button);
        levelOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeMusic.stop();
                MainActivity.this.startActivity(new Intent(MainActivity.this, LevelOneActivity.class));
            }
        });

        levelTwoButton = findViewById(R.id.main_level_two_button);
        levelTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeMusic.stop();
                MainActivity.this.startActivity(new Intent(MainActivity.this, LevelTwoActivity.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (themeMusic == null)
            themeMusic = MediaPlayer.create(this, R.raw.menu_music);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        themeMusic.stop();
        themeMusic.release();
    }
}

