package views.gamePanel.controlPanel;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Gameplay.Game;
import Game.Gameplay.Player;
import views.AppFrame;

public class ScorePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Game game;
	
	private ArrayList<JLabel> scoresLabels = new ArrayList<JLabel>();
	
	public ScorePanel() {
		this.game = AppFrame.game;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.initScores();
	}
	
	private void initScores() {
		this.scoresLabels.add(new JLabel("Scores :"));
		for(Player player : game.getPlayers()) {
			this.scoresLabels.add(new JLabel(player.getName()+" : "+player.getScore()));
		}
		for(JLabel label : this.scoresLabels) {
			this.add(label);
		}
	}
}
