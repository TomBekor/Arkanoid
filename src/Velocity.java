/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class Velocity {
    /**
     * double dx- the change in axis x.
     * double dy- the change in axis y.
     */
    private double dx;
    private  double dy;

    /**
     * Velocity function is a constructor of a new Velocity object.
     * @param dx int, the change in axis x.
     * @param dy int, the change in axis y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * the function Velocity creates a new velocity from the angle of the speed and its size.
     * @param angle double, the angle of the velocity, when 0 degrees is up, clockwise.
     * @param speed double, the size of  the velocity.
     * @return Velocity, a new velocity from the angle and the speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = angle % 360;
        double dx = speed * Math.sin(angle * Math.PI / 180);
        double dy = speed * Math.cos(angle * Math.PI / 180);;
        if (0 < angle && angle < 180) {
            dx = Math.abs(dx);
        } else {
            dx = (-1) * Math.abs(dx);
        }
        if (90 < angle && angle <= 270) {
            dy = Math.abs(dy);
        } else {
            dy = (-1) * Math.abs(dy);
        }
        return new Velocity(dx, dy);
    }

    /**
     * the function applyToPoint takes a point with position (x,y) and returns a new point
     * with position (x+dx, y+dy).
     * @param p Point, the point that we are going to change.
     * @return Point, the new point after the change.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * takes the point p to almost the point "almostTarget".
     * @param p Point p.
     * @param radius the radius of the ball.
     * @param almostTarget the almostTarget Point.
     * @return new Point, almost almostTarget.
     */
    public Point almostApplyToPoint(Point p, double radius, Point almostTarget) {
        double newX = almostTarget.getX() - velSign(dx);
        double newY = almostTarget.getY() - velSign(dy);
        return new Point(newX, newY);
//        double newDx = velSign(dx) * (Math.abs(p.getX() - almostTarget.getX())) - velSign(dx) * 1;
//        double newDy = velSign(dy) * (Math.abs(p.getY() - almostTarget.getY())) - velSign(dy) * 1;
//        return new Point(Math.round(p.getX() + newDx), Math.round(p.getY() + newDy));
    }

    /**
     * finds the velocity sign.
     * @param speed the velocity speed (dx or dy).
     * @return int: -1 if negative, 1 if positive and 0 if 0.
     */
    public static int velSign(double speed) {
        if (speed < 0) {
            return -1;
        } else if (speed > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * chenges the degree of a velocity by the rules of "More fun paddle" from ass 3.
     * @param degree the degree we want to apply.
     * @return a new Velocity created with degree, while kipping her size equal..
     */
    public Velocity changeDegree(int degree) {
        return fromAngleAndSpeed(degree, Math.sqrt(dx * dx + dy * dy));
    }

    /**
     * the function getDx returns the change in axis x of the current velocity.
     * @return double, the change in axis x.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * the function getDy returns the change in axis Y of the current velocity.
     * @return double, the change in axis y.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * returns the speed of the velocity.
     * @return the speed.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }
    /**
     * the function toString is a overRide of the toString function in from Object.
     * @return String, the printed Velocity.
     */
    public String toString() {
        return "Velocity: (" + dx + ", " + dy + ")";
    }
}