import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class Ball implements Sprite {
    /**
     * Point center- the center of the ball.
     * int radius= the radius of the ball.
     * java.awt.Color color- the color of the ball.
     * Velocity velocity- the velocity of the ball.
     */
    private  Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Ball function is a constructor of a new Ball object.
     * @param center Point, the center point.
     * @param r int, the radius.
     * @param color java.awt.Color, the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Ball function is a constructor of a new Ball object.
     * @param x double, the x coordinate of the ball center.
     * @param y double, the y coordinate of the ball center.
     * @param r int, the radius.
     * @param color java.awt.Color, the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * the function getX returns the x coordinate of the ball center.
     * @return int, the ball center x coordinate.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * the function getY returns the y coordinate of the ball center.
     * @return int, the ball center y coordinate.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * the function getCenter returns a defensive copy of the ball center.
     * @return Point, the defensive copy of the center.
     */
    public Point getCenter() {
        return new Point(getX(), getY());
    }

    /**
     * the function getSize returns the radius of the ball.
     * @return int, the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * the function getColor returns the color of the ball.
     * @return java.awt.Color, the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * the function outFromRightOrLeft checks if the ball is out of the screen from the
     * right and left sides.
     * @param right int, the right limit.
     * @param left int, the left limit.
     * @return boolean, true if out of the screen, false else.
     */
    public boolean outFromRightOrLeft(int right, int left) {
        return (this.getX() + this.radius) >= right || (this.getX() - this.radius) <= left;
    }

    /**
     * the function outFromUpOrDown checks if the ball is out of the screen from the
     * up and down sides.
     * @param up int, the right limit.
     * @param down int, the left limit.
     * @return boolean, true if out of the screen, false else.
     */
    public boolean outFromUpOrDown(int up, int down) {
        return (this.getY() + this.radius) >= up || (this.getY() - this.radius) <= down;
    }

    /**
     * ths function drawOn draw the ball on the given DrawSurface.
     * @param surface DrawSurface, the draw surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * the function setVelocity set the velocity of the current ball to be v.
     * @param v Velocity, the new velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * the function setVelocity set the velocity of the current ball to be
     * dx in axis x, and dy in axis y.
     * @param dx double, the speed of the ball in axis x.
     * @param dy double, the speed of the ball in axis y.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * the function getVelocity returns the current velocity of the ball.
     * @return Velocity, the current velocity.
     */
    public Velocity getVelocity() {
        return new Velocity(this.velocity.getDx(), this.velocity.getDy());
    }

    /**
     * calculate the speed of the ball and returns it.
     * @return the speed of the ball.
     */
    public double getSpeed() {
        return velocity.getSpeed();
    }

    /**
     * the function moveOneStep changes the center of the ball by walking one step using his velocity.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            Point newCenter = trajectory.end();
//            for (Collidable c : gameEnvironment.getCollidables()) {
//                if (c.getCollisionRectangle().isInRectangle(newCenter)) {
//                    gameEnvironment.getClosestCollision(trajectory);
//                }
//            }
            this.center = newCenter;
        } else {
            Point newCenter = getVelocity().almostApplyToPoint(this.center, radius, collisionInfo.collisionPoint());
//            if (collisionInfo.collisionObject().getCollisionRectangle().isInRectangle(newCenter)) {
//                getVelocity().almostApplyToPoint(this.center, radius, collisionInfo.collisionPoint());
//            }
            this.center = newCenter;
            Velocity newVelocity = collisionInfo.collisionObject().hit(this,
                    collisionInfo.collisionPoint(), this.velocity);
//            if(newVelocity==null){
//                CollisionInfo oldCollisionInfo = gameEnvironment.getClosestCollision(trajectory);
//                collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), this.velocity);
//            }
            this.velocity = newVelocity;
        }
//        System.out.println(velocity);
    }

    /**
     * the function moveOneStep is making one step, ensures that the ball is not going out of the bounds
     * that was given in the function arguments which are- up, down, right, left.
     * @param up int, the upper bound.
     * @param down int, the lower bound.
     * @param right int, the right bound.
     * @param left int, the left bound.
     */
    public void moveOneStep(int up, int down, int right, int left) {
        if (this.outFromUpOrDown(up, down)) {
            this.setVelocity(this.velocity.getDx(), (-1) * this.velocity.getDy());
        }
        if (this.outFromRightOrLeft(right, left)) {
            this.setVelocity((-1) * this.velocity.getDx(), this.velocity.getDy());
        }
        this.moveOneStep();
    }

    /**
     * the function returns the gameEnvironment of the ball.
     * @return GameEnvironment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * the function addToGame adds the ball to Game game, as a Sprite object.
     * @param game Game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        gameEnvironment = game.getEnvironment();
    }

    /**
     * the function removeFromGame removes the current ball from the game.
     * @param game the Game we want to remove the current ball from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        gameEnvironment = game.getEnvironment();
    }

    /**
     * the function toString is a overRide of the toString function in from Object.
     * @return String, the printed Ball.
     */
    public String toString() {
        return "(" + getX() + "," + getY() + ")";
    }
}