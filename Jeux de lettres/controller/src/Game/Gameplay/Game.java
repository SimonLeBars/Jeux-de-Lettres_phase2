package Game.Gameplay;

import Game.Board.Board;
import Game.Board.FundoxBoard;
import Game.Board.ScrabbleBoard;
import Game.Tile.Tile;
import Game.Tools.ANSI_Color;
import Game.Tools.Direction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Game of Scrabble or Fundox.
 */
public class Game {
    /**
     * Max number of tiles on a rack.
     */
    public static final int TILES_COUNT_ON_RACK = 7;
    /**
     * Max number of player in a game.
     */
    private static final int MAX_PLAYER_COUNT = 4;
    /**
     * Reader used to read user's inputs.
     */
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    /**
     * List of players.
     */
    private final ArrayList<Player> players = new ArrayList<>();
    /**
     * Rack used by all players in Fundox.
     */
    private final ArrayList<Tile> commonRack = new ArrayList<>();
    /**
     * Default colors of players. Ordered to easily differentiate the players when there are only a few.
     */
    private final ArrayList<ANSI_Color> playerColors = new ArrayList<>(Arrays.asList(ANSI_Color.RED, ANSI_Color.BLUE, ANSI_Color.YELLOW, ANSI_Color.GREEN, ANSI_Color.PURPLE, ANSI_Color.CYAN));
    /**
     * GameType (Scrabble/Fundox)
     */
    private final GameType gameType;
    /**
     * Index of the current player (whose is playing at this turn) in the list.
     */
    private int currentPlayerIndex;
    /**
     * Board/grid used to play.
     */
    private Board board;
    /**
     * Bag containing the tiles distributed to the players.
     */
    private ArrayList<Tile> bag;
    /**
     * Number of players that skipped in a row (reset to 0 when one of the players places letters)
     */
    private int skipsInARow;

    /**
     * Constructor.
     *
     * @param gameType Type of the game.
     */
    public Game(GameType gameType) {
        this.gameType = gameType;
        this.currentPlayerIndex = 0;
        this.skipsInARow = 0;
    }

    public static void main(String[] args) {
        try {
            Dictionary.load("Jeux de lettres/resources/Dico.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameType gameType = GameType.SCRABBLE;
        System.out.println("Which game do you want to play ? (1: Scrabble, 2: Fundox)");
        if (Integer.parseInt(getUserInput()) == GameType.FUNDOX.id) {
            gameType = GameType.FUNDOX;
        }

        Game g = new Game(gameType);
        g.setupPlayers();
        g.setupBoardAndRacks();
        
        g.displayGame();

        g.start();

    }

    /**
     * Reads the user input.
     *
     * @return Returns the inputs as a string.
     */
    public static String getUserInput() {
        String input;
        try {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return input;
    }

    /**
     * String of the ordinal value of the given number. Example: 1 -> "1st", 2 -> "2nd"...
     *
     * @param i Number to convert.
     * @return String of the ordinal.
     */
    public static String ordinal(int i) {
        String[] suffixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + suffixes[i % 10];

        }
    }

    /**
     * Sets the currentPlayerIndex to the next player (0 if the current player was the last of the list).
     */
    public void getNextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex == players.size()) {
            currentPlayerIndex = 0;
        }
    }

    /**
     * Starts the game. Loops until the game is finished. Ask players to play one after the other.
     */
    public void start() {
        Player currentPlayer;

        while (!isFinished()) {
            currentPlayer = players.get(currentPlayerIndex);
            System.out.println("It's " + currentPlayer.getColor() + currentPlayer.getName() + ANSI_Color.RESET + "'s turn !");

            System.out.println("Do you want to play (1), to skip your turn (2) or to exchange letters (3) ?");

            switch (Integer.parseInt(getUserInput())) {
                case 2:
                    skip(currentPlayer);
                    break;
                case 3:
                    exchangeTiles(currentPlayer);
                    break;
                default:
                    play(currentPlayer);
            }

            displayGame();

            getNextPlayer();
        }

        displayResult();

    }

    /**
     * Indicates whether the game is finished or not. Game is finished if a player has no tiles to play or if all the players skipped 2 turns in a row.
     *
     * @return True is the game has ended, false otherwise.
     */
    public boolean isFinished() {
        for (Player player : players) {
            if (player.getRack().size() == 0) {
                return true;
            }
        }
        return skipsInARow >= players.size() * 2;
    }

    /**
     * When a user decides to skip his turn.
     *
     * @param currentPlayer Player that is skipping his turn.
     */
    public void skip(Player currentPlayer) {
        skipsInARow++;

        System.out.println(currentPlayer.getColor() + currentPlayer.getName() + ANSI_Color.RESET + " skipped his turn...");
    }

    /**
     * Asks the user how many and which tiles he wants to get rid of. Refills his rack and places his old tiles into the bag.
     *
     * @param currentPlayer Player that is exchanging tiles.
     */
    public void exchangeTiles(Player currentPlayer) {
        skipsInARow = 0;

        System.out.println("How many tiles do you want to exchange ? (Between 0 and " + Math.min(currentPlayer.getRack().size(), bag.size()) + ")");
        int amount = Integer.parseInt(getUserInput());
        amount = (amount >= 0 && amount <= Math.min(currentPlayer.getRack().size(), bag.size())) ? amount : 0;

        ArrayList<Tile> temp = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            System.out.println("Which letter do you want to exchange ? (Number of the tile in your rack you want to get rid of)");
            int index = Integer.parseInt(getUserInput());
            index = (index >= 1 && index <= currentPlayer.getRack().size()) ? index - 1 : 0;
            System.out.println("Getting rid of " + currentPlayer.getRack().get(index).getCharacter() + ".");
            temp.add(currentPlayer.getRack().remove(index));
            System.out.println("Current rack: " + currentPlayer.getRack());
        }

