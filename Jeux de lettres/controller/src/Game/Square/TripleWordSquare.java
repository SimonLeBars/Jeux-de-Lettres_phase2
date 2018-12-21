package Game.Square;

import Game.Tile.Tile;
import Game.Tools.ANSI_Color;

public class TripleWordSquare extends SquareImplementation implements ScrabbleSquare {
    /**
     * Multiplier bonus used for Scrabble. Acts on the value of the contained tile.
     */
    private static final int letterMultiplier = 1;

    /**
     * Multiplier bonus used for Scrabble. Acts on the value of the word formed by the tile contained.
     */
    private static final int wordMultiplier = 3;

    /**
     * Color of the square.
     */
    private static final String color = ANSI_Color.RED.toString();

    /**
     * Constructor.
     */
    public TripleWordSquare() {
        reset();
    }

    /**
     * Resets the square with its default values (removes the tile and sets the bonus related values to their default state).
     */
    @Override
    public void reset() {
        this.tile = null;
        this.bonusUsed = false;
    }

    /**
     * Computes the value of the tile contained. If bonus has not been used, multiplies the multiplier to the letter value.
     *
     * @return Calculated value of the tile.
     */
    @Override
    public int getLetterValue() {
        return (this.bonusUsed ? 1 : letterMultiplier) * this.tile.getValue();
    }
    
    @Override
    public int getLetterMultiplier() {
    	return this.letterMultiplier;
    }

    /**
     * Multiplier applied by this square.
     *
     * @return Multiplier if it hasn't already been used.
     */
    @Override
    public int getWordMultiplier() {
        return (this.bonusUsed ? 1 : wordMultiplier);
    }

    /**
     * Getter of the color of the square. Text color should be reset after use. (color + text + resetColor)
     *
     * @return ANSI color code as a string.
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * String representation of the square. "[tile]", [ and ] are colored.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return this.getColor() + "[" + getResetColor() + (this.isEmpty() ? Tile.NO_TILE_STRING : getTile()) + this.getColor() + "]" + getResetColor();
    }
}
