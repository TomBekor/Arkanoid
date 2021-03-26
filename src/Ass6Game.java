import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class Ass6Game {
    /**
     * the function main creates a new game object, initialize it and run it.
     * @param args is an array of Strings from the command line arguments.
     */
    public static void main(String[] args) {
//        args = new String[]{"a", "1", "2"};
        AnimationRunner runner = new AnimationRunner();
        KeyboardSensor keyboardSensor = runner.getKeyboardSensor();
        List<LevelInformation> options = new ArrayList<LevelInformation>();
        options.add(new DirectHit());
        options.add(new WideEasy());
        options.add(new Green3());
        options.add(new FinalFour());
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        GameFlow gameFlow = new GameFlow(runner, keyboardSensor);
        for (String input : args) {
            if (stringIsDigit(input)) {
                int currentNumber = Integer.parseInt(input);
                if (currentNumber == 1) {
                    levels.add(new DirectHit());
                } else if (currentNumber == 2) {
                    levels.add(new WideEasy());
                } else if (currentNumber == 3) {
                    levels.add(new Green3());
                } else if (currentNumber == 4) {
                    levels.add(new FinalFour());
                }
            }
        }
        if (levels.size() != 0) {
            gameFlow.runLevels(levels);
        } else {
            gameFlow.runLevels(options);
        }
    }

    /**
     * checks if some String is a decimal value.
     * @param str the String.
     * @return true if decimal, false else.
     */
    public static boolean stringIsDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
}
