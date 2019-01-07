package views.gamePanel.controlPanel;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.Tile.Tile;
import listeners.exchangeLettersListeners.ExchangeBtnAnnulerListener;
import listeners.exchangeLettersListeners.ExchangeBtnValiderListener;
import listeners.exchangeLettersListeners.ExchangeRackListener;

public class ExchangeLettersFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String FILE_PREFIXE = "resources"+File.separator+"images"+File.separator;
	
	private JButton btnValider = new JButton("Valider");
	
	private JButton btnAnnuler = new JButton("Annuler");
	
	private JPanel rackPanel = new JPanel();
	
	private JPanel btnPanel = new JPanel();
	
	private ArrayList<Tile> selectedTiles = new ArrayList<Tile>();

	public ExchangeLettersFrame(ArrayList<Tile> rack, ArrayList<Tile> bag) {
		
		this.setTitle("Echanger lettres");
		this.setSize(400, 200);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		
		int locationX = Math.round((float) ((screenSize.getWidth()-frameSize.getWidth())/2));
		int locationY = Math.round((float) ((screenSize.getHeight()-frameSize.getHeight())/2));
		
		this.setLocation(locationX, locationY);	
		
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		this.initRackPanel(rack);
		
		this.initBtnPanel();
		
		this.setVisible(true);
	}
	

	private void initBtnPanel() {
		this.btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		
		this.btnValider.addActionListener(new ExchangeBtnValiderListener(this));
		this.btnPanel.add(btnValider);
		
		this.btnAnnuler.addActionListener(new ExchangeBtnAnnulerListener(this));
		this.btnPanel.add(btnAnnuler);
		
		this.getContentPane().add(btnPanel);
	}


	private void initRackPanel(ArrayList<Tile> rack) {
		this.rackPanel.setLayout(new BoxLayout(rackPanel, BoxLayout.X_AXIS));
		
		for(Tile tile : rack) {
			JButton button = new JButton();
			ImageIcon icon = new ImageIcon(getTileImage(tile));
			button.setIcon(icon);
			
			button.setBorder(new EmptyBorder(0, 0, 0, 0));
			
			button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
			
			button.addActionListener(new ExchangeRackListener(this, tile));
			
			this.rackPanel.add(button);
		}
		
		this.getContentPane().add(rackPanel);
	}
	
	private Image getTileImage(Tile tile) {
		
		char letter = tile.getCharacter();
		
		if(letter==Tile.JOKER_DEFAULT_CHAR) {
			return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"cur__.gif");
		}else {
			return Toolkit.getDefaultToolkit().getImage(FILE_PREFIXE+"cur_"+String.valueOf(letter).toLowerCase()+".gif");
		}
	}
	
	public ArrayList<Tile> getSelectedTiles(){
		return this.selectedTiles;
	}
}
