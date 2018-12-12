package Game.Board;

import Game.Gameplay.Dictionary;
import Game.Gameplay.Player;
import Game.Square.Square;
import Game.Tile.Tile;
import Game.Tools.Direction;
import Game.Tools.Index2D;

import java.util.ArrayList;

/**
 * Grid of squares used to play Scrabble or Fundox.
 */
public abstract class Board {
    /**
     * Indicates whether there are tiles placed on the board.
     */
    protected boolean isEmpty;

    /**
     * Prints {@code msg} prefixed by "[INFO]".
     *
     * @param msg Message to log.
     */
    public static void log(String msg) {
        System.out.println("[INFO] " + msg);
    }

    /**
     * Prints {@code msg} prefixed by "[ERROR]".
     *
     * @param msg Message to log as error.
     */
    public static void err(String msg) {
        System.out.println("[ERROR] " + msg);
    }

    /**
     * Length and width of the board.
     *
     * @return Board size.
     */
    protected abstract int BOARD_SIZE();

    /**
     * Returns the square (case) at [{@code line}][{@code column}]
     *
     * @param line   Line index of the square.
     * @param column Column index of the square.
     * @return Square indicated by the parameters.
     * @throws IllegalArgumentException If the indicated square is outside the board.
     */
    public abstract Square getSquare(int line, int column);

    /**
     * Places the tile of the board in the square [{@code line}][{@code column}].
     *
     * @param line   Line index where the tile will be placed.
     * @param column Column index where the tile will be placed.
     * @param tile   Tile to place.
     */
    public void playTile(int line, int column, Tile tile) {
        this.getSquare(line, column).setTile(tile);
    }

    /**
     * Removes the tile from the board in the square [{@code line}][{@code column}] and returns it.
     *
     * @param line   Line index where the tile will be removed.
     * @param column Column index where the tile will be removed.
     * @return Tile in the square indicated.
     */
    protected Tile removeTile(int line, int column) {
        return this.getSquare(line, column).removeTile();
    }

