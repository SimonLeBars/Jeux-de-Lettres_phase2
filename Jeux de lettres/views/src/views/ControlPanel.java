package views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Gameplay.Game;

public class ControlPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Game game;
	
	private JLabel scores = new JLabel();
	
	private JLabel playerName = new JLabel();
	
	private RackPanel rackPanel;
	
	private JButton btnQuitter = new JButton("Quitter");
	
	public ControlPanel(Game game) {
		this.game = game;
		this.rackPanel = new RackPanel(game.getCurrentPlayer());
		
		this.initScores();
		this.initPlayerName();
		this.initBtnQuitter();
	}

	private void initBtnQuitter() {
		this.add(btnQuitter);
	}

	private void initPlayerName() {
		// TODO Auto-generated method stub
		
	}

	private void initScores() {
		// TODO Auto-generated method stub
		
	}
}
