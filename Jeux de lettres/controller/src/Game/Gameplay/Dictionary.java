package Game.Gameplay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * List of words.
 */
public class Dictionary {
    /**
     * List containing the words. Using HashSet because of the efficiency of the method contains().
     */
    private static final HashSet<String> WORDS = new HashSet<>();

    /**
     * Loads the words placed in the file at {@code filePath}. One line = one word in the dictionary.
     * Dictionary is cleared before reading. Word are stored in upper case.
     *
     * @param filePath Path to the file.
     * @throws IOException If an error append while reading the file.
     */
    public static void load(String filePath) throws IOException {
        WORDS.clear();
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String word;
        while ((word = bufferedReader.readLine()) != null) {
            WORDS.add(word.toUpperCase());
        }
        bufferedReader.close();
    }

    /**
     * Checks if the word is contained in the dictionary. Case has to match.
     *
     * @param word Word to check.
     * @return True if the word is in the dictionary, false otherwise.
     */
    public static boolean checkWord(String word) {
        return WORDS.contains(word);
    }
}
