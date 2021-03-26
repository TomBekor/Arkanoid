import biuoop.DrawSurface;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
