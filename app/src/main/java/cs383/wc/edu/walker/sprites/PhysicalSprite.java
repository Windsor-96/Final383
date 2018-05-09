package cs383.wc.edu.walker.sprites;

import cs383.wc.edu.walker.game_models.Vec2d;
@SuppressWarnings("All")

/**
 * Created by shaffer on 4/28/16.
 * Edited by David Windsor and Alex Michels
 */
public abstract class PhysicalSprite extends Sprite {

    private Vec2d velocity;

    private Vec2d acceleration;

    PhysicalSprite(Vec2d v) {
        super(v);
        velocity = new Vec2d(0, 0);
        acceleration = new Vec2d(0, 0);
    }

    public Vec2d getVelocity() {
        return velocity;
    }

    public void setVelocity(Vec2d v) {
        velocity = v;
    }

    public Vec2d getAcceleration() {
        return acceleration;
    }

    void setAcceleration(Vec2d acceleration) {
        this.acceleration = acceleration;
    }

    public void tick(double dt) {
        super.tick(dt);
        position = position.add(velocity.times(dt)).add(acceleration.times(0.5 * dt * dt));
        velocity = velocity.add(acceleration.times(dt));
    }

}
