
/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class Point {
    /**
     * double x is the x coordinate, and double y is the y coordinate.
     */
    private double x;
    private double y;

    /**
     * Point function is the constructor of a new Point object.
     * @param x double, the x coordinate of the new point.
     * @param y double, the y coordinate of the new point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the function addWidthAndHeight returns new point with the coordinates (x + width, y + height).
     * @param width double, the width that we are adding to the x coordinate.
     * @param height double, the height that we are adding to the y coordinate.
     * @return new Point with the new coordinates.
     */
    public Point cloneAddWidthAndHeight(double width, double height) {
        return new Point(x + width, y + height);
    }

    /**
     * pow2 function returns the duplicate of the inputted number with himself (num * num).
     * @param num the double that we want to get his multiplication with himself.
     * @return double number, the multiplication.
     */
    public static double pow2(double num) {
        return num * num;
    }

    /**
     * distance function returns the 2 norma of the current and other point.
     * @param other Point, which we want to know the distance to.
     * @return double, the distance.
     */
    public double distance(Point other) {
        return Math.sqrt(pow2(this.x - other.getX()) + pow2(this.y - other.getY()));
    }

    /**
     * equals function checks if two points are the same point (have the same coordinates).
     * @param other Point, which we want to check its equality to the current point.
     * @return boolean, true if equals, false else.
     */
    public boolean equals(Point other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * the function getX returns the value of the x coordinate of the current point.
     * @return double, the coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * the function getX returns the value of the x coordinate of the current point.
     * @return double, the coordinate.
     */
    public double getY() {
        return this.y;
    }

    /**
     * set new x coordinate.
     * @param newX double.
     */
    public void setX(double newX) {
        this.x = newX;
    }
//    public Point(BigDecimal bigX, BigDecimal bigY) {
//        this.x = bigX.doubleValue();
//        this.y = bigY.doubleValue();
//    }
//    public BigDecimal getBigDecimalX() {
//        return new BigDecimal(x);
//    }
//    public BigDecimal getBigDecimalY() {
//        return new BigDecimal(y);
//    }

    /**
     * the function toString is a overRide of the toString function in from Object.
     * @return String, the printed Point.
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
