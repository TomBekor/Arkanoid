import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private long freezeTime;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private boolean justStarted;
    private Color color;

    /**
     * creates a countdown with a black numbers.
     * @param numOfSeconds number of seconds.
     * @param countFrom number to count from to 0.
     * @param gameScreen the gameScreen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this(numOfSeconds, countFrom, gameScreen, Color.BLACK);
    }

    /**
     * creates a countdown with a black numbers.
     * @param numOfSeconds number of seconds.
     * @param countFrom number to count from to 0.
     * @param gameScreen the gameScreen.
     * @param color the color of the digits.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Color color) {
        this.freezeTime = (long) ((numOfSeconds / countFrom) * 1000 - (1000 / AnimationRunner.getFramesPerSecond()));
        if (freezeTime < 0) {
            freezeTime = 0;
        }
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
        this.justStarted = true;
        this.color = color;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(color);
        d.drawText(d.getWidth() / 2 - 16, d.getHeight() / 2, Integer.toString(countFrom), 64);
        countFrom -= 1;
    }

    @Override
    public boolean shouldStop() {
        if (!justStarted) {
            sleeper.sleepFor(freezeTime);
        } else {
            justStarted = false;
        }
        if (countFrom == 0) {
            return true;
        }
        return false;
    }
}
