package views.gamePanel.controlPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.ControllerEventListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Gameplay.GameType;
import listeners.buttonsPanelListener.BtnEchangerListener;
import listeners.buttonsPanelListener.BtnValiderListener;
import views.AppFrame;
import views.GamePanel;
import views.gamePanel.BoardPanel;
import views.gamePanel.ControlPanel;

public class ButtonsPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GamePanel gamePanel;

	private JButton btnValider = new JButton("Valider");
	
	private JButton btnPasser = new JButton("Passer");
	
	private JButton btnEchanger = new JButton("Echanger lettres");
	
	private JButton btnQuitter = new JButton("Quitter");
	
	public ButtonsPanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.initBtnValider();
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.initBtnEchanger();
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.initBtnPasser();
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.initBtnQuitter();
	}

	private void initBtnPasser() {
		this.add(btnPasser);
	}

	private void initBtnEchanger() {
		this.btnEchanger.addActionListener(new BtnEchangerListener(
				AppFrame.game.getCurrentPlayer().getRack(),
				AppFrame.game.getBag()));
		this.add(btnEchanger);
	}

	private void initBtnValider() {
		this.btnValider.addActionListener(new BtnValiderListener(gamePanel));
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
