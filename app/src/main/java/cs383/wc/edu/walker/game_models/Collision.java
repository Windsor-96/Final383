package cs383.wc.edu.walker.game_models;

import android.graphics.RectF;

import cs383.wc.edu.walker.sprites.Sprite;

/**
 * Created by shaffer on 4/28/16.
 *
 */
public class Collision {
    private Sprite sprite1;
    private Sprite sprite2;

    /**
     * Construct a Collision such that sprite1 is equal to the active sprite from s1 and s2 (if one of them
     * is active) or either s1 or s2 otherwise.
     *
     * @param s1 first sprite
     * @param s2 second sprite
     */
    Collision(Sprite s1, Sprite s2) {
        if (s1.isActive()) {
            sprite1 = s1;
            sprite2 = s2;
        } else {
            sprite1 = s2;
            sprite2 = s1;
        }
    }

    void resolve() {
        sprite1.resolve(this, sprite2);
    }

    public RectF getCollisionRectangle() {
        return sprite1.intersectionWith(sprite2);
    }
}
