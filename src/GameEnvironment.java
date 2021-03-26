import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * create and initialize a new gameEnvironment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     * @param c the given Collidable.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * removes the Collidable c from the collidables.
     * @param c the Collidable we want to remove.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory Line.
     * @return the CollisionInfo, if happened, else null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestCollision = null;
        Point collisionPoint = null;
        double minDistance = Double.POSITIVE_INFINITY;
        List<Collidable> collidablesClone = new ArrayList<Collidable>(collidables);
        for (Collidable c : collidablesClone) {
            Rectangle currentRec = c.getCollisionRectangle();
            Point intersectionPoint = trajectory.closestIntersectionToStartOfLine(currentRec);
            if (intersectionPoint != null) {
                if (intersectionPoint.distance(trajectory.start()) < minDistance) {
                    closestCollision = c;
                    minDistance = intersectionPoint.distance(trajectory.start());
                    collisionPoint = intersectionPoint;
                }
            }
        }
        if (closestCollision == null) {
            return null;
        } else {
            collisionPoint = new Point(Math.round(collisionPoint.getX()), Math.round(collisionPoint.getY()));
            return new CollisionInfo(collisionPoint, closestCollision);
        }
    }

//
//    public List<Collidable> getCollidables() {
//        return collidables;
//    }
}