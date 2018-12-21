package views;

import java.util.ArrayList;

import javax.swing.JPanel;

import Game.Gameplay.Player;
import Game.Tile.Tile;


public class RackPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Player player;

	public RackPanel(Player player) {
		this.player = player;
		
		this.initRack();
	}

	private void initRack() {
		ArrayList<Tile> rack = player.getRack();
	}
}
