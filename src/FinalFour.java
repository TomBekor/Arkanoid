import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class FinalFour implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * creates a new FinalFour levelInformation.
     */
    public FinalFour() {
        velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(-40, 6));
        velocities.add(Velocity.fromAngleAndSpeed(0, 6));
        velocities.add(Velocity.fromAngleAndSpeed(40, 6));
        numberOfBalls = velocities.size();
        paddleSpeed = 12;
        paddleWidth = 200;
        levelName = "Final Four";

        background = createClouds();

        int blockWeight = 76;
        int blockHeight = 20;
        blocks = new ArrayList<Block>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 10; j++) {
                blocks.add(new Block(new Point(20 + (j * blockWeight), 100 + (i * blockHeight)),
                        blockWeight, blockHeight, colorForRow(i)));
            }
        }
        numberOfBlocksToRemove = blocks.size();
    }

    /**
     * creates the clouds of the level.
     * @return the clouds background.
     */
    public BackGround createClouds() {
        List<Sprite> sprites = new ArrayList<Sprite>();
        sprites.addAll(createCloud(new Point(100, 400)));
        sprites.addAll(createCloud(new Point(680, 500)));

        return new BackGround(Color.CYAN, sprites);
    }

    /**
     * creates the clouds.
     * @param point the clouds point.
     * @return the cloud Sprites.
     */
    public List<Sprite> createCloud(Point point) {
        List<Sprite> sprites = new ArrayList<Sprite>();

        //rain:
        for (int i = 0; i < 8; i++) {
            sprites.add(new DecorLine(new Line(wind(point, 10 * i, 0),
                    new Point(point.getX() - 50 + 10 * i, 600)), Color.WHITE));
        }

        //cloud:
        sprites.add(new FullBall(wind(point, -5, 0), 24, Color.WHITE));
        sprites.add(new FullBall(wind(point, 18, 12), 25, Color.WHITE));
        sprites.add(new FullBall(wind(point, 28, -12), 24, Color.LIGHT_GRAY));
        sprites.add(new FullBall(wind(point, 36, 12), 23, Color.LIGHT_GRAY));
        sprites.add(new FullBall(wind(point, 56, 2), 27, Color.GRAY));

        return sprites;
    }

    /**
     * move a point.
     * @param point the point the wind blows away.
     * @param x move in x.
     * @param y move in y.
     * @return the new point.
     */
    public Point wind(Point point, double x, double y) {
        return  new Point(point.getX() + x, point.getY() + y);
    }

    /**
     * shoose color for row.
     * @param row the row.
     * @return the chosen color.
     */
    private static Color colorForRow(int row) {
//        Color[] colors = new Color[]{Color.BLUE};
        Color[] colors = new Color[]{Color.GRAY, Color.RED,
                Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.BLUE};
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
