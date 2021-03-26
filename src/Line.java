import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class Line {
    /**
     * Point start is the start of the current line and Point end is the end of it.
     */
    private Point start;
    private Point end;

    /**
     * Line function is a constructor of a new Line object.
     * @param start Point, the starting point of the new line.
     * @param end Point, the ending point of the new line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Line function is a constructor of a new Line object.
     * @param x1 double, the x coordinate of the starting point of the new line.
     * @param y1 double, the y coordinate of the starting point of the new line.
     * @param x2 double, the x coordinate of the ending point of the new line.
     * @param y2 double, the y coordinate of the ending point of the new line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * pow2 function returns the duplicate of the inputted number with himself (num * num).
     * @param num the double that we want to get his multiplication with himself.
     * @return double number, the multiplication.
     */
    private double pow2(double num) {
        return num * num;
    }

    /**
     * pow2 function returns the square root of num.
     * @param num the double that we want to get his square root.
     * @return double number, the square root of num.
     */
    private double sqrt(double num) {
        return Math.sqrt(num);
    }

    /**
     * length function returns the length of the current line.
     * @return double, the length of the line.
     */
    public double length() {
        return sqrt(pow2(start.getX() - end.getX()) + pow2(start.getY() - end.getY()));
    }


    /**
     * middle function returns the middle point of the current line.
     * @return Point, the middle of the line.
     */
    public Point middle() {
        double middleX = (start.getX() + end.getX()) / 2;
        double middleY = (start.getY() + end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * start function returns a defensive copy of the starting point of the current line.
     * @return Point, a copy of the starting point.
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * end function returns a defensive copy of the ending point of the current line.
     * @return Point, a copy of the ending point.
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * the function isVerticalToX checks if the current line is vertical to axis x.
     * @return boolean, true if it is vertical, false else.
     */
    private boolean isVerticalToX() {
        return this.incline() == Double.POSITIVE_INFINITY;
    }

    /**
     * incline function returns the incline of the current line.
     * @return double, the incline.
     */
    private double incline() {
        if (this.start.getX() == this.end.getX()) {
            return Double.POSITIVE_INFINITY;
        }
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * the function intersectionWithY returns the value of the intersection of the current line with axis y.
     * @return double, the intersection with axis y;
     */
    private double intersectionWithY() {
        if (!this.isVerticalToX()) {
            return (this.start.getY() * this.end.getX() - this.end.getY() * this.start.getX())
                    / (this.end.getX() - this.start.getX());
        }
        return Double.POSITIVE_INFINITY;
    }

    /**
     * isXLine function checks if x is in the current line limits.
     * @param x double, the number we want to check.
     * @return boolean, true if in ths limits, false else.
     */
    private boolean isXInLine(double x) {
        return Math.min(this.start.getX(), this.end.getX()) <= x + 0.001
                && Math.max(this.start.getX(), this.end.getX()) >= x - 0.001;
    }

    /**
     * the function isYInVerticalLine checks if value y is in the vertical line limits.
     * the current line in the function should be vertical to axis x.
     * @param y double, the number we want to check.
     * @return boolean, true if in ths limits, false else.
     */
    private boolean isYInVerticalLine(double y) {
        return Math.min(this.start.getY(), this.end.getY()) <= y + 0.01
                && Math.max(this.start.getY(), this.end.getY()) >= y - 0.01;
    }

    /**
     * the function calc calculates the value of a point on the current normal line (not vertical).
     * @param x double, the x coordinate of the point that we want to get its value.
     * @return double, the value at x.
     */
    private double clac(double x) {
        return this.incline() * x + this.intersectionWithY();
    }

    /**
     * the function isPoint checks if a line is a point, its happen when start equals end.
     * @return boolean, if the current line is a point returns true, else, false.
     */
    private boolean isPoint() {
        return this.start.equals(this.end);
    }

    /**
     * the function isIntersecting returns true if the lines intersect, false otherwise.
     * @param other the other line.
     * @return boolean, true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * the function intersectionWith returns the intersection point between two lines,
     * if they are not intersecting, returns null.
     * @param other the other Line.
     * @return Point, the intersection point, if does not exist, returns null.
     */
    public Point intersectionWith(Line other) {
        if (this.isPoint() && other.isPoint()) {
            return this.twoPoints(other);
        } else if ((!this.isPoint()) && other.isPoint()) {
            return this.isPointInLine(other);
        } else if (this.isPoint() && (!other.isPoint())) {
            return other.isPointInLine(this);
        } else if (this.incline() == other.incline()) {
            return this.intersectionOf2WithSameIncline(other);
        } else if ((!this.isVerticalToX()) && (!other.isVerticalToX())) {
            return this.intersectionOf2Normals(other);
        } else if ((!this.isVerticalToX()) && (other.isVerticalToX())) {
            return this.intersectionOfMixLines(other);
        } else if ((this.isVerticalToX()) && (!other.isVerticalToX())) {
            return other.intersectionOfMixLines(this);
        } else {
            return null;
        }
    }

    /**
     * the function twoPoints checks if two lines that are actually points are the same point,
     * which mean that that is their intersection point.
     * @param other the other line.
     * @return Point, the intersection point, if does not exist, returns null.
     */
    private Point twoPoints(Line other) {
        if (this.equals(other)) {
            return this.start();
        } else {
            return null;
        }
    }

    /**
     * the function isPointInLine checks if a "point" line exist on the current line.
     * (the current line is the normal line and the other line is the "point" line.
     * @param other the other line.
     * @return Point, the intersection point, if does not exist, returns null.
     */
    private Point isPointInLine(Line other) {
        if (this.isVerticalToX()) {
            if (this.isYInVerticalLine(other.start().getY()) && this.start.getX() == other.start().getX()) {
                return other.start();
            }
        } else {
            if (this.clac(other.start().getX()) == other.start().getY()) {
                return other.start();
            }
        }
        return null;
    }

    /**
     * the function intersectionOf2WithSameIncline finds intersection of 2 lines with the same incline,
     * if does not exist returns null.
     * @param other the other line.
     * @return Point, the intersection point, if does not exist, returns null.
     */
    private Point intersectionOf2WithSameIncline(Line other) {
        if (!(this.start.equals(other.start())
                || this.start.equals(other.end())
                || this.end.equals(other.start())
                || this.end.equals(other.end()))) {
            return null;
        } else {
            int intersectionCount = 0;
            Point intersectionPoint = null;
            if (this.start.equals(other.start())) {
                intersectionCount += 1;
                intersectionPoint = other.start();
                intersectionPoint = this.noOverLapping(intersectionPoint, this.end, other.end());
            }
            if (this.start.equals(other.end())) {
                intersectionCount += 1;
                intersectionPoint = other.end();
                intersectionPoint = this.noOverLapping(intersectionPoint, this.end, other.start());
            }
            if (this.end.equals(other.start())) {
                intersectionCount += 1;
                intersectionPoint = other.start();
                intersectionPoint = this.noOverLapping(intersectionPoint, this.start, other.end());
            }
            if (this.end.equals(other.end())) {
                intersectionCount += 1;
                intersectionPoint = other.end();
                intersectionPoint = this.noOverLapping(intersectionPoint, this.start, other.start());
            }
            if (intersectionCount != 1) {
                return null;
            }
            return intersectionPoint;
        }
    }

    /**
     * the function noOverLapping checks that there are no over lapping of 2 points or more in 2 lines.
     * @param intersectionPoint Point, the one point that surly over lapping.
     * @param p1 Point, other point, did'nt checked yed.
     * @param p2 Point, other point, did'nt checked yed.
     * @return Point, the intersection point, if does not exist, returns null.
     */
    private Point noOverLapping(Point intersectionPoint, Point p1, Point p2) {
        if (this.isVerticalToX()) {
            return noOverLappingVerticalLines(intersectionPoint, p1, p2);
        } else {
            return noOverLappingNormalLines(intersectionPoint, p1, p2);
        }
    }

    /**
     * the function noOverLappingNormalLines checks that there are no over lapping of 2 points or more in 2
     * normal lines.
     * @param intersectionPoint Point, the one point that surly over lapping.
     * @param p1 Point, other point, did'nt checked yed.
     * @param p2 Point, other point, did'nt checked yed.
     * @return Point, the intersection point, if does not exist, returns null.
     */
    private Point noOverLappingNormalLines(Point intersectionPoint, Point p1, Point p2) {
        if ((intersectionPoint.getX() <= p1.getX() && intersectionPoint.getX() <= p2.getX())
                || (intersectionPoint.getX() >= p1.getX() && intersectionPoint.getX() >= p2.getX())) {
            return null;
        }
        return intersectionPoint;
    }

    /**
     * the function noOverLappingVerticalLines checks that there are no over lapping of 2 points or more in 2
     * vertical lines (vertical  to axis x).
     * @param intersectionPoint Point, the one point that surly over lapping.
     * @param p1 Point, other point, did'nt checked yed.
     * @param p2 Point, other point, did'nt checked yed.
     * @return Point, the intersection point, if does not exist, returns null.
     */
    // checks that there are no over lapping in two vertical lines
    private Point noOverLappingVerticalLines(Point intersectionPoint, Point p1, Point p2) {
        if ((intersectionPoint.getY() <= p1.getY() && intersectionPoint.getY() <= p2.getY())
                || (intersectionPoint.getY() >= p1.getY() && intersectionPoint.getY() >= p2.getY())) {
            return null;
        }
        return intersectionPoint;
    }

    /**
     * the function intersectionOf2Normals finds intersection of 2 normal lines.
     * if does not exist returns null.
     * @param other the other line.
     * @return Point, the intersection point, if does not exist, returns null.
     */
    private Point intersectionOf2Normals(Line other) {
        double intersectionX = (other.intersectionWithY() - this.intersectionWithY())
                / (this.incline() - other.incline());
        if (this.isXInLine(intersectionX) && other.isXInLine(intersectionX)) {
            return new Point(intersectionX, this.incline() * intersectionX + this.intersectionWithY());
        }
        return null;
    }

    /**
     * the function intersectionOfMixLines finds intersection of 2 lines- one normal and one vertical.
     * if does not exist returns null.
     * @param other the other line.
     * @return Point, the intersection point, if does not exist, returns null.
     */
    // intersection of 2 lines- one normal and the other one vertical to axis x.
    private Point intersectionOfMixLines(Line other) {
        double intersectionX = other.start().getX();
        if (this.isXInLine(intersectionX)) {
            double intersectionY = this.clac(intersectionX);
            if (other.isYInVerticalLine(intersectionY)) {
                return new Point(intersectionX, intersectionY);
            }
        }
        return null;
    }

    /**
     * the function equals checks if two lines are equals,
     * which mean that the start and the end points are equal.
     * @param other the other line.
     * @return boolean, true if equals, false else.
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start()) && this.end.equals(other.end());
    }

    /**
     * If the current line does not intersect with the rectangle, return null,
     * else, the function  returns the return the closest intersection point to the
     * start of the line.
     * @param rect Rectangle.
     * @return Point, the closest the the start point of the line, or null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints == null) {
            return null;
        }
        int closestPointIndex = 0;
        double minDistance = start.distance(intersectionPoints.get(0));
        for (int i = 0; i < intersectionPoints.size(); i++) {
            if (start.distance(intersectionPoints.get(i)) < minDistance) {
                minDistance = start.distance(intersectionPoints.get(i));
                closestPointIndex = i;
            }
        }
        return new Point(intersectionPoints.get(closestPointIndex).getX(),
                intersectionPoints.get(closestPointIndex).getY());

    }

    /**
     * the function toString is a overRide of the toString function in from Object.
     * @return String, the printed Line.
     */
    public String toString() {
        return "Line: start= " + start.toString() + " end= " + end.toString();
    }

    /**
     * draw the line on a given drawSurface.
     * @param drawSurface the drawSurface.
     * @param color the Color of the line.
     */
    public void drawLine(DrawSurface drawSurface, Color color) {
        drawSurface.setColor(color);
        drawSurface.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }
}