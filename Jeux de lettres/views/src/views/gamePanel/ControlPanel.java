package views.gamePanel;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Game.Gameplay.Game;
import views.AppFrame;
import views.gamePanel.controlPanel.ButtonsPanel;
import views.gamePanel.controlPanel.RackPanel;
import views.gamePanel.controlPanel.ScorePanel;

public class ControlPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Game game;
	
	private ScorePanel scorePanel;
	
	private RackPanel rackPanel;
	
	private ButtonsPanel buttonsPanel;
	
	public ControlPanel() {
		this.game = AppFrame.game;
		this.rackPanel = new RackPanel(game.getCurrentPlayer());
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.initScorePanel();
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.initRackPanel();
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.initButtonsPanel();
	}

	private void initButtonsPanel() {
		this.buttonsPanel = new ButtonsPanel();
		this.add(buttonsPanel);
	}

	private void initScorePanel() {
		this.scorePanel = new ScorePanel();
		this.add(scorePanel);
	}

	private void initRackPanel() {
		this.rackPanel = new RackPanel(this.game.getCurrentPlayer());
		this.add(rackPanel);
	}
}
