package cs383.wc.edu.walker.game_models;

import android.hardware.SensorEvent;

import java.util.LinkedList;

/**
 * Queue for motion sensor events, similar to TouchEventQueue
 */
public class SensorEventQueue {

    private static SensorEventQueue defaultInstance;
    private LinkedList<SensorEvent> events;

    private SensorEventQueue() {
        events = new LinkedList<>();
    }

    public static SensorEventQueue getInstance() {
        if (defaultInstance == null)
            defaultInstance = new SensorEventQueue();
        return defaultInstance;
    }

    public synchronized void enqueue(SensorEvent e) {
        events.addLast(e);
    }

    synchronized SensorEvent dequeue() {
        if (events.isEmpty())
            return null;
        else
            return events.removeFirst();
    }
}