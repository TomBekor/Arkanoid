import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class FullBall implements Sprite {
    private Point center;
    private int radius;
    private Color color;

    /**
     * creates and initialize a new FullBall.
     * @param center the center of the ball.
     * @param radius the radius of the ball.
     * @param color the color of the ball.
     */
    public FullBall(Point center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillCircle((int) center.getX(), (int) center.getY(), radius);
    }

    @Override
    public void timePassed() {

    }
}
