import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private List<Point> corners;
    private Color color;
    private List<HitListener> hitListeners;


    /**
     * creates a new Block object and initialize it.
     * @param upperLeft Point, the center.
     * @param width double, the width.
     * @param height double, the height.
     */
    public Block(Point upperLeft, double width, double height) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.corners = findCorners();
        this.color = Color.BLUE;
        hitListeners = new ArrayList<HitListener>();
    }

    /**
     * creates a new Block object and initialize it.
     * @param upperLeft Point, the center.
     * @param width double, the width.
     * @param height double, the height.
     * @param color Color, the color of the ball.
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.corners = findCorners();
        this.color = color;
        hitListeners = new ArrayList<HitListener>();
    }

    /**
     * creates a new Block object and initialize it.
     * @param rectangle Rectangle, the block's rectangle.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = new Rectangle(rectangle.getUpperLeft(), rectangle.getWidth(), rectangle.getHeight());
        this.corners = findCorners();
        this.color = Color.BLACK;
        hitListeners = new ArrayList<HitListener>();
    }

    /**
     * returns the corners of the rectangle.
     * @return List of the corners(Points).
     */
    public List<Point> findCorners() {
        corners = new ArrayList<Point>();
        corners.add(rectangle.getUpperLeft().cloneAddWidthAndHeight(0, 0));
        corners.add(rectangle.getUpperLeft().cloneAddWidthAndHeight(rectangle.getWidth(), 0));
        corners.add(rectangle.getUpperLeft().cloneAddWidthAndHeight(0, rectangle.getHeight()));
        corners.add(rectangle.getUpperLeft().cloneAddWidthAndHeight(rectangle.getWidth(), rectangle.getHeight()));
        return corners;
    }

    /**
     * returns a clone of the upperLeft Point.
     * @return the upperLeft Point.
     */
    public Point getUpperLeft() {
        return new Point(rectangle.getUpperLeft().getX(), rectangle.getUpperLeft().getY());
    }

    /**
     * checks if the given point is a corner of the block.
     * @param point Point the given point.
     * @return boolean, true if the point is a corner, else false.
     */
    public boolean isCorner(Point point) {
        boolean isCorner = false;
        for (int i = 0; i < corners.size(); i++) {
            if (point.equals(corners.get(i))) {
                isCorner = true;
            }
        }
        return isCorner;
    }

    /**
     * checks if the given point is on the top or on the bottom of the block.
     * @param point Point the given point.
     * @return boolean, true if the point is on the top or on the bottom of the block, else false.
     */
    public boolean isOnTopOrBotEdge(Point point) {
        return point.getY() <= rectangle.getUpperLeft().getY() + 0.01
                || point.getY() >= rectangle.getUpperLeft().getY() + rectangle.getHeight() - 0.01;
    }
    /**
     * checks if the given point is on the right or on the left of the block.
     * @param point Point the given point.
     * @return boolean, true if the given point is on the right or on the left of the block, else false.
     */
    public boolean isOnRightOrLeftEdge(Point point) {
        return point.getX() <= rectangle.getUpperLeft().getX() + 0.01
                || point.getX() >= rectangle.getUpperLeft().getX() + rectangle.getWidth() - 0.01;
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = null;
        if (isCorner(collisionPoint)) {
            Velocity virtualVelocity = new Velocity(Velocity.velSign(currentVelocity.getDx()),
                    Velocity.velSign(currentVelocity.getDy()));
            Point virtualPoint = virtualVelocity.applyToPoint(collisionPoint);
            if (rectangle.isInXBoundaries(virtualPoint)) {
                newVelocity = new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
            }
            if (rectangle.isInYBoundaries(virtualPoint)) {
                newVelocity = new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            } else {
                newVelocity = new Velocity((-1) * currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            }
        } else {
            if (isOnRightOrLeftEdge(collisionPoint)) {
                newVelocity = new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
            } else {
                if (isOnTopOrBotEdge(collisionPoint)) {
                    newVelocity = new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
                }
            }
        }
        notifyHit(hitter);
        return newVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    @Override
    public void timePassed() { }

    /**
     * moves the block on axis X and updates its corners.
     * @param pixels int, pixels to move.
     */
    public void moveOnX(int pixels) {
        this.rectangle.moveOnX(pixels);
        this.corners = findCorners();
    }

    /**
     * sets the Block's x value.
     * @param newX the new x value.
     */
    public void setBlockX(double newX) {
        this.rectangle.setX(newX);
        this.corners = findCorners();
    }

    /**
     * function addToGame adds the block to Game game, as a Sprite and Collidable object.
     * @param game the Game.
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * removes the current block from game.
     * @param game the Game we want to remove the current block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * the function toString is a overRide of the toString function in from Object.
     * @return String, the printed block.
     */
    public String toString() {
        return "Block: " + rectangle.getUpperLeft().toString();
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     *  Notify all listeners about a hit event.
     * @param hitter the hitter Ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}