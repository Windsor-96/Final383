package cs383.wc.edu.walker;

/**
 * Created by shaffer on 4/28/16.
 * Edited by David Windsor and Alex Michels
 */
public abstract class PhysicalSprite extends Sprite {

    private Vec2d velocity;

    private Vec2d acceleration;

    public PhysicalSprite(Vec2d v) {
        super(v);
        velocity = new Vec2d(0,0);
        acceleration = new Vec2d(0,0);
    }

    public void setVelocity(Vec2d v) {
        velocity = v;
    }

    public Vec2d getVelocity() {
        return velocity;
    }

    public Vec2d getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vec2d acceleration) {
        this.acceleration = acceleration;
    }

    public void tick(double dt) {
        super.tick(dt);
        position = position.add(velocity.times(dt)).add(acceleration.times(0.5*dt*dt));
        velocity = velocity.add(acceleration.times(dt));
    }

}
