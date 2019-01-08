package Game.Square;


import java.io.Serializable;

import Game.Tile.Tile;
import Game.Tools.ANSI_Color;

/**
 * Square (or case) in a grid of Scrabble/Fundox.
 */
public interface Square extends Serializable{

    /**
     * Reset code for text color.
     *
     * @return ANSI code to reset the text color.
     */
    default String getResetColor() {
        return ANSI_Color.RESET.toString();
    }

    /**
     * Resets the properties of the square.
     */
    void reset();

    /**
     * To know whether the square contains a tile or not.
     *
     * @return True if the square doesn't contain any tile, false otherwise.
     */
    boolean isEmpty();

    /**
     * Getter of the tile contained.
     *
     * @return Tile contained, null if square is empty.
     */
    Tile getTile();

    /**
     * Setter of the tile contained.
     *
     * @param tile New tile contained.
     */
    void setTile(Tile tile);

    /**
     * Removes the tile from the square and returns it
     *
     * @return Old tile contained.
     */
    Tile removeTile();

    /**
     * To know whether the bonus can still be applied.
     *
     * @return True if the bonus has been used, false otherwise.
     */
    boolean isBonusUsed();

    /**
     * Sets the bonus as used.
     */
    void markBonusAsUsed();

    /**
     * Returns the ANSI color code of the tile (indicating squares with special effects).
     *
     * @return ANSI code as a string.
     */
    String getColor();
}
