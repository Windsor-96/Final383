package cs383.wc.edu.walker;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by shaffer on 4/28/16.
 */
public class TouchEventQueue {

    private static TouchEventQueue defaultInstance;

    public static TouchEventQueue getInstance() {
        if (defaultInstance == null)
            defaultInstance = new TouchEventQueue();
        return defaultInstance;
    }

    private LinkedList<MotionEvent> events;

    public TouchEventQueue() {
        events = new LinkedList<>();
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
