import biuoop.DrawSurface;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class EndScreen implements Animation {
    private boolean isWinner;
    private int score;

    /**
     * creates and initialize a new EndScreen Animation.
     * @param isWinner is winner or not EndScreen.
     * @param score the final score.
     */
    public EndScreen(boolean isWinner, int score) {
        this.isWinner = isWinner;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (isWinner) {
            d.drawText(170, d.getHeight() / 2, "You Win! Your score is " + Integer.toString(score), 32);
        } else {
            d.drawText(150, d.getHeight() / 2, "Game Over. Your score is " + Integer.toString(score), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
