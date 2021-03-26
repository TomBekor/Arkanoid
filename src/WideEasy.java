import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class WideEasy implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * creates a new WideEasy levelInformation.
     */
    public WideEasy() {

        velocities = new ArrayList<Velocity>();
        for (int i = 0; i < 5; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(-45 + (7 * i), 7));
            velocities.add(Velocity.fromAngleAndSpeed(45 - (7 * i), 7));
        }

        numberOfBalls = velocities.size();

        paddleSpeed = 12;
        paddleWidth = 600;

        levelName = "Wide Easy";

        List<Sprite> backgroundSprites = new ArrayList<Sprite>();

        Point sunsCenter = new Point(90, 100);
        int linesNumber = 100;
        int blocksHeight = 200;

        for (int i = 0; i < linesNumber; i++) {
            backgroundSprites.add(new DecorLine(
                    new Line(sunsCenter, new Point(20 + ((760 / linesNumber) * i), blocksHeight)), Color.YELLOW));
        }

        backgroundSprites.add(new FullBall(sunsCenter, 50, Color.PINK));
        backgroundSprites.add(new FullBall(sunsCenter, 45, Color.ORANGE));
        backgroundSprites.add(new FullBall(sunsCenter, 40, Color.YELLOW));


        background = new BackGround(Color.WHITE, backgroundSprites);

        blocks = new ArrayList<Block>();
        numberOfBlocksToRemove = 14;
        int blockWidth = 760 / numberOfBlocksToRemove;

        for (int i = 0; i < numberOfBlocksToRemove; i++) {
            blocks.add(new Block(new Point(20 + i * blockWidth, blocksHeight), blockWidth, 20, blockColor(i)));
        }
    }

    /**
     * returns the color for each row.
     * @param row the row number.
     * @return the Color of the row.
     */
    private Color blockColor(int row) {
        Color[] colors = new Color[]{Color.RED, Color.PINK, Color.ORANGE, Color.YELLOW, Color.GREEN,
                Color.BLUE, Color.CYAN};
        return colors[((int) Math.ceil(row / 2)) % colors.length];
    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
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
