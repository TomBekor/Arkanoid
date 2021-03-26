import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class GameLevel implements Animation {
    private LevelInformation levelInformation;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private final int blockHeight = 20;
    private int blockWidth;
    private Counter remainedBlocks;
    private Counter remainedBalls;
    private Counter score;

    /**
     * creates a new GameLevel.
     * @param levelInformation the level information.
     * @param keyboard the keyboard sensor.
     * @param runner the animation runner.
     * @param score the score counter.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard,
                     AnimationRunner runner, Counter score) {
        this.levelInformation = levelInformation;
        this.keyboard = keyboard;
        this.runner = runner;
        this.score = score;
    }

    /**
     * return the game Environment of the game.
     *
     * @return GameEnvironment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Initialize a new game: create the Blocks the Balls and Paddle,
     * and add them to the game.
     */
    public void initialize() {
        running = true;
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        paddle = new Paddle(levelInformation.paddleSpeed(), levelInformation.paddleWidth(), Color.YELLOW, keyboard);
        remainedBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        remainedBalls = new Counter(levelInformation.numberOfBalls());
        addSprite(levelInformation.getBackground());
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, levelInformation.levelName());
        scoreIndicator.addToGame(this);
        paddle.addToGame(this);
        List<Ball> balls = new LinkedList<Ball>();



        if (levelInformation.levelName().equals("Direct Hit")) {
            Ball directBall = new Ball(new Point(400, 500), 5, Color.WHITE);
            balls.add(directBall);
        } else if (levelInformation.levelName().equals("Wide Easy")) {
            paddle.setPaddleColor(Color.BLUE);
            for (int i = 0; i < 5; i++) {
                balls.add(new Ball(200 + (i * 30), 400 - (i * 20), 5, Color.WHITE));
                balls.add(new Ball(600 - (i * 30), 400 - (i * 20), 5, Color.WHITE));
            }
        } else if (levelInformation.levelName().equals("Green 3")) {
            paddle.setPaddleColor(Color.BLACK);
            balls.add(new Ball(400, 400, 5, Color.WHITE));
            balls.add(new Ball(600, 400, 5, Color.WHITE));
        } else if (levelInformation.levelName().equals("Final Four")) {
            paddle.setPaddleColor(Color.YELLOW);
            balls.add(new Ball(400, 350, 5, Color.WHITE));
            balls.add(new Ball(500, 400, 5, Color.WHITE));
            balls.add(new Ball(600, 350, 5, Color.WHITE));
        }





        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).setVelocity(levelInformation.initialBallVelocities().get(i));
            balls.get(i).addToGame(this);
        }

        List<Block> boundaries = new ArrayList<Block>();
        List<Block> blocks = new ArrayList<Block>();
        boundaries.add(new Block(new Point(0, 20), 800, 20, Color.GRAY));
        boundaries.add(new Block(new Point(0, 40), 20, 580, Color.GRAY));
        boundaries.add(new Block(new Point(780, 40), 20, 580, Color.GRAY));
//        boundaries.add(new Block(new Point(20, 580), 760, 20, Color.GRAY));
        Block deathZone = new Block(new Point(20, 595), 760, 20, Color.ORANGE);

        blocks.addAll(levelInformation.blocks());


//        Block summonerBlock = new Block(new Point(40, 50), blockWidth, blockHeight, Color.GREEN);
//        Block bombBlock = new Block(new Point(400, 300), 10, 10, Color.RED);
//
//        ----------------------------------------------------------------------------------------------
//        LEVEL #1
//
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 12 - i; j++) {
//                blocks.add(new Block(new Point(780 - (j + 1) * blockWidth, 150 + i * blockHeight),
//                        blockWidth, blockHeight, colorForRow(i)));
//            }
//        }
//        ----------------------------------------------------------------------------------------------
//
//        LEVEL #2
//        for (int i = 0; i < 7; i++) {
//            for (int j = 0; j < 12 - i; j++) {
//                blocks.add(new Block(new Point(780 - (j + 1) * blockWidth, 150 + i * blockHeight),
//                        blockWidth, blockHeight, colorForRow(i)));
//            }
//        }
//        ----------------------------------------------------------------------------------------------
//        BIG BLOCK:
//
//        blocks.add(new Block(new Point (300, 100), 300, 20, colorForRow(0)));
//
//
//


        HitListener blockRemover = new BlockRemover(this, remainedBlocks);
        HitListener ballRemover = new BallRemover(this, remainedBalls);
//        HitListener ballSummoner = new BallSummoner(this, remainedBalls);
//        HitListener bombSummoner = new BombSummoner(this, remainedBalls, 10);
        HitListener currentScore = new ScoreTrackingListener(score);
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(currentScore);
        }
        for (Block boundary : boundaries) {
            boundary.addToGame(this);
        }
        deathZone.addToGame(this);
        deathZone.addHitListener(ballRemover);
//        summonerBlock.addToGame(this);
//        summonerBlock.addHitListener(ballSummoner);
//        bombBlock.addToGame(this);
//        bombBlock.addHitListener(bombSummoner);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        Color textColor = Color.BLACK;
        if (levelInformation.levelName().equals("Direct Hit")) {
            textColor = Color.WHITE;
        }
        runner.run(new CountdownAnimation(2, 3, sprites, textColor));
        runner.run(this);
    }

    /**
     * returns some color for every row.
     *
     * @param row the current row.
     * @return the current row color.
     */
    private static java.awt.Color colorForRow(int row) {
//        java.awt.Color[] colors = new java.awt.Color[]{Color.BLUE};
        java.awt.Color[] colors = new java.awt.Color[]{Color.BLUE, Color.CYAN, Color.GREEN,
                Color.YELLOW, Color.RED, Color.MAGENTA, Color.ORANGE};
        return colors[row % colors.length];
    }

//    /**
//     * returns the balls speed.
//     * @return the balls speed.
//     */
//    public double getBallsSpeed() {
//        return ballsSpeed;
//    }

    /**
     * add new Collidable object to the collidables.
     *
     * @param c the Collidable object we want to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * add new Sprint object to sprites.
     *
     * @param s the Sprite object we want to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * removes Collidable c from the current game.
     * @param c the Collidable we want to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * removes Sprite s from the current game.
     * @param s the Sprite we want to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        if (keyboard.isPressed("p")) {
            Animation pauseScreen = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen());
            runner.run(pauseScreen);
        }

        if (remainedBlocks.getValue() == 0) {
            score.increase(100);
            running = false;
        }

        if (remainedBalls.getValue() == 0) {
            running = false;
        }

        sprites.drawAllOn(d);
        sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !running;
    }

    /**
     * checks if there are existing balls on the screen.
     * @return true if there are no more balls, false else.
     */
    public boolean noMoreBalls() {
        return remainedBalls.getValue() == 0;
    }

    @Override
    public String toString() {
        return levelInformation.levelName();
    }
}