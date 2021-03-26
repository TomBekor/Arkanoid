import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * creates and initialize a new SpriteCollision.
     */
    public SpriteCollection() {
        sprites = new ArrayList<Sprite>();
    }

    /**
     * adds Sprite s to sprites.
     * @param s the Sprite we want to add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * removes Sprite s from the sprites collection.
     * @param s the Sprite we want to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesClone = new ArrayList<Sprite>(sprites);
        for (Sprite s : spritesClone) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d the drawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}