import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private Counter score;

    /**
     * creates and initialize a new GameFlow.
     * @param ar the AnimationRunner.
     * @param ks the KeyboardSensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.runner = ar;
        this.keyboard = ks;
        this.score = new Counter(0);
    }

    /**
     * run the levels of the gameFlow.
     * @param levels the levels.
     */
    public void runLevels(List<LevelInformation> levels) {

        boolean isWinner = true;

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, keyboard, runner, score);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.noMoreBalls()) {
                isWinner = false;
                break;
            }
        }
        if (levels.size() != 0) {
            Animation endScreen = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                    new EndScreen(isWinner, score.getValue()));
            runner.run(endScreen);
        }

        //bug: (not anymore :) )
//        Animation a1 = new EndScreen(true, 1000);
//        Animation a2 = new PauseScreen(); // also an Animation
//        Animation a1k = new KeyPressStoppableAnimation(keyboard, "m", a1);
//        Animation a2k = new KeyPressStoppableAnimation(keyboard, "m", a2);
//        runner.run(a1k);
//        runner.run(a2k);

        runner.getGui().close();
    }
}