    /**
     * Checks if the word the player tries to place is connected to a tile on the board.
     *
     * @param firstSquareLine   Line index of the first letter.
     * @param firstSquareColumn Column index of the first letter.
     * @param direction         Orientation of the word.
     * @param word              Word which is tried to be placed
     * @return True if the word is connected ot another word, false otherwise.
     */
    protected boolean wordIsConnected(int firstSquareLine, int firstSquareColumn, Direction direction, String word) {
        int zoneUpperLeftCornerLine = firstSquareLine - 1;
        int zoneUpperLeftCornerColumn = firstSquareColumn - 1;
        int zoneLowerRightCornerLine = firstSquareLine + (direction.isHorizontal() ? 1 : word.length());
        int zoneLowerRightCornerColumn = firstSquareColumn + (direction.isHorizontal() ? word.length() : 1);

        for (int i = zoneUpperLeftCornerLine; i <= zoneLowerRightCornerLine; i++) {
            for (int j = zoneUpperLeftCornerColumn; j <= zoneLowerRightCornerColumn; j++) {
                if (i >= 0 && i < BOARD_SIZE() && j >= 0 && j < BOARD_SIZE()) {
                    if (!this.getSquare(i, j).isEmpty()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Checks if the word the player tries to place is placed at on point of the middle case.
     *
     * @param firstSquareLine   Line index of the first letter.
     * @param firstSquareColumn Column index of the first letter.
     * @param direction         Orientation of the word.
     * @param word              Word which is tried to be placed
     * @return True if the word crosses the middle case, false otherwise.
     */
    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    protected boolean wordIsOnMiddleCase(int firstSquareLine, int firstSquareColumn, Direction direction, String word) {
        for (int i = firstSquareLine; i <= (firstSquareLine + (direction.isHorizontal() ? 0 : word.length())); i++) {
            for (int j = firstSquareColumn; j <= (firstSquareColumn + (direction.isHorizontal() ? word.length() : 0)); j++) {
                if (i >= 0 && i < BOARD_SIZE() && j >= 0 && j < BOARD_SIZE()) {
                    if (i == j && i == Math.floor(BOARD_SIZE() / 2)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Plays the word {@code word} starting in the square [{@code firstSquareLine}][{@code firstSquareColumn}] following the direction {@code direction} using the tiles in {@code rack} and attributing the points to {@code player}.
     *
     * @param firstSquareLine   Line index of the first letter.
     * @param firstSquareColumn Column index of the first letter.
     * @param direction         Orientation in which to place the word.
     * @param word              Word to place.
     * @param player            Player that needs to receive the points
     * @return The number of tiles placed if the word has been placed, 0 otherwise.
     */
    public int playWord(int firstSquareLine, int firstSquareColumn, Direction direction, String word, Player player) {
        if (firstSquareLine < 0
                || firstSquareLine > BOARD_SIZE()
                || firstSquareColumn < 0
                || firstSquareColumn > BOARD_SIZE()
                || (direction.isHorizontal() && firstSquareColumn + word.length() > BOARD_SIZE())
                || (direction == Direction.VERTICAL && firstSquareLine + word.length() > BOARD_SIZE())) {
            err("Word doesn't fit in the board");
            return 0;
        }

        if (word.length() == 0) {
            err("Word is too short");
            return 0;
        }

        if (this.isEmpty) {
            if (!wordIsOnMiddleCase(firstSquareLine, firstSquareColumn, direction, word)) {
                err("Word is not in the middle case");
                return 0;
            }
        } else {
            if (!wordIsConnected(firstSquareLine, firstSquareColumn, direction, word)) {
                err("Word is not connected");
                return 0;
            }
        }

        int i = firstSquareLine;
        int j = firstSquareColumn;
        ArrayList<Index2D> placedTilesPosition = new ArrayList<>();

        for (char letter : word.toCharArray()) {
            if (this.getSquare(i, j).isEmpty()) {
                if (!ownsLetter(letter, player.getRack(), i, j, placedTilesPosition)) {
                    err("Could not place the word because the letter " + letter + " was not in the players hand");
                    retrieveTilesToRack(player.getRack(), placedTilesPosition);
                    return 0;
                }
            } else {
                if (this.getSquare(i, j).getTile().getCharacter() != letter) {
                    err("Could not place the word because the letter " + letter + " was not on the board");
                    retrieveTilesToRack(player.getRack(), placedTilesPosition);
                    return 0;
                }
            }

            if (direction.isHorizontal()) {
                j++;
            } else {
                i++;
            }
        }

        if (placedTilesPosition.size() < MIN_LETTER_PLACED_COUNT()) {
            err("Not enough letters were placed (min: " + MIN_LETTER_PLACED_COUNT() + ")");
            retrieveTilesToRack(player.getRack(), placedTilesPosition);
            return 0;
        }

        if (!areWordsCreatedAreCorrect(placedTilesPosition, direction)) {
            err("Words created are not correct.");
            retrieveTilesToRack(player.getRack(), placedTilesPosition);
            return 0;
        }

        this.isEmpty = false;
        this.countPoints(firstSquareLine, firstSquareColumn, direction, placedTilesPosition, player);

        return placedTilesPosition.size();
    }

    /**
     * Returns true if the {@code letter} is in the {@code rack}, if so places the tile on the board and removes it from the rack, if the tile is placed the played position is registered in {@code placedTilesPositions}.
     *
     * @param letter               Character looked for.
     * @param rack                 Rack in which the tile is searched.
     * @param squareLine           Line index of the square the tile is tried to be placed in.
     * @param squareColumn         Column index of the square the tile is tried to be placed in.
     * @param placedTilesPositions List of the position in which the player has placed tiles in the current turn.
     * @return True if the letter has been placed, false otherwise.
     */
    protected boolean ownsLetter(char letter, ArrayList<Tile> rack, int squareLine, int squareColumn, ArrayList<Index2D> placedTilesPositions) {
        for (int h = 0; h < rack.size(); h++) {
            if (rack.get(h).getCharacter() == letter) {
                this.playTile(squareLine, squareColumn, rack.remove(h));
                placedTilesPositions.add(new Index2D(squareLine, squareColumn));
                return true;
            }
        }
        for (int h = 0; h < rack.size(); h++) {
            if (rack.get(h).IS_JOKER()) {
                rack.get(h).setCHARACTER(letter);
                this.playTile(squareLine, squareColumn, rack.remove(h));
                placedTilesPositions.add(new Index2D(squareLine, squareColumn));
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if the word generated after the player played are correct.
     *
     * @param placedTiles List of the positions of the placed tiles.
     * @param direction   Direction of the word placed.
     * @return True if the word generated after the player played are correct, else otherwise.
     * @throws IllegalArgumentException If the list of tiles placed is empty
     */
    protected boolean areWordsCreatedAreCorrect(ArrayList<Index2D> placedTiles, Direction direction) throws IllegalArgumentException {
        if (placedTiles.size() == 0) {
            throw new IllegalArgumentException("No letter placed.");
        }

        boolean wordsCreatedAreCorrect = checkWord(placedTiles.get(0).LINE, placedTiles.get(0).COLUMN, direction);

        for (Index2D placedTile : placedTiles) {
            wordsCreatedAreCorrect &= checkWord(placedTile.LINE, placedTile.COLUMN, direction.getOpposite());
        }

        return wordsCreatedAreCorrect;
    }

    /**
     * Removes the tile from the squares indicated in {@code placedTiles} and places them in the {@code rack}.
     *
     * @param rack        Rack where tiles will be placed.
     * @param placedTiles List of positions of squares that contains the tiles to remove.
     */
    protected void retrieveTilesToRack(ArrayList<Tile> rack, ArrayList<Index2D> placedTiles) {
        for (Index2D placedTile : placedTiles) {
            Tile removedTile = this.removeTile(placedTile.LINE, placedTile.COLUMN);
            if (removedTile.IS_JOKER()) {
                removedTile.setCHARACTER(Tile.JOKER_DEFAULT_CHAR);
            }
            rack.add(removedTile);
        }
    }

    /**
     * Checks if the word passing by the case [{@code squareLine}][{@code squareColumn}] following the direction {@code direction} is in the dictionary.
     *
     * @param squareLine   Line index of the square to start inspection from.
     * @param squareColumn Column index of the square to start inspection from.
     * @param direction    Direction to inspect (horizontal/vertical).
     * @return True if the word is in the dictionary, false otherwise.
     * @throws IllegalArgumentException If the square indexes given are outside the board.
     */
    protected boolean checkWord(int squareLine, int squareColumn, Direction direction) throws IllegalArgumentException {
        assertSquareIsNotEmpty(squareLine, squareColumn);

        int firstLetter = getFirstLetterIndexOfWordAt(squareLine, squareColumn, direction);

        int lastLetter = getLastLetterIndexOfWordAt(squareLine, squareColumn, direction);

        int wordLength = lastLetter - firstLetter + 1;

        if (wordLength > 1) {
            Tile[] word = new Tile[wordLength];
            for (int k = 0; k < wordLength; k++) {
                if (direction.isHorizontal()) {
                    word[k] = this.getSquare(squareLine, firstLetter + k).getTile();
                } else {
                    word[k] = this.getSquare(firstLetter + k, squareColumn).getTile();
                }

            }
            return Dictionary.checkWord(Tile.wordToString(word));
        } else {
            return true;
        }
    }

    /**
     * Throws an exception if the square at [{@code line}][{@code column}] is outside the board and if it is empty.
     *
     * @param line   Line index of the square to inspect.
     * @param column Column index of the square to inspect.
     * @throws IllegalArgumentException If the given indexes are outside the board.
     */
    protected void assertSquareIsNotEmpty(int line, int column) throws IllegalArgumentException {
        if (line < 0
                || line > BOARD_SIZE()
                || column < 0
                || column > BOARD_SIZE()) {
            throw new IllegalArgumentException("Trying to access square outside the board.");
        }
        if (this.getSquare(line, column).isEmpty()) {
            throw new IllegalArgumentException("Case is empty");
        }
    }

    /**
     * Returns the index of the first letter in the word placed at [{@code line}][{@code column}] oriented following {@code direction}.
     * Example:
     * i\j 0  1  2  3  4  5
     * 0  [ ][ ][ ][ ][ ][ ]
     * 1  [ ][ ][ ][ ][ ][ ]
     * 2  [ ][W][O][R][D][ ]
     * 3  [ ][ ][ ][ ][ ][ ]
     * getFirstLetterIndexOfWordAt(2, 3, Direction.HORIZONTAL) will return 1 because column index of W is 1
     *
     * @param line      Line index of the square to start inspection from.
     * @param column    Column index of the square to start inspection from.
     * @param direction Direction to inspect (horizontal/vertical).
     * @return Index of the first letter.
     */
    protected int getFirstLetterIndexOfWordAt(int line, int column, Direction direction) {
        int firstLetter;
        if (direction.isHorizontal()) {
            firstLetter = column;

            while (firstLetter - 1 >= 0 && !this.getSquare(line, firstLetter - 1).isEmpty()) {
                firstLetter--;
            }
        } else {
            firstLetter = line;

            while (firstLetter - 1 >= 0 && !this.getSquare(firstLetter - 1, column).isEmpty()) {
                firstLetter--;
            }
        }
        return firstLetter;
    }

    /**
     * Returns the index of the last letter in the word placed at [{@code line}][{@code column}] oriented following {@code direction}.
     * Example:
     * i\j 0  1  2  3  4  5
     * 0  [ ][ ][ ][ ][ ][ ]
     * 1  [ ][ ][ ][ ][ ][ ]
     * 2  [ ][W][O][R][D][ ]
     * 3  [ ][ ][ ][ ][ ][ ]
     * getLastLetterIndexOfWordAt(2, 3, Direction.HORIZONTAL) will return 4 because column index of D is 1
     *
     * @param line      Line index of the square to start inspection from.
     * @param column    Column index of the square to start inspection from.
     * @param direction Direction to inspect (horizontal/vertical).
     * @return Index of the last letter.
     */
    protected int getLastLetterIndexOfWordAt(int line, int column, Direction direction) {
        int lastLetter;
        if (direction.isHorizontal()) {
            lastLetter = column;

            while (lastLetter + 1 < BOARD_SIZE() && !this.getSquare(line, lastLetter + 1).isEmpty()) {
                lastLetter++;
            }
        } else {
            lastLetter = line;

            while (lastLetter + 1 < BOARD_SIZE() && !this.getSquare(lastLetter + 1, column).isEmpty()) {
                lastLetter++;
            }
        }
        return lastLetter;
    }

    /**
     * Counts the points scored by playing the word crossing the square [{@code firstSquareLine}][{@code firstSquareColumn}] following the direction {@code direction} and add them to the player {@code player}.
     *
     * @param firstSquareLine   Line index of the letter to start counting points from.
     * @param firstSquareColumn Column index of the letter to start counting points from.
     * @param direction         Direction of the placed word.
     * @param placedTiles       List of positions where the player has placed tile in the current turn.
     * @param player            Player to give the points to.
     */
    protected abstract void countPoints(int firstSquareLine, int firstSquareColumn, Direction direction, ArrayList<Index2D> placedTiles, Player player);

    /**
     * Minimum number of letters that needs to be placed by the player.
     *
     * @return Minimum amount.
     */
    protected abstract int MIN_LETTER_PLACED_COUNT();
    
    public int getBoardSize() {
    	return BOARD_SIZE();
    }
}
