package Game.Square;

import Game.Gameplay.Player;

abstract class FundoxSquareImplementation extends SquareImplementation implements FundoxSquare {
    /**
     * Owner of the square. Used to recalculate the score based on territories.
     */
    protected Player owner;

    /**
     * Owner of the bonus. Used to reset the bonus count of all the players at the end of the round.
     */
    protected Player bonusOwner;

    /**
     * Indicates whether the square has an owner.
     *
     * @return True if the square is owned, false otherwise
     */
    @Override
    public boolean isOwned() {
        return this.owner != null;
    }

    /**
     * Getter of the owner.
     *
     * @return The owner, can be null.
     */
    @Override
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Setter of the owner.
     *
     * @param owner New owner.
     */
    @Override
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Indicates whether the bonus is owned by a player.
     *
     * @return True if a player has taken this bonus.
     */
    @Override
    public boolean isBonusOwned() {
        return this.bonusOwner != null;
    }

    /**
     * Getter of the bonus owner.
     *
     * @return The bonus owner, can be null.
     */
    @Override
    public Player getBonusOwner() {
        return this.bonusOwner;
    }

    /**
     * Setter of the bonus owner.
     *
     * @param owner The new bonus owner.
     */
    @Override
    public void setBonusOwner(Player owner) {
        this.bonusOwner = owner;
    }
}
