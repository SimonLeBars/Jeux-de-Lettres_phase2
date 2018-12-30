package views.gamePanel.controlPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Gameplay.GameType;
import views.AppFrame;

public class ButtonsPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btnValider = new JButton("Valider");
	
	private JButton btnPasser = new JButton("Passer");
	
	private JButton btnPiocher = new JButton("Piocher");
	
	private JButton btnQuitter = new JButton("Quitter");
	
	public ButtonsPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.initBtnValider();
		if(AppFrame.game.getGameType()==GameType.SCRABBLE) {
			this.add(Box.createRigidArea(new Dimension(0,20)));
			this.initBtnPiocher();
		}
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.initBtnPasser();
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.initBtnQuitter();
	}

	private void initBtnPasser() {
		this.add(btnPasser);
	}

	private void initBtnPiocher() {
		this.add(btnPiocher);
	}

	private void initBtnValider() {
		this.add(btnValider);
	}

	private void initBtnQuitter() {
		this.btnQuitter.addActionListener(this);
		this.add(btnQuitter);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnQuitter) {
			AppFrame.appframe.initMenuPanel();
		}
	}
}
