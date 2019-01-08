package Game.Square;

import java.io.Serializable;

/**
 * Describes properties specific to Scrabble squares.
 */
public interface ScrabbleSquare extends Square, Serializable {

    /**
     * The coefficient by which the letter's value should be multiplied.
     *
     * @return Letter value bonus multiplier.
     */
    int getLetterValue();
    
    int getLetterMultiplier();

    /**
     * The coefficient by which the word's value should be multiplied.
     *
     * @return Word value bonus multiplier.
     */
    int getWordMultiplier();

}
