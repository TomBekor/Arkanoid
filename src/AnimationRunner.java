import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class AnimationRunner {
    private GUI gui;
    private static int framesPerSecond = 60;
    private Sleeper sleeper;
    private KeyboardSensor keyboardSensor;

    /**
     * creates and initialize a new AnimationRunner.
     */
    public AnimationRunner() {
        gui = new GUI("Arkanoid", 800, 600);
        sleeper = new Sleeper();
        keyboardSensor = gui.getKeyboardSensor();
    }

    /**
     * run the Animation.
     * @param animation the Animation whom should run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            } else {
                System.out.println("too slow: " + usedTime);
            }
        }
//        sleeper.sleepFor(1500);
//        gui.close();
    }

    /**
     * returns the Gui.
     * @return gui.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * returns the framesPerSecond.
     * @return the framesPerSecond.
     */
    public static int getFramesPerSecond() {
        return framesPerSecond;
    }

    /**
     * returns the KeyBoardSensor.
     * @return keyboard.
     */
    public KeyboardSensor getKeyboardSensor() {
        return keyboardSensor;
    }
}