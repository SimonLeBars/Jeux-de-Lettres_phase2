package views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Gameplay.GameType;

public class PlayerNumberPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int MIN_JOUEURS = 2;
	private static int MAX_JOUEURS = 4;
	
	private JLabel nbJoueursLabel = new JLabel("Combien de joueurs ?");
	
	public PlayerNumberPanel(GameType gameType) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(nbJoueursLabel);
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		
		for(int i = MIN_JOUEURS; i<MAX_JOUEURS+1; i++) {
			JButton btnNbJoueurs = new JButton(Integer.toString(i));
			btnPanel.add(btnNbJoueurs);
		}
		
		this.add(btnPanel);
	}
}
