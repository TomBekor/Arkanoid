/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * creates and initialize a new BallRemover HitListener.
     * @param game the Game of the ball.
     * @param remainingBalls the remainingBalls counter.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(game);
    }
}
