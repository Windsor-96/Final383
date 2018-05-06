package cs383.wc.edu.walker;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.SurfaceTexture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TextureView textureView;
    private Thread renderLoopThread;
    private Button levelOneButton;
    private Button levelTwoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);


        levelOneButton = findViewById(R.id.main_level_one_button);
        levelOneButton.setOnClickListener(new View.OnClickListener()
                                          {
                                              @Override
                                              public void onClick(View view)
                                              {

                                                  startActivity(new Intent(MainActivity.this, LevelOneActivity.class));
                                              }
                                          }
        );

        levelTwoButton = findViewById(R.id.main_level_two_button);
        levelTwoButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                    startActivity(new Intent(MainActivity.this, LevelTwoActivity.class));
                }
            }
        );
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

}
