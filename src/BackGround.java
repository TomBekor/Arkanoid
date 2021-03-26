import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class BackGround implements Sprite {
    private List<Sprite> sprites;

    /**
     * creats and initialize a new Background.
     * @param color the color of the backGround.
     * @param backGroundSprites the Sprites of the background.
     */
    public BackGround(Color color, List<Sprite> backGroundSprites) {
        sprites = new ArrayList<Sprite>();
        sprites.add(new Block(new Point(0, 20), 800, 580, color));
        sprites.addAll(backGroundSprites);
    }

    @Override
    public void drawOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    @Override
    public void timePassed() {

    }
}
