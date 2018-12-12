package Game.Square;

import Game.Gameplay.Player;

/**
 * Describes properties specific to Fundox squares.
 */
public interface FundoxSquare extends Square {

    /**
     * Indicates whether the square is owned by a player or not.
     *
     * @return True if the square is owned, false otherwise.
     */
    boolean isOwned();

    /**
     * Getter of the owner of the Square.
     *
     * @return The owner, null if there is none.
     */
    Player getOwner();

    /**
     * Setter of the owner.
     *
     * @param owner New owner.
     */
    void setOwner(Player owner);

    /**
     * Indicates if the square is a grid clearing one.
     *
     * @return True if the square's special effect is to clear the grid.
     */
    boolean gridShouldBeCleared();

    /**
     * Indicates whether the bonus is owned by a player.
     *
     * @return True if a player has taken this bonus.
     */
    boolean isBonusOwned();

    /**
     * Getter of the bonus owner.
     *
     * @return The bonus owner, can be null.
     */
    Player getBonusOwner();

    /**
     * Setter of the bonus owner.
     *
     * @param owner The new bonus owner.
     */
    void setBonusOwner(Player owner);

}