        refillRack(currentPlayer.getRack(), bag, amount);
        bag.addAll(temp);

        System.out.println(currentPlayer.getColor() + currentPlayer.getName() + ANSI_Color.RESET + " exchanged " + amount + " tiles from his rack.");
    }

    /**
     * Asks the user which word he wants to play and where it wants to play. Refills his rack at the end.
     *
     * @param currentPlayer Player that is playing.
     */
    public void play(Player currentPlayer) {
        skipsInARow = 0;

        System.out.println("What word do you want to play ?");
        String word = getUserInput();

        System.out.println("What's the line of the square in which you want to play the first letter ?");
        int line = Integer.parseInt(getUserInput());

        System.out.println("What's the column of the square in which you want to play the first letter ?");
        int column = Integer.parseInt(getUserInput());

        Direction direction = Direction.HORIZONTAL;
        System.out.println("Do you want to play horizontally or vertically ? (1: Horizontally, 2: Vertically)");
        direction = Integer.parseInt(getUserInput()) == 2 ? Direction.VERTICAL : direction;

        int result = board.playWord(line, column, direction, word, currentPlayer);

        if (result == 0) {
            System.out.println("No word has been placed.");
        } else {
            System.out.println(currentPlayer.getColor() + currentPlayer.getName() + ANSI_Color.RESET + " played \"" + word + "\" " + (direction.isHorizontal() ? "horizontally" : "vertically") + " at [" + line + "][" + column + "]");
        }

        refillRack(currentPlayer.getRack(), bag, result);
    }

    /**
     * Displays the scoreboard by sorting players by biggest score.
     */
    public void displayResult() {
        System.out.println("Game has ended, here is the result :");
        players.sort(Comparator.comparingInt(Player::getScore));
        Collections.reverse(players);
        for (int i = 0; i < players.size(); i++) {
            System.out.println(ordinal(i + 1) + ": " + players.get(i).getColor() + players.get(i).getName() + ANSI_Color.RESET + "(" + players.get(i).getScore() + " point" + (players.get(i).getScore() > 1 ? "s" : "") + ")");
        }
        System.out.println("Congratulations !");
    }

    /**
     * Displays the board and the players scores and racks. Only one rack is displayed in a Fundox game.
     */
    public void displayGame() {
        System.out.println(board);
        if (gameType == GameType.SCRABBLE) {
            for (Player player : players) {
                System.out.println(player.toStringScrabble());
            }
        } else {
            System.out.println(commonRack);
            for (Player player : players) {
                System.out.println(player.toStringFundox());
            }
        }
        System.out.println();
    }

    /**
     * Creates the players following the user inputs (number of players and their names).
     */
    public void setupPlayers() {
        int playerCount;
        do {
            System.out.println("How many player is there ? (between 1 and " + MAX_PLAYER_COUNT + ")");
            playerCount = Integer.parseInt(getUserInput());
        } while (playerCount < 1 || playerCount > MAX_PLAYER_COUNT);

        String playerName;
        for (int i = 0; i < playerCount; i++) {
            System.out.println("What's the name of the " + ordinal(i + 1) + " player ?");
            playerName = getUserInput();
            playerName = (playerName.length() == 0 ? "Player " + (i + 1) : playerName);

            if (gameType == GameType.SCRABBLE) {
                players.add(new Player(playerName, new ArrayList<>(), (i < playerColors.size() ? playerColors.get(i) : ANSI_Color.RESET)));
            } else {
                players.add(new Player(playerName, commonRack, (i < playerColors.size() ? playerColors.get(i) : ANSI_Color.RESET)));
            }
        }
    }

    /**
     * Creates the board and distributes the tiles to the players.
     */
    public void setupBoardAndRacks() {
        if (gameType == GameType.SCRABBLE) {
            board = new ScrabbleBoard();
            bag = Tile.getScrabbleFrenchTiles();
            Collections.shuffle(bag);
            for (Player player : players) {
                refillRack(player.getRack(), bag, TILES_COUNT_ON_RACK);
            }
        } else {
            board = new FundoxBoard();
            bag = Tile.getFundoxFrenchTiles();
            Collections.shuffle(bag);
            refillRack(commonRack, bag, TILES_COUNT_ON_RACK);
        }
    }

    /**
     * Transfers {@code tileAmount} tiles from {@code bag} to {@code rack}. If their is not enough tiles in the bag, transfers as much tile as possible.
     *
     * @param rack       Rack that receives the tiles.
     * @param bag        Bag that provides the tiles.
     * @param tileAmount Number of tiles to transfer.
     */
    public void refillRack(ArrayList<Tile> rack, ArrayList<Tile> bag, int tileAmount) {
        for (int i = 0; i < tileAmount && !bag.isEmpty(); i++) {
            rack.add(bag.remove(0));
        }
    }
    
    public Board getBoard() {
    	return this.board;
    }
}
