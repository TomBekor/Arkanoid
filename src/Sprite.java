import biuoop.DrawSurface;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d the DrawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}