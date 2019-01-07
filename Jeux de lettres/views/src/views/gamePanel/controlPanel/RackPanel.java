package views.gamePanel.controlPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Game.Gameplay.Player;
import Game.Tile.Tile;
import listeners.RackListener;
import views.GamePanel;
import views.gamePanel.BoardPanel;


public class RackPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String FILE_PREFIXE = "resources"+File.separator+"images"+File.separator;
	
	private Player player;
	
	private JLabel playerNameLabel = new JLabel();
	
	private JPanel rack;
	
	private GamePanel gamePanel;
	
	private int selected = -1;
	
	private ArrayList<JButton> tilesBtn;

	public RackPanel(Player player, GamePanel gamePanel) {
		
		this.gamePanel = gamePanel;
		this.player = player;
		this.tilesBtn = new ArrayList<JButton>();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.initPlayerName();
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.initRack();
		this.add(rack);
	}

	public void initRack() {
		this.rack = new JPanel();
		this.rack.setLayout(new BoxLayout(rack, BoxLayout.X_AXIS));
		
		ArrayList<Tile> playerRack = player.getRack();

		for(int i = 0; i<playerRack.size(); i++) {
			
			JButton button = new JButton();
			ImageIcon icon = new ImageIcon(getTileImage(i));
			button.setIcon(icon);
			if(i == this.selected) {
				button.setBorder(new LineBorder(Color.BLUE, 5));
			}else {
				button.setBorder(new EmptyBorder(0, 0, 0, 0));
			}
			button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
			button.addActionListener(new RackListener(i, this));
			this.tilesBtn.add(button);
			this.rack.add(button);
		}
	}
	
	private void initPlayerName() {
		this.playerNameLabel.setText(player.getName());
		this.add(this.playerNameLabel);
	}

	private Image getTileImage(int i) {
		
		char letter = this.player.getRack().get(i).getCharacter();
		
		if(letter==Tile.JOKER_DEFAULT_CHAR) {
			return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"cur__.gif");
		}else {
			return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"cur_"+String.valueOf(letter).toLowerCase()+".gif");
		}
	}
	
	@Override
	public void repaint() {
		super.repaint();
		
		if(tilesBtn!=null) {
			for(int i = 0; i<tilesBtn.size(); i++) {
				if(i == this.selected) {
					tilesBtn.get(i).setBorder(new LineBorder(Color.BLUE, 5));
				}else {
					tilesBtn.get(i).setBorder(new EmptyBorder(0, 0, 0, 0));
				}
			}
		}
		
	}
	
	public int getSelected() {
		return this.gamePanel.getSelected();
	}
	
	public void setSelected(int i) {
		this.gamePanel.setSelected(i);
	}
}
