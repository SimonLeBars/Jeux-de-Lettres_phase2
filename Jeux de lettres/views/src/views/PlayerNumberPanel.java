package views;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Gameplay.GameType;
import listeners.BtnPlayerNumberListener;

public class PlayerNumberPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int MIN_JOUEURS = 2;
	private static int MAX_JOUEURS = 4;
	
	private AppFrame gameFrame;
	
	private JLabel nbJoueursLabel = new JLabel("Combien de joueurs ?");
	
	public PlayerNumberPanel(GameType gameType, AppFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(nbJoueursLabel);
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		
		for(int i = MIN_JOUEURS; i<MAX_JOUEURS+1; i++) {
			JButton btnNbPlayers = new JButton(Integer.toString(i));
			btnNbPlayers.addActionListener(new BtnPlayerNumberListener(i, gameType, gameFrame));
			btnPanel.add(btnNbPlayers);
			btnPanel.add(Box.createRigidArea(new Dimension(0,40)));
		}
		
		this.add(btnPanel);
	}
}
