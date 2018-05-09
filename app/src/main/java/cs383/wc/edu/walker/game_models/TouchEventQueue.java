package cs383.wc.edu.walker.game_models;

import android.view.MotionEvent;

import java.util.LinkedList;
@SuppressWarnings("All")

/**
 * Created by shaffer on 4/28/16.
 */
public class TouchEventQueue {

    private static TouchEventQueue defaultInstance;
    private LinkedList<MotionEvent> events;

    public TouchEventQueue() {
        events = new LinkedList<>();
    }

    public static TouchEventQueue getInstance() {
        if (defaultInstance == null)
            defaultInstance = new TouchEventQueue();
        return defaultInstance;
    }

    public synchronized void enqueue(MotionEvent e) {
        events.addLast(e);
    }

    public synchronized MotionEvent dequeue() {
        if (events.isEmpty())
            return null;
        else
            return events.removeFirst();
    }
}
