package views;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

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

	private AppFrame gameFrame;
	
	private JButton btnScrabble = new JButton("Nouvelle partie de Scrabble");
	
	private JButton btnFundox = new JButton("Nouvelle partie de Fundox");
	
	private JButton btnQuitter = new JButton("Quitter");
	
	//TODO suppr
	private static String FILE_PREFIXE = ".."+File.separator+"Jeux de lettres"+File.separator+"resources"+File.separator+"images"+File.separator;

	
	public MenuPanel(AppFrame gameFrame) {
		this.gameFrame = gameFrame;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		this.initBtn(btnScrabble);
		this.add(Box.createRigidArea(new Dimension(0,40)));
		this.initBtn(btnFundox);
		this.add(Box.createRigidArea(new Dimension(0,40)));
		this.initBtn(btnQuitter);
		
		//TODO suppr debut
		Image offscreen = createImage(100, 100);
		Image cell = Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"C_VIDE");
		
		//TODO chargement de l'image
		
		offscreen.getGraphics().drawImage(cell, 0, 0, 100, 100, this);
		//TODO suppr fin
	}

	private void initBtn(JButton button) {
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.addActionListener(this);
		
		this.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnQuitter) {
			gameFrame.dispatchEvent(new WindowEvent(gameFrame, WindowEvent.WINDOW_CLOSING));
		}
		
		else if(e.getSource()==btnScrabble) {
			gameFrame.initGamePanel(GameType.SCRABBLE);
		}
		
		else if(e.getSource()==btnFundox) {
			gameFrame.initGamePanel(GameType.FUNDOX);
		}
	}
}
