package Game.Tile;


import java.util.ArrayList;
import java.util.Comparator;

/**
 * Tile used in a scrabble-like game. It has a letter assigned to itself and a value.
 */
public class Tile implements Comparable<Tile>, Comparator<Tile> {
    /**
     * String used to print the inside of a square in the console when there is no tile inside.
     */
    public static final String NO_TILE_STRING = "  ";

    public static final char JOKER_DEFAULT_CHAR = '✩';
    /**
     * Value (number of points it is worth) of the tile.
     */
    private final int VALUE;
    /**
     * Indicates if a tile is a joker.
     */
    private final boolean IS_JOKER;
    /**
     * Character (letter) of the tile.
     */
    private char CHARACTER;

    /**
     * Constructor generating a tile with given character and value.
     *
     * @param character Letter on the tile.
     * @param value     Value of the tile.
     */
    public Tile(char character, int value) {
        this.CHARACTER = character;
        this.VALUE = value;
        this.IS_JOKER = false;
    }

    /**
     * Constructor generating a tile with a given letter. Value is set to one. Useful for games like Fundox.
     *
     * @param character Letter on the tile.
     */
    public Tile(char character) {
        this.CHARACTER = character;
        this.VALUE = 1;
        this.IS_JOKER = false;
    }

    /**
     * Constructor generating a tile with a given letter, value and isJoker state.
     *
     * @param character Letter on the tile.
     * @param value     Value of the tile.
     * @param isJoker   Indicates if the tile is a joker.
     */
    private Tile(char character, int value, boolean isJoker) {
        this.CHARACTER = character;
        this.VALUE = value;
        this.IS_JOKER = isJoker;
    }

    /**
     * Constructor of jokers.
     *
     * @return A tile used as a joker.
     */
    public static Tile Joker() {
        return new Tile(JOKER_DEFAULT_CHAR, 0, true);
    }

    /**
     * Converts an array of tiles to the corresponding string.
     *
     * @param word List of letter. (Taken in the order of the array).
     * @return String/word contained in the list.
     */
    public static String wordToString(Tile[] word) {
        StringBuilder str = new StringBuilder();
        for (Tile tile : word) {
            str.append(tile.getCharacter());
        }
        return str.toString().toUpperCase();
    }

    /**
     * Generates a bag (ArrayList) of tiles corresponding to the french Scrabble letter repartition.
     *
     * @return List of tiles.
     */
    public static ArrayList<Tile> getScrabbleFrenchTiles() {
        ArrayList<Tile> bag = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            bag.add(new Tile('E', 1));
        }
        for (int i = 0; i < 9; i++) {
            bag.add(new Tile('A', 1));
        }
        for (int i = 0; i < 8; i++) {
            bag.add(new Tile('I', 1));
        }
        for (int i = 0; i < 6; i++) {
            bag.add(new Tile('N', 1));
            bag.add(new Tile('O', 1));
            bag.add(new Tile('R', 1));
            bag.add(new Tile('S', 1));
            bag.add(new Tile('T', 1));
            bag.add(new Tile('U', 1));
        }
        for (int i = 0; i < 5; i++) {
            bag.add(new Tile('L', 1));
        }
        for (int i = 0; i < 3; i++) {
            bag.add(new Tile('D', 2));
            bag.add(new Tile('M', 2));
        }
        for (int i = 0; i < 2; i++) {
            bag.add(new Tile('G', 2));
            bag.add(new Tile('B', 3));
            bag.add(new Tile('C', 3));
            bag.add(new Tile('P', 3));
            bag.add(new Tile('F', 4));
            bag.add(new Tile('H', 4));
            bag.add(new Tile('V', 4));
            bag.add(Joker());
        }
        for (int i = 0; i < 1; i++) {
            bag.add(new Tile('J', 8));
            bag.add(new Tile('Q', 8));
            bag.add(new Tile('K', 10));
            bag.add(new Tile('W', 10));
            bag.add(new Tile('X', 10));
            bag.add(new Tile('Y', 10));
            bag.add(new Tile('Z', 10));
        }

