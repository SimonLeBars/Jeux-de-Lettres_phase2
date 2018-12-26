package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.Gameplay.Game;
import Game.Gameplay.Player;

public class ControlPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Game game;
	
	private AppFrame gameFrame;
	
	private ArrayList<JLabel> scoresLabels = new ArrayList<JLabel>();
	
	private JLabel playerNameLabel = new JLabel();
	
	private RackPanel rackPanel;
	
	private JButton btnQuitter = new JButton("Quitter");
	
	public ControlPanel(Game game, AppFrame gameFrame) {
		this.game = game;
		this.gameFrame =  gameFrame;
		this.rackPanel = new RackPanel(game.getCurrentPlayer());
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		this.initScores();
		this.add(Box.createRigidArea(new Dimension(0,40)));
		this.initPlayerName();
		this.add(Box.createRigidArea(new Dimension(0,40)));
		this.initRackPanel();
		this.add(Box.createRigidArea(new Dimension(0,40)));
		this.initBtnQuitter();
		
		//TODO suppr
		System.out.println(this.getHeight()+" "+this.getWidth());
	}

	private void initRackPanel() {
		this.rackPanel = new RackPanel(this.game.getCurrentPlayer());
		this.add(rackPanel);
	}

	private void initBtnQuitter() {
		this.btnQuitter.addActionListener(this);
		this.add(btnQuitter);
	}

	private void initPlayerName() {
		if(game.getPlayers().size()==0) throw new NullPointerException("Les joueurs ont été mal initialisés");
		
		this.playerNameLabel.setText(game.getCurrentPlayer().getName());
		this.add(this.playerNameLabel);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnQuitter) {
			this.gameFrame.initMenuPanel();
		}
	}
}
