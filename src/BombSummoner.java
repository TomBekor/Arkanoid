//import java.awt.Color;
//
///**
// * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
// * @version 13.0.2
// * @since 2020-05-31
// */
//public class BombSummoner implements HitListener {
//    private Game game;
//    private Counter remainingBalls;
//    private int numberOfExplodingBalls;
//
//    /**
//     * creates and initialize a new BombSummoner HitListener.
//     * @param game the Game of the bomb.
//     * @param remainingBalls the remaining balls counter.
//     * @param numberOfExplodingBalls the number of the balls that will be created from the bomb.
//     */
//    public BombSummoner(Game game, Counter remainingBalls, int numberOfExplodingBalls) {
//        this.game = game;
//        this.remainingBalls = remainingBalls;
//        this.numberOfExplodingBalls = numberOfExplodingBalls;
//    }
//
//    @Override
//    public void hitEvent(Block beingHit, Ball hitter) {
//        beingHit.removeFromGame(game);
//        for (int i = 0; i < numberOfExplodingBalls; i++) {
//            Ball summonedBombBall = new Ball(hitter.getCenter(), hitter.getSize(), Color.RED);
//            summonedBombBall.setVelocity(Velocity.fromAngleAndSpeed((360 / numberOfExplodingBalls) * i + 5,
//                    game.getBallsSpeed()));
//            summonedBombBall.addToGame(game);
//        }
//        remainingBalls.increase(numberOfExplodingBalls);
//        beingHit.removeHitListener(this);
//    }
//}
