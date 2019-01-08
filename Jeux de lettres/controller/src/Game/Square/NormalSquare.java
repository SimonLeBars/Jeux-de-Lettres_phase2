package Game.Square;

import java.io.Serializable;

import Game.Tile.Tile;
import Game.Tools.ANSI_Color;

/**
 * Classic square for Scrabble and Fundox
 */
public class NormalSquare extends FundoxSquareImplementation implements ScrabbleSquare, FundoxSquare, Serializable {
    /**
     * Color of the square. Grey makes bonus colors and letters more visible on a dark console.
     */
    protected static final String color = ANSI_Color.GREY.toString();
    /**
     * Multiplier bonus used for Scrabble. Acts on the value of the contained tile.
     */
    private static final int letterMultiplier = 1;
    /**
     * Multiplier bonus used for Scrabble. Acts on the value of the word formed by the tile contained.
     */
    private static final int wordMultiplier = 1;
    /**
     * Default value of the bonus used state indicator (true = not a bonus in Fundox, no impact on Scrabble as multipliers are set to 1).
     */
    private static final boolean bonusUsedDefault = true;
    /**
     * Default value of the grid clearing square indicator (false = not a grid clearing square in Fundox).
     */
    private static final boolean gridShouldBeCleared = false;

    /**
     * Constructor.
     */
    public NormalSquare() {
        reset();
    }

    /**
     * Resets the square with its default values (removes the tile and sets the bonus related values to their default state).
     */
    @Override
    public void reset() {
        this.tile = null;
        this.owner = null;
        this.bonusUsed = bonusUsedDefault;
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
     * Indicates whether the square is a grid clearing one.
     *
     * @return gridShouldBeCleared static attribute.
     */
    @Override
    public boolean gridShouldBeCleared() {
        return gridShouldBeCleared;
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
        return this.getColor() + "[" + getResetColor() + (isEmpty() ? Tile.NO_TILE_STRING : (isOwned() ? getOwner().getColor() + getTile() + getResetColor() : getTile())) + getColor() + "]" + getResetColor();
    }
}