package cs383.wc.edu.walker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by shaffer on 4/27/16.
 */
public class BitmapSequence {
    private ArrayList<SequenceElement> bitmaps;
    private int current;
    private double timeShowingCurrent;

    public BitmapSequence() {
        bitmaps = new ArrayList<>();
        current = 0;
    }

    public void addImage(Bitmap bm, double duration) {
        bitmaps.add(new SequenceElement(bm, duration));
    }

    public void drawCurrent(Canvas c, float x, float y, @Nullable Paint p) {
        c.drawBitmap(getCurrent().bitmap, x, y, p);
    }

    private SequenceElement getCurrent() {
        return bitmaps.get(current);
    }

    public void nextBitmap() {
        current = (current + 1) % bitmaps.size();
        timeShowingCurrent = 0;
    }

    public void tick(double dt) {
        timeShowingCurrent += dt;
        if (timeShowingCurrent > getCurrent().duration)
            nextBitmap();
    }

    public PointF getSize() {
        return new PointF(getCurrent().bitmap.getWidth(), getCurrent().bitmap.getHeight());
    }
}

class SequenceElement {
    Bitmap bitmap;
    double duration;

    public SequenceElement(Bitmap bm, double duration) {
        bitmap = bm;
        this.duration = duration;
    }
}