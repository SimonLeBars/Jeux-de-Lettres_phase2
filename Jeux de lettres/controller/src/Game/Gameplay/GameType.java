package Game.Gameplay;

/**
 * Type of game (Scrabble/Fundox)
 */
public enum GameType {
    SCRABBLE(1),
    FUNDOX(2);

    /**
     * Id used to compare to user's input.
     */
    public final int id;

    /**
     * Constructor.
     *
     * @param id id.
     */
    GameType(int id) {
        this.id = id;
    }
}