package cs383.wc.edu.walker;

import android.graphics.PointF;

/**
 * Created by shaffer on 4/28/16.
 */
public class Vec2d {
    private PointF coordinates;

    public Vec2d(double x, double y) {
        this((float)x, (float)y);
    }

    public Vec2d(float x, float y) {
        coordinates = new PointF(x, y);
    }

    public Vec2d add(Vec2d other) {
        return new Vec2d(getX()+other.getX(),getY()+other.getY());
    }

    public float getX() { return coordinates.x; }
    public float getY() { return coordinates.y; }

    public Vec2d times(double d) {
        return new Vec2d((float)d*coordinates.x, (float)d*coordinates.y);
    }

    @Override
    public String toString() {
        return "("+getX()+","+getY()+")";
    }
}
