import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class DecorLine implements Sprite {
    private Line line;
    private Color color;

    /**
     * creates and initialize a new DecorLine.
     * @param line the line.
     * @param color the color of the line.
     */
    public DecorLine(Line line, Color color) {
        this.line = line;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        line.drawLine(d, color);
    }

    @Override
    public void timePassed() {

    }
}
