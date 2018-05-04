package cs383.wc.edu.walker.sprites;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import cs383.wc.edu.walker.bitmaps.BitmapSequence;
import cs383.wc.edu.walker.game_models.Collision;
import cs383.wc.edu.walker.game_models.Vec2d;

/**
 * Created by shaffer on 4/27/16.
 * Edited by David Windsor and Alex Michels
 */
public abstract class Sprite {

    private BitmapSequence bitmaps;
    Vec2d position;


    public Sprite(Vec2d v) {
        setPosition(v);
    }

    public void setBitmaps(BitmapSequence b) {
        bitmaps = b;
    }

    public void setPosition(Vec2d p) {
        position = p;
    }

    public Vec2d getPosition() {
        return position;
    }

    public void draw(Canvas c) {
        bitmaps.drawCurrent(c, position.getX(), position.getY(), null);
    }

    public void tick(double dt) {
        bitmaps.tick(dt);
    }

    public RectF getBoundingBox() {
        PointF size = bitmaps.getSize();
        return new RectF(getPosition().getX(),getPosition().getY(),getPosition().getX()+size.x,getPosition().getY()+size.y);
    }

    public boolean collidesWith(Sprite other) {
        return intersectionWith(other) != null;
    }

    /**
     * Return null if I don't intersect with other, otherwise return the overlap of our two bounding boxes.
     *
     * @param other The other sprite we are checking for a collision with
     * @return The box that is intersecting
     */
    public RectF intersectionWith(Sprite other) {
        RectF r = new RectF(getBoundingBox());
        if (!r.intersect(other.getBoundingBox()))
            return null;
        else
            return r;
    }

    public abstract boolean isActive();

    public void resolve(Collision collision, Sprite other) {

    }


}
