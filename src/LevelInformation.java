import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public interface LevelInformation {
    /**
     * returns the number of the balls.
     * @return the balls number.
     */
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()

    /**
     * returns the initial ball velocities.
     * @return the initial ball velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * returns the paddle speed.
     * @return the puddle speed.
     */
    int paddleSpeed();

    /**
     * returns the paddle width.
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * returns the level name.
     * @return the level name.
     */
    // the level name will be displayed at the top of the screen.
    String levelName();

    /**
     * returns the background.
     * @return the background.
     */
    // Returns a sprite with the background of the level
    Sprite getBackground();

    /**
     * returns the blocks of the level.
     * @return the blocks of the level.
     */
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();

    /**
     * returns the number ob blocks to remove.
     * @return the number ob blocks to remove.
     */
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}