import Game.Board.Board;
import Game.Board.FundoxBoard;
import Game.Gameplay.Dictionary;
import Game.Gameplay.Player;
import Game.Tile.Tile;
import Game.Tools.ANSI_Color;
import Game.Tools.Direction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class TestFundoxScrabble {

    public static void main(String[] args) {
        Board b = new FundoxBoard();

        try {
            Dictionary.load("ressources/Dico.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Tile> bag = Tile.getFundoxFrenchTiles();
        bag.sort(Comparator.comparingInt(Tile::getCharacter));
        System.out.println(bag);

        Player p1 = new Player("P1", bag, ANSI_Color.RED);
        Player p2 = new Player("P2", bag, ANSI_Color.GREEN);
        Player p3 = new Player("P3", bag, ANSI_Color.BLUE);
        Player p4 = new Player("P4", bag, ANSI_Color.YELLOW);

        displayGame(b, p1, p2, p3, p4);


        Board.log("Playing CAMIONS");
        b.playWord(6, 1, Direction.HORIZONTAL, "CAMIONS", p1);

        displayGame(b, p1, p2, p3, p4);

        Board.log("Playing ARTISTE");
        b.playWord(6, 2, Direction.VERTICAL, "ARTISTE", p2);

        displayGame(b, p1, p2, p3, p4);

        Board.log("Playing SERVIR");
        b.playWord(10, 2, Direction.HORIZONTAL, "SERVIR", p3);

        displayGame(b, p1, p2, p3, p4);

        Board.log("Playing IRA");
        b.playWord(10, 6, Direction.HORIZONTAL, "IRA", p4);

        displayGame(b, p1, p2, p3, p4);

        Board.log("Playing DICO");
        b.playWord(4, 0, Direction.VERTICAL, "DICO", p1);

        displayGame(b, p1, p2, p3, p4);

        Board.log("Playing ASS");
        b.playWord(10, 0, Direction.HORIZONTAL, "ASS", p2);

        displayGame(b, p1, p2, p3, p4);

        Board.log("Playing BIP");
        b.playWord(5, 0, Direction.HORIZONTAL, "BI", p3);

        displayGame(b, p1, p2, p3, p4);

        Board.log("Playing BONAP");
        b.playWord(1, 2, Direction.VERTICAL, "BONAP", p4);

        displayGame(b, p1, p2, p3, p4);

        Board.log("Playing STUPIDE");
        b.playWord(8, 1, Direction.HORIZONTAL, "STUPIDE", p1);

        displayGame(b, p1, p2, p3, p4);

        Board.log("Playing STUPIDE");
        b.playWord(7, 8, Direction.VERTICAL, "ES", p3);

        displayGame(b, p1, p2, p3, p4);

        Board.log("Playing YS");
        b.playWord(11, 0, Direction.VERTICAL, "YS", p3);

        displayGame(b, p1, p2, p3, p4);



    }

    private static void displayGame(Board b, Player p1, Player p2, Player p3, Player p4) {
        System.out.println(b);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println();
    }
}
