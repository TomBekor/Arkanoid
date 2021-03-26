import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class Green3 implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * creates a new Green3 levelInformation.
     */
    public Green3() {
        velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(-40, 6));
        velocities.add(Velocity.fromAngleAndSpeed(40, 6));
        numberOfBalls = velocities.size();
        paddleSpeed = 12;
        paddleWidth = 150;
        levelName = "Green 3";

        background = createBuilding();

        int blockWidth = 60;
        int blockHeight = 20;
        blocks = new ArrayList<Block>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9 - i; j++) {
                blocks.add(new Block(new Point(780 - (j + 1) * blockWidth, 150 + i * blockHeight),
                        blockWidth, blockHeight, colorForRow(i)));
            }
        }
        numberOfBlocksToRemove = blocks.size();
    }

    /**
     * creates the building background.
     * @return the background.
     */
    public BackGround createBuilding() {
        List<Sprite> sprites = new ArrayList<Sprite>();

        //base:
        sprites.add(new Block(new Point(65, 200), 10, 400, Color.DARK_GRAY));
        sprites.add(new Block(new Point(60, 300), 20, 300, Color.DARK_GRAY));
        sprites.add(new Block(new Point(40, 350), 60, 250, Color.DARK_GRAY));

        //white:
        sprites.add(new Block(new Point(50, 360), 40, 240, Color.WHITE));

        //windows:

        sprites.add(new Block(new Point(60, 350), 5, 250, Color.DARK_GRAY));
        sprites.add(new Block(new Point(75, 350), 5, 250, Color.DARK_GRAY));

        sprites.add(new Block(new Point(40, 420), 60, 10, Color.DARK_GRAY));
        sprites.add(new Block(new Point(40, 420), 60, 10, Color.DARK_GRAY));
        sprites.add(new Block(new Point(40, 490), 60, 10, Color.DARK_GRAY));
        sprites.add(new Block(new Point(40, 560), 60, 10, Color.DARK_GRAY));

        //light:
        sprites.add(new Ball(70, 200, 13, Color.ORANGE));
        sprites.add(new Ball(70, 200, 9, Color.RED));
        sprites.add(new Ball(70, 200, 5, Color.WHITE));

        return new BackGround(Color.GREEN, sprites);
    }

    /**
     * returns the color for each row.
     * @param row the row number.
     * @return the Color of the row.
     */
    private static Color colorForRow(int row) {
//        Color[] colors = new Color[]{Color.BLUE};
        Color[] colors = new Color[]{Color.BLUE, Color.CYAN,
                Color.YELLOW, Color.RED, Color.MAGENTA, Color.ORANGE};
        return colors[row % colors.length];
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
