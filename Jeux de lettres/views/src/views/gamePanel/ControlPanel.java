package views.gamePanel;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Game.Gameplay.Game;
import views.AppFrame;
import views.GamePanel;
import views.gamePanel.controlPanel.ButtonsPanel;
import views.gamePanel.controlPanel.RackPanel;
import views.gamePanel.controlPanel.ScorePanel;

public class ControlPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Game game;
	
	private GamePanel gamePanel;
	
	private ScorePanel scorePanel;
	
	private RackPanel rackPanel;
	
	private ButtonsPanel buttonsPanel;
	
	public ControlPanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.game = AppFrame.game;
		this.rackPanel = new RackPanel(game.getCurrentPlayer(), this.gamePanel.getBoardPanel().getGamePanel());
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.initScorePanel();
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.initRackPanel();
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.initButtonsPanel();
	}

	private void initButtonsPanel() {
		this.buttonsPanel = new ButtonsPanel(this.gamePanel);
		this.add(buttonsPanel);
	}

	private void initScorePanel() {
		this.scorePanel = new ScorePanel();
		this.add(scorePanel);
	}

	private void initRackPanel() {
		this.rackPanel = new RackPanel(this.game.getCurrentPlayer(), this.gamePanel.getBoardPanel().getGamePanel());
		this.add(rackPanel);
	}
	
	public RackPanel getRackPanel() {
		return this.rackPanel;
	}
	
	public void setRackPanel(RackPanel rackPanel) {
		this.rackPanel = rackPanel;
	}
	
	public ScorePanel getScorePanel() {
		return this.scorePanel;
	}
}
