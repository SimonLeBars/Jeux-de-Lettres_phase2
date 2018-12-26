package views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;

import Game.Gameplay.Player;
import Game.Tile.Tile;


public class RackPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String FILE_PREFIXE = "resources"+File.separator+"images"+File.separator;
	
	private static int TILE_SIZE = 20;
	private static int MAX_TILES = 7;
	
	/**
	 * true si les images sont chargées
	 */
	private boolean charge = false;
	
	private Player player;

	public RackPanel(Player player) {
		this.player = player;
		this.setSize(new Dimension(TILE_SIZE*MAX_TILES, TILE_SIZE));
		
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		
		this.initRack();
	}

	private void initRack() {
		ArrayList<Tile> rack = player.getRack();
		
		Image offscreen = createImage(TILE_SIZE*rack.size(), TILE_SIZE);

		for(int i = 0; i<rack.size(); i++) {
			Image tile = getTileImage(i);
			
			//chargement de l'image
			MediaTracker tracker = new MediaTracker(this);
			// attente du chargement via un tracker
			tracker.addImage(offscreen, 0);
			tracker.addImage(tile, 0);
			try {
				tracker.waitForID(0);
			} catch (InterruptedException e) {
				System.out.println("erreur tracker "+e);
			}
			if (offscreen==null || offscreen.getWidth(null)<0 || tile==null || tile.getWidth(null)<0){
				System.out.println("images pas bien chargees");
				charge=false;
			}
			else {
				charge=true;
			}
			
			if(charge) {
				offscreen.getGraphics().drawImage(tile, i*TILE_SIZE, 0, TILE_SIZE, TILE_SIZE, this);
			}
		}
	}

	private Image getTileImage(int i) {
		
		char letter = this.player.getRack().get(i).getCharacter();
		
		if(letter==Tile.JOKER_DEFAULT_CHAR) {
			return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"cur_.gif");
		}else {
			return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"cur_"+String.valueOf(letter).toLowerCase()+".gif");
		}
	}
}
