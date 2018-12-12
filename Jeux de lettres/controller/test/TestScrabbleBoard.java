import Game.Board.Board;
import Game.Board.ScrabbleBoard;
import Game.Gameplay.Dictionary;
import Game.Gameplay.Player;
import Game.Tile.Tile;
import Game.Tools.Direction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class TestScrabbleBoard {

    public static void main(String[] args) {
        Board b = new ScrabbleBoard();
        System.out.println(b);
        try {
            Dictionary.load("ressources/Dico.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        ArrayList<Tile> bag = Tile.getScrabbleFrenchTiles();
        bag.sort(Comparator.comparingInt(Tile::getCharacter));
        System.out.println(bag);
        System.out.println(bag.size());
        //Collections.shuffle(bag);

        Player p1 = new Player("Eric-Maxim", bag);

        System.out.println(p1);

        System.out.println("Bag: " + bag);

        Board.log("Playing CANOE");
        b.playWord(7, 7, Direction.HORIZONTAL, "CANOE", p1);
        System.out.println(b);
        System.out.println(p1);
        System.out.println(bag);
        System.out.println();

        ///*
        Board.log("Playing DANSE");
        b.playWord(5, 9, Direction.VERTICAL, "DANSE", p1);
        System.out.println(b);
        System.out.println(p1);
        System.out.println(bag);
        System.out.println();

        Board.log("Playing DESTIN");
        b.playWord(5, 9, Direction.HORIZONTAL, "DESTIN", p1);
        System.out.println(b);
        System.out.println(p1);
        System.out.println(bag);
        System.out.println();

        Board.log("Playing QUELCONQUE");
        b.playWord(3, 7, Direction.VERTICAL, "QUELCONQUE", p1);
        System.out.println(b);
        System.out.println(p1);
        System.out.println(bag);
        System.out.println();

        Board.log("Playing ASQUE");
        b.playWord(12, 3, Direction.HORIZONTAL, "ASQUE", p1);
        System.out.println(b);
        System.out.println(p1);
        System.out.println(bag);
        System.out.println();

        Board.log("Playing B");
        b.playWord(12, 2, Direction.HORIZONTAL, "B", p1);
        System.out.println(b);
        System.out.println(p1);
        System.out.println(bag);
        System.out.println();

        Board.log("Playing BAS");
        b.playWord(12, 2, Direction.HORIZONTAL, "B", p1);
        System.out.println(b);
        System.out.println(p1);
        System.out.println(bag);
        System.out.println();
        /**/

    }
}
