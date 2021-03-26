import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private String levelName;

    /**
     * creates a new ScoreIndicator.
     * @param counter the Counter of the score.
     * @param levelName the name of the current level.
     */
    public ScoreIndicator(Counter counter, String levelName) {
        this.score = counter;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(200, 14, "Score: " + score.toString(), 14);
        d.drawText(510, 14, "Level Name: " + levelName, 14);
    }

    @Override
    public void timePassed() { }

    /**
     * add the current ScoreIndicator to game's Sprites.
     * @param game the Game of the score.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}