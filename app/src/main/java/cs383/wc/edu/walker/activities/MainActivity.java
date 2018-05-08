package cs383.wc.edu.walker.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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

    Button levelOneButton;
    Button levelTwoButton;
    Button foreverButton;
    MediaPlayer themeMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        themeMusic = MediaPlayer.create(this, R.raw.menu_music);
        themeMusic.start();

        levelOneButton = findViewById(R.id.main_level_one_button);
        levelOneButton.setOnClickListener((View view) -> {
            themeMusic.pause();
            themeMusic.release();
            startActivity(new Intent(MainActivity.this, LevelOneActivity.class));
        });

        levelTwoButton = findViewById(R.id.main_level_two_button);
        levelTwoButton.setOnClickListener((View view) -> {
            themeMusic.pause();
            themeMusic.release();
            startActivity(new Intent(MainActivity.this, LevelTwoActivity.class));
        });

        foreverButton = findViewById(R.id.forever_level_button);
        foreverButton.setOnClickListener((View view) -> {
            themeMusic.pause();
            themeMusic.release();
            startActivity(new Intent(MainActivity.this, Forever.class));
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
}

