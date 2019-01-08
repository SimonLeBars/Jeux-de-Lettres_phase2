package Game.Square;

import java.io.Serializable;

import Game.Tile.Tile;

public abstract class SquareImplementation implements Square, Serializable {
    /**
     * Tile contained in the square, can be null.
     */
    protected Tile tile;
    /**
     * Indicates whether the bonus has already been used once.
     */
    protected boolean bonusUsed;

    /**
     * To know whether the square contains a tile or not.
     *
     * @return True if the square doesn't contain any tile, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.tile == null;
    }

    /**
     * Getter of the tile contained.
     *
     * @return Tile contained, null if square is empty.
     */
    @Override
    public Tile getTile() {
        return this.tile;
    }

    /**
     * Setter of the tile contained.
     *
     * @param tile New tile contained.
     */
    @Override
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    /**
     * Removes the tile from the square and returns it
     *
     * @return Old tile contained.
     */
    @Override
    public Tile removeTile() {
        Tile temp = this.tile;
        this.tile = null;
        return temp;
    }

    /**
     * To know whether the bonus can still be applied.
     *
     * @return True if the bonus has been used, false otherwise.
     */
    @Override
    public boolean isBonusUsed() {
        return this.bonusUsed;
    }

    /**
     * Sets the bonus as used.
     */
    @Override
    public void markBonusAsUsed() {
        this.bonusUsed = true;
    }

}
