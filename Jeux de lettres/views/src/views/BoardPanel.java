package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.Gameplay.Game;

public class BoardPanel extends JPanel{
	
	private Game game;
	
	private static String FILE_PREFIXE = "Jeux de lettres"+File.separator+"resources"+File.separator+"images"+File.separator;
	
	public BoardPanel(Game game) {
		this.game = game;
		
		this.setSize(new Dimension(500, 500));
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.GREEN);
		//TODO suppr
		/*g.setColor(Color.GREEN);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());*/
		
		this.initCells();
	}

	private void initCells() {
		int heigth = this.getHeight();
		int width = this.getWidth();
		int boardSize = this.game.getBoard().getBoardSize();
		
		int cellHeigth = (int)heigth/boardSize;
		int cellWidth = (int)width/boardSize;
		
		for(int i = 0; i<boardSize; i++) {
			for(int j = 0; j<boardSize; j++) {
				Image offscreen = createImage((int)width/boardSize, (int)heigth/boardSize);
				Image cell = Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_VIDE");
				
				//TODO chargement de l'image
				
				offscreen.getGraphics().drawImage(cell, i*cellHeigth, j*cellWidth, cellWidth, cellHeigth, this);
				
				//TODO suppr
				System.out.println("Cell "+(i*boardSize+j)+" drawn ("+FILE_PREFIXE+"C_VIDE"+")"+" : "+i*cellHeigth+" "+j*cellWidth+" "+cellWidth+" "+cellHeigth);
			}
			
		}
	}
}
