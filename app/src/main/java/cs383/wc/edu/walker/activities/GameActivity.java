package cs383.wc.edu.walker.activities;

import android.support.v7.app.AppCompatActivity;

/**
 * @author David Windsor
 * Base Activity for our levels
 * Rather than using an interface and having to cast to the interface this allows us to just call methods we need all LevelActivities to do
 * And still have access to telling the activity to do things in AppCompatActivity
 */

public abstract class GameActivity extends AppCompatActivity {
    public abstract void playMedia(int resource);
    public abstract void promptLevelEnd(long score);
}
