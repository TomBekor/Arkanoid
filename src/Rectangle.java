import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class Rectangle {
    /**
     * Point upperLeft- the upper left point of the rectangle.
     * double width- the width of the rectangle.
     * double height- the height of the rectangle.
     * List<Line> edges- list of lines who are the edges of the rectangle.
     */
    private  Point upperLeft;
    private  double width;
    private  double height;
    private  List<Line> edges;

    /**
     * Rectangle is a constructor of a new Rectangle object.
     * @param upperLeft the upper left point of the rectangle.
     * @param width double, the width of the rectangle.
     * @param height double, the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.edges = findEdges();
    }

    /**
     * Rectangle is a constructor of a new Rectangle object.
     * @param upperPointX double, the x coordinate of the upper left point of the rectangle.
     * @param upperPointY double, the y coordinate of the upper left point of the rectangle.
     * @param width double, the width of the rectangle.
     * @param height double, the height of the rectangle.
     */
    public Rectangle(double upperPointX, double upperPointY, double width, double height) {
        this.upperLeft = new Point(upperPointX, upperPointY);
        this.width = width;
        this.height = height;
        this.edges = findEdges();
    }

    /**
     * creates new lines from the rectangle edges, and returns
     * a list of them.
     * @return list of lines, the edges of the current rectangle.
     */
    private List<Line> findEdges() {
        edges = new ArrayList<Line>();
        Line topEdge = new Line(upperLeft.cloneAddWidthAndHeight(0, 0),
                                upperLeft.cloneAddWidthAndHeight(width, 0));
        Line bottomEdge = new Line(upperLeft.cloneAddWidthAndHeight(0, height),
                                upperLeft.cloneAddWidthAndHeight(width, height));
        Line rightEdge = new Line(upperLeft.cloneAddWidthAndHeight(0, 0),
                                upperLeft.cloneAddWidthAndHeight(0, height));
        Line leftEdge = new Line(upperLeft.cloneAddWidthAndHeight(width, 0),
                                upperLeft.cloneAddWidthAndHeight(width, height));
        edges.add(topEdge);
        edges.add(bottomEdge);
        edges.add(rightEdge);
        edges.add(leftEdge);
        return edges;
    }

    /**
     * returns the top edge of the rectangle.
     * @return Line, the top edge.
     */
    public Line getTopEdge() {
        return edges.get(0);
    }

    /**
     * returns the bottom edge of the rectangle.
     * @return Line, the bottom edge.
     */
    public Line getBottomEdge() {
        return edges.get(1);
    }

    /**
     * returns the right edge of the rectangle.
     * @return Line, the right edge.
     */
    public Line getRightEdge() {
        return edges.get(2);
    }

    /**
     * returns the left edge of the rectangle.
     * @return Line, the left edge.
     */
    public Line getLeftEdge() {
        return edges.get(3);
    }

    /**
     * returns a (possibly empty) List of intersection points
     * with the specified line.
     * @param line the line we are going to check.
     * @return list of the intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = null;
        for (int i = 0; i < 4; i++) {
            if (line.isIntersecting(edges.get(i))) {
                if (intersectionPoints == null) {
                    intersectionPoints = new ArrayList<Point>();
                }
                intersectionPoints.add(line.intersectionWith(edges.get(i)));
            }
        }
        return intersectionPoints;
    }

    /**
     * returns the width of the current rectangle.
     * @return double, the width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * returns the height of the current rectangle.
     * @return double, the height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * returns a defensive copy if the upper-left point of the rectangle.
     * @return Point, the defensive copy.
     */
    public Point getUpperLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY());
    }

    /**
     * checks if is in the X boundaries of the rectangle.
     * @param point the point we want to check its x coordinate.
     * @return boolean, true if in the boundaries, else false.
     */
    public boolean isInXBoundaries(Point point) {
        return point.getX() >= getUpperLeft().getX() && point.getX() <= getUpperLeft().getX() + getWidth();
    }

    /**
     * checks if is in the Y boundaries of the rectangle.
     * @param point the point we want to check its y coordinate.
     * @return boolean, true if in the boundaries, else false.
     */
    public boolean isInYBoundaries(Point point) {
        return point.getY() >= getUpperLeft().getY() && point.getY() <= getUpperLeft().getY() + getHeight();
    }

    /**
     * checks if a point is inside rectangle.
     * @param point the point we want to check.
     * @return boolean, true if in the boundaries, else false.
     */
    public boolean isInRectangle(Point point) {
        return isInXBoundaries(point) && isInYBoundaries(point);
    }

    /**
     * moves the rectangle in axis x pixels coordinates,
     * and updates its edges.
     * @param pixels int.
     */
    public void moveOnX(int pixels) {
        upperLeft.setX(upperLeft.getX() + pixels);
        this.edges = findEdges();
    }

    /**
     * set a new x value to the upperLeft point of the current rectangle.
     * @param newX the new X value.
     */
    public void setX(double newX) {
        upperLeft.setX(newX);
        this.edges = findEdges();
    }

    /**
     * the function toString is a overRide of the toString function in from Object.
     * @return String, the printed Rectangle.
     */
    public String toString() {
        return "Rectangle: " + upperLeft.toString();
    }
}