package views;

import javax.swing.JPanel;

import Game.Gameplay.Game;

public class ControlPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Game game;
	
	public ControlPanel(Game game) {
		this.game = game;
	}
}
