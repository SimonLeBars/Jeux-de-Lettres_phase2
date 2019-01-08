package Game.Gameplay;

import Game.Tile.Tile;
import Game.Tools.ANSI_Color;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Player
 */
public class Player implements Serializable{
    /**
     * Displayed name of the player.
     */
    private final String NAME;

    /**
     * List of tiles of the player.
     */
    private final ArrayList<Tile> RACK;
    /**
     * Color of the player (mainly used in Fundox to display territories).
     */
    private final String color;
    /**
     * Points of the player.
     */
    private int score;
    /**
     * Number of bonuses the player owns (useful for Fundox only).
     */
    private int bonusCount;

    /**
     * Constructor requiring the name, color is set to default.
     *
     * @param name Name of the player.
     * @param rack Rack of the player.
     */
    public Player(String name, ArrayList<Tile> rack) {
        this.NAME = name;
        this.score = 0;
        this.bonusCount = 0;
        this.RACK = rack;
        this.color = ANSI_Color.RESET.toString();
    }

    /**
     * Constructor requiring the name and the color of the player.
     *
     * @param name  Name of the player.
     * @param rack  Rack of the player.
     * @param color Color of the player.
     */
    public Player(String name, ArrayList<Tile> rack, ANSI_Color color) {
        this.NAME = name;
        this.score = 0;
        this.RACK = rack;
        this.color = color.toString();
    }

    /**
     * Getter of the name of the player.
     *
     * @return Name as a string.
     */
    public String getName() {
        return NAME;
    }

    /**
     * Getter of the name of the player.
     *
     * @return Score as a int.
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter of the score.
     *
     * @param score New value of the score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Adds the given amount to the actual score (amount can be negative).
     *
     * @param score Score to add.
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Adds a tile to the player's rack.
     *
     * @param tile Tile to add.
     */
    public void addTile(Tile tile) {
        this.RACK.add(tile);
    }

    /**
     * Getter of the number of bonuses.
     *
     * @return Count of bonuses as an integer.
     */
    public int getBonusCount() {
        return bonusCount;
    }

    /**
     * Setter of the bonus count.
     *
     * @param bonusCount New bonus count.
     */
    public void setBonusCount(int bonusCount) {
        this.bonusCount = bonusCount;
    }

    /**
     * Increments the bonus count by one.
     */
    public void increaseBonusCount() {
        this.bonusCount++;
    }

    /**
     * Getter of the player's rack.
     *
     * @return List of tiles the player owns
     */
    public ArrayList<Tile> getRack() {
        return this.RACK;
    }

    /**
     * Getter of the color of the player. Text color should be reset after use. (color + text + resetColor)
     *
     * @return ANSI color code as a string.
     */
    public String getColor() {
        return color;
    }

    /**
     * String representation of the player. "Name (score, bonusCount) [rack]", player name is colored.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return this.getColor() + this.getName() + ANSI_Color.RESET.toString() + " (" + this.getScore() + " point" + (this.getScore() > 1 ? "s" : "") + ", " + this.getBonusCount() + " point" + (this.getBonusCount() > 1 ? "s" : "") + ") " + this.getRack();
    }

    /**
     * String representation of the player. "Name (score) [rack]", player name is colored.
     *
     * @return String representation.
     */
    public String toStringScrabble() {
        return this.getColor() + this.getName() + ANSI_Color.RESET.toString() + " (" + this.getScore() + " point" + (this.getScore() > 1 ? "s" : "") + ") " + this.getRack();
    }

    /**
     * String representation of the player. "Name (score, bonusCount)", player name is colored.
     *
     * @return String representation.
     */
    public String toStringFundox() {
        return this.getColor() + this.getName() + ANSI_Color.RESET.toString() + " (" + this.getScore() + " point" + (this.getScore() > 1 ? "s" : "") + ", " + this.getBonusCount() + " bonus" + (this.getBonusCount() > 1 ? "es" : "") + ")";
    }
}
