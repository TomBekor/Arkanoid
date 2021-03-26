import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class DirectHit implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * creates a new DirectHit levelInformation.
     */
    public DirectHit() {
        numberOfBalls = 1;
        velocities = new ArrayList<Velocity>();
        velocities.add(new Velocity(0, -6));
        paddleSpeed = 12;
        paddleWidth = 150;
        levelName = "Direct Hit";
        List<Sprite> backgroundSprites = new ArrayList<Sprite>();

        backgroundSprites.add(new EmptyBall(new Point(400, 200), 60, Color.BLUE));
        backgroundSprites.add(new EmptyBall(new Point(400, 200), 80, Color.BLUE));
        backgroundSprites.add(new EmptyBall(new Point(400, 200), 100, Color.BLUE));

        backgroundSprites.add(new DecorLine(new Line(280, 200, 370, 200), Color.BLUE));
        backgroundSprites.add(new DecorLine(new Line(430, 200, 520, 200), Color.BLUE));
        backgroundSprites.add(new DecorLine(new Line(400, 230, 400, 320), Color.BLUE));
        backgroundSprites.add(new DecorLine(new Line(400, 170, 400, 80), Color.BLUE));

        background = new BackGround(Color.BLACK, backgroundSprites);

        blocks = new ArrayList<Block>();
        blocks.add(new Block(new Point(380, 180), 40, 40, Color.RED));

        numberOfBlocksToRemove = 1;
    }

    @Override
    public int numberOfBalls() {
        return velocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }
}
