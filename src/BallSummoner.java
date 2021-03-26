import java.awt.Color;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class BallSummoner implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    private int ballsCreated;

    /**
     * creates and initialize a new BallSummoner HitListener.
     * @param game the Game of the Block.
     * @param remainingBalls the counter of the remaining blocks.
     */
    public BallSummoner(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
        this.ballsCreated = 0;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        ballsCreated += 1;
        Ball summonedBall = new Ball(hitter.getCenter(), hitter.getSize(), Color.GREEN);
        summonedBall.setVelocity(Velocity.fromAngleAndSpeed(180, hitter.getSpeed()));
        summonedBall.addToGame(game);
        remainingBalls.increase(1);
        if (ballsCreated == 3) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
        }
    }
}
