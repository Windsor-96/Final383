package cs383.wc.edu.walker.sprites;

import cs383.wc.edu.walker.game_models.Vec2d;

/**
 * BoostSprite is really nice because all it does is sit there and double points when you collide.
 * Nothing needs done in here, the player will check to see if it hit a boost and if it did, tell the world to remove it
 * And then double its own points
 */

public class BoostSprite extends Sprite {
    public BoostSprite(Vec2d v) {
        super(v);
    }

    @Override
    public boolean isActive() {
        return false;
    }

}
