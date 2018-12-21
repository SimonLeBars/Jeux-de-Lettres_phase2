package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.Gameplay.Game;
import Game.Gameplay.GameType;
import Game.Square.FundoxSquare;
import Game.Square.ScrabbleSquare;
import Game.Square.Square;
import Game.Tile.Tile;
import Game.Tools.ANSI_Color;

public class BoardPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Game game;
	
	private static String FILE_PREFIXE = "resources"+File.separator+"images"+File.separator;
	
	/**
	 * true si les images sont charg�es
	 */
	private boolean charge = false;
	
	public BoardPanel(Game game) {
		this.game = game;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//this.setBackground(Color.GREEN);
		
		this.initCells(g);
	}

	private void initCells(Graphics g) {
		int sideLenght = Math.min(this.getHeight(), this.getWidth());
		int boardSize = this.game.getBoard().getBoardSize();
		
		int squareLenght = (int)sideLenght/boardSize;
		
		Image offscreen = createImage(sideLenght, sideLenght);
		
		//TODO sortir le chargement des images
		for(int i = 0; i<boardSize; i++) {
			for(int j = 0; j<boardSize; j++) {
				
				Image square = getCellImage(i, j);

				//chargement de l'image
				MediaTracker tracker = new MediaTracker(this);
				// attente du chargement via un tracker
				tracker.addImage(offscreen, 0);
				tracker.addImage(square, 0);
				try {
					tracker.waitForID(0);
				} catch (InterruptedException e) {
					System.out.println("erreur tracker "+e);
				}
				if (offscreen==null || offscreen.getWidth(null)<0 || square==null || square.getWidth(null)<0){
					System.out.println("images pas bien chargees");
					charge=false;
				}
				else {
					charge=true;
				}
				
				if(charge) {
					offscreen.getGraphics().drawImage(square, i*squareLenght, j*squareLenght, squareLenght, squareLenght, this);
				}
			}
			
		}
		
		g.drawImage(offscreen, 0, 0, this);
	}
	
	private Image getCellImage(int i, int j) {
		Square square = this.game.getBoard().getSquare(i, j);
		
		System.out.println("getCellImage");
		System.out.println(square.getColor().toString());
		
		if(game.getGameType()==GameType.SCRABBLE) {
			if(square.getTile() != null) return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_"+square.getTile().getCharacter()+".gif");
			else if(i == this.game.getBoard().getBoardSize()/2 && j == this.game.getBoard().getBoardSize()/2) return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_CENTRALE.gif");
			else if(((ScrabbleSquare)square).getWordMultiplier()==3) return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_MOT_COMPTE_TRIPLE.gif");
			else if(((ScrabbleSquare)square).getWordMultiplier()==2) return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_MOT_COMPTE_DOUBLE.gif");
			else if(((ScrabbleSquare)square).getLetterMultiplier()==3) return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_LETTRE_COMPTE_TRIPLE.gif");
			else if(((ScrabbleSquare)square).getLetterMultiplier()==2) return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_LETTRE_COMPTE_DOUBLE.gif");
			else return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_VIDE.gif");
		}else {
			if(square.getTile() != null) return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+Character.toLowerCase(square.getTile().getCharacter())+".gif");
			else if(i == this.game.getBoard().getBoardSize()/2 && j == this.game.getBoard().getBoardSize()/2) return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_CENTRALE_FUNDOX.gif");
			else if(((FundoxSquare)square).gridShouldBeCleared()) return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_ROUGE.gif");
			else if(((FundoxSquare)square).isBonusUsed()) return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_GRIS.gif");
			else return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_JAUNE.gif");
		}
	}
}
