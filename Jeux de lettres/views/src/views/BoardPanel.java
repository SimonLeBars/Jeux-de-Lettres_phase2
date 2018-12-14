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
	 * true si les images sont chargées
	 */
	private boolean charge = false;
	
	public BoardPanel(Game game) {
		this.game = game;
		
		this.setSize(new Dimension(500, 500));
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
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
		
		//TODO suppr
		game.getBoard().getSquare(3, 3).setTile(new Tile('E', 1));
	}
	
	private Image getCellImage(int i, int j) {
		Square square = this.game.getBoard().getSquare(i, j);
		
		if(square.getTile() != null) return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_"+square.getTile().getCharacter()+".gif");
		
		return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_VIDE.gif");
	}
}
