package cs383.wc.edu.walker.game_models;

import android.hardware.SensorEvent;
import android.view.MotionEvent;

import java.util.LinkedList;

/**
 * TODO DOCUMENTATION
 */
public class SensorEventQueue {

    private static SensorEventQueue defaultInstance;

    public static SensorEventQueue getInstance() {
        if (defaultInstance == null)
            defaultInstance = new SensorEventQueue();
        return defaultInstance;
    }

    private LinkedList<SensorEvent> events;

    public SensorEventQueue() {
        events = new LinkedList<>();
    }

    public synchronized void enqueue(SensorEvent e) {
        events.addLast(e);
    }

    public synchronized SensorEvent dequeue() {
        if (events.isEmpty())
            return null;
        else
            return events.removeFirst();
    }
}