package Game.Square;

/**
 * Describes properties specific to Scrabble squares.
 */
public interface ScrabbleSquare extends Square {

    /**
     * The coefficient by which the letter's value should be multiplied.
     *
     * @return Letter value bonus multiplier.
     */
    int getLetterValue();

    /**
     * The coefficient by which the word's value should be multiplied.
     *
     * @return Word value bonus multiplier.
     */
    int getWordMultiplier();

}