        return bag;
    }

    /**
     * Generates a bag (ArrayList) of tiles corresponding to the french Fundox letter repartition.
     *
     * @return List of tiles.
     */
    public static ArrayList<Tile> getFundoxFrenchTiles() {
        ArrayList<Tile> bag = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            bag.add(new Tile('J'));
            bag.add(new Tile('K'));
            bag.add(new Tile('Q'));
            bag.add(new Tile('W'));
            bag.add(new Tile('X'));
            bag.add(new Tile('Y'));
            bag.add(new Tile('Z'));
        }

        for (int i = 0; i < 4; i++) {
            bag.add(new Tile('B'));
            bag.add(new Tile('F'));
            bag.add(new Tile('G'));
            bag.add(new Tile('H'));
        }

        for (int i = 0; i < 6; i++) {
            bag.add(new Tile('C'));
            bag.add(new Tile('P'));
            bag.add(new Tile('V'));
        }

        for (int i = 0; i < 8; i++) {
            bag.add(new Tile('D'));
            bag.add(new Tile('M'));
        }

        for (int i = 0; i < 12; i++) {
            bag.add(new Tile('L'));
        }

        for (int i = 0; i < 14; i++) {
            bag.add(new Tile('O'));
        }

        for (int i = 0; i < 16; i++) {
            bag.add(new Tile('N'));
            bag.add(new Tile('R'));
            bag.add(new Tile('S'));
            bag.add(new Tile('T'));
            bag.add(new Tile('U'));
        }

        for (int i = 0; i < 20; i++) {
            bag.add(new Tile('I'));
        }

        for (int i = 0; i < 22; i++) {
            bag.add(new Tile('A'));
        }

        for (int i = 0; i < 38; i++) {
            bag.add(new Tile('E'));
        }

        return bag;
    }

    /**
     * Getter of the character attribute.
     *
     * @return Char of the tile.
     */
    public char getCharacter() {
        return CHARACTER;
    }

    /**
     * Getter of the value attribute.
     *
     * @return Value of the tile.
     */
    public int getValue() {
        return VALUE;
    }

    /**
     * Getter of the joker state.
     *
     * @return True if the tile is a joker, false otherwise.
     */
    public boolean IS_JOKER() {
        return IS_JOKER;
    }

    /**
     * Setter of the character on the tile.
     *
     * @param CHARACTER New char on the tile.
     */
    public void setCHARACTER(char CHARACTER) {
        this.CHARACTER = CHARACTER;
    }

    /**
     * Returns a string representation of a tile in the format Letter in upper case followed by the value in indices. Examples: AÃ¢â€šï¿½, QÃ¢â€šË†...
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String str = String.valueOf(CHARACTER);
        switch (this.VALUE) {
            case 0:
                str += "Ã¢â€šâ‚¬";
                break;
            case 1:
                str += "Ã¢â€šï¿½";
                break;
            case 2:
                str += "Ã¢â€šâ€š";
                break;
            case 3:
                str += "Ã¢â€šÆ’";
                break;
            case 4:
                str += "Ã¢â€šâ€ž";
                break;
            case 5:
                str += "Ã¢â€šâ€¦";
                break;
            case 6:
                str += "Ã¢â€šâ€ ";
                break;
            case 7:
                str += "Ã¢â€šâ€¡";
                break;
            case 8:
                str += "Ã¢â€šË†";
                break;
            case 9:
                str += "Ã¢â€šâ€°";
                break;
            case 10:
                str += "Ã¢â€šï¿½Ã¢â€šâ‚¬";
                break;
            default:
                str += String.valueOf(this.VALUE);
        }
        return str;
    }

    /**
     * Compares this tile to another one alphabetically depending on their character.
     *
     * @param o Tile to compare to.
     * @return -1 if this tile's letter is smaller than the other's, 0 if equals, 1 if greater.
     */
    @Override
    public int compareTo(Tile o) {
        return Character.compare(this.getCharacter(), o.getCharacter());
    }

    /**
     * Compares two tiles alphabetically depending on their character.
     *
     * @param o1 First tile.
     * @param o2 Second tile.
     * @return -1 if the first tile's letter is smaller than the second one's, 0 if equals, 1 if greater.
     */
    @Override
    public int compare(Tile o1, Tile o2) {
        return o1.compareTo(o2);
    }
}
