import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block block;
    private int speed;
    private final double paddleHeight = 10;
    private double paddleWidth;
    private double partLength;
    private Color paddleColor;
    private final int leftBoundary = 20;
    private final int rightBoundary = 780;

    /**
     * create and initialize a new Paddle.
     * @param keyboard a keyBoard Sensor.
     */
    public Paddle(KeyboardSensor keyboard) { // 580
        paddleColor = Color.MAGENTA;
        speed = 12;
        paddleWidth = 300;
        this.block = new Block(new Point(400 - (paddleWidth / 2), 580 - paddleHeight), paddleWidth, paddleHeight);
        this.keyboard = keyboard;
        partLength = paddleWidth / 5;
    }

    /**
     * creates and initialize a new Paddle.
     * @param newPaddleSpeed the paddle speed.
     * @param newPaddleWidth the paddle width.
     * @param newPaddleColor the paddle color.
     * @param keyboard the paddle keyboard.
     */
    public Paddle(int newPaddleSpeed, int newPaddleWidth, Color newPaddleColor, KeyboardSensor keyboard) { // 580
        this.block = new Block(new Point(400 - (newPaddleWidth / 2), 580 - paddleHeight), newPaddleWidth, paddleHeight);
        this.keyboard = keyboard;
        partLength = newPaddleWidth / 5;
        speed = newPaddleSpeed;
        paddleWidth = newPaddleWidth;
        paddleColor = newPaddleColor;
    }

    /**
     * moves the puddle left in axis X in speed "speed".
     */
    public void moveLeft() {
        block.moveOnX(-speed);
    }

    /**
     * moves the puddle right in axis X in speed "speed".
     */
    public void moveRight() {
        this.block.moveOnX(speed);
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else {
            if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
                moveRight();
            }
        }
        if (block.getUpperLeft().getX() < leftBoundary) {
            block.setBlockX(leftBoundary);
        }
        if (block.getUpperLeft().getX() + paddleWidth > rightBoundary) {
            block.setBlockX(rightBoundary - paddleWidth);
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(paddleColor);
        d.fillRectangle((int) block.getCollisionRectangle().getUpperLeft().getX(),
                (int) block.getCollisionRectangle().getUpperLeft().getY(),
                (int) block.getCollisionRectangle().getWidth(),
                (int) block.getCollisionRectangle().getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) block.getCollisionRectangle().getUpperLeft().getX(),
                (int) block.getCollisionRectangle().getUpperLeft().getY(),
                (int) block.getCollisionRectangle().getWidth(),
                (int) block.getCollisionRectangle().getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int partNumber = partNumber(collisionPoint.getX());
        int impactAngle = findImpactAngle(partNumber);
        Velocity secureVelocity = block.hit(hitter, collisionPoint, currentVelocity);
        Velocity velocityAfterHit = block.hit(hitter, collisionPoint, currentVelocity);
        return velocityAfterHit.changeDegree(impactAngle);
    }

    /**
     * Add this paddle to the game.
     * @param game Game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * find the impact angle of the ball after hitting the paddle.
     * @param partNumber int.
     * @return the impact angle.
     */
    public int findImpactAngle(int partNumber) {
        return -60 + (30 * partNumber);
    }

    /**
     * finds the part of the paddle that collision happened on him.
     * @param x the x coordinate of the collision point
     * @return the part of the paddle.
     */
    public int partNumber(double x) {
        for (int i = 0; i <= 4; i++) {
            if (x >= upperLeftX() + i * partLength && x <= upperLeftX() + (i + 1) * partLength) {
                return i;
            }
        }
        return -1;
    }

    /**
     * returns the upper left point of the paddle.
     * @return the upper left Point.
     */
    public double upperLeftX() {
        return this.block.getCollisionRectangle().getUpperLeft().getX();
    }

    /**
     * sets the paddle color.
     * @param color the new color.
     */
    public void setPaddleColor(Color color) {
        this.paddleColor = color;
    }

    /**
     * the function toString is a overRide of the toString function in from Object.
     * @return String, the printed Paddle.
     */
    public String toString() {
        return "Paddle: " + block.toString() + " Collision Rectangle: " + getCollisionRectangle().toString();
    }
}