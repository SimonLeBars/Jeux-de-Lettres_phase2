package Game.Square;

import java.io.Serializable;

import Game.Tile.Tile;
import Game.Tools.ANSI_Color;

public class BonusSquare extends FundoxSquareImplementation implements FundoxSquare, Serializable {
    /**
     * Default value of the bonus used state indicator (false = a bonus in Fundox).
     */
    private static final boolean bonusUsedDefault = false;

    /**
     * Default value of the grid clearing square indicator (false = no a grid clearing square in Fundox).
     */
    private static final boolean gridShouldBeCleared = false;

    /**
     * Color of the square.
     */
    private static final String color = ANSI_Color.YELLOW.toString();

    /**
     * Constructor.
     */
    public BonusSquare() {
        reset();
    }

    /**
     * Resets the square with its default values (removes the tile and sets the bonus related values to their default state).
     */
    @Override
    public void reset() {
        this.tile = null;
        this.bonusUsed = bonusUsedDefault;
        this.owner = null;
        this.bonusOwner = null;
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
     * String representation of the square. "[tile]", [ and ] are colored by the square color before a tile is placed then by the color of the player that owns the bonus.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return (isBonusOwned() ? getBonusOwner().getColor() : getColor()) + "[" + getResetColor() + (isEmpty() ? Tile.NO_TILE_STRING : (isOwned() ? getOwner().getColor() + getTile() + getResetColor() : getTile())) + (isBonusOwned() ? getBonusOwner().getColor() : getColor()) + "]" + getResetColor();
    }
}

