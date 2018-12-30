package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.Gameplay.GameType;

public class MenuPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton btnScrabble = new JButton("Nouvelle partie de Scrabble");
	
	private JButton btnFundox = new JButton("Nouvelle partie de Fundox");
	
	private JButton btnQuitter = new JButton("Quitter");
	
	public MenuPanel() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		this.initBtn(btnScrabble);
		this.add(Box.createRigidArea(new Dimension(0,40)));
		this.initBtn(btnFundox);
		this.add(Box.createRigidArea(new Dimension(0,40)));
		this.initBtn(btnQuitter);
	}

	private void initBtn(JButton button) {
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.addActionListener(this);
		
		this.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnQuitter) {
			AppFrame.appframe.dispatchEvent(new WindowEvent(AppFrame.appframe, WindowEvent.WINDOW_CLOSING));
		}
		
		else if(e.getSource()==btnScrabble) {
			AppFrame.appframe.initPlayerNumberPanel(GameType.SCRABBLE);
		}
		
		else if(e.getSource()==btnFundox) {
			AppFrame.appframe.initPlayerNumberPanel(GameType.FUNDOX);
		}
	}
}
