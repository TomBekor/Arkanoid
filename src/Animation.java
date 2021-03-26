import biuoop.DrawSurface;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public interface Animation {
    /**
     * make one frame of the animation.
     * @param d the drawSurface the animation is played on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * tells when the animation should stop.
     * @return true if should stop, false else.
     */
    boolean shouldStop();
}