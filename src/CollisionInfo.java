/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * creates and initialize a new CollisionInfo object.
     * @param collisionPoint the collision point.
     * @param collisionObject the collision object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * the point at which the collision occurs.
     * @return the collision Point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     * @return the collision object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}