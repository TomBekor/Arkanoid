import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean shouldStop;
    private boolean isAlreadyPressed;

    /**
     * creates a new KeyPressStoppableAnimation.
     * @param sensor the keySensor.
     * @param key the key (ont the keyboard).
     * @param animation the Animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.shouldStop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!keyboard.isPressed(key)) {
            isAlreadyPressed = false;
        }
        if ((!isAlreadyPressed) && keyboard.isPressed(key)) {
            shouldStop = true;
        }
        animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return shouldStop;
    }
}