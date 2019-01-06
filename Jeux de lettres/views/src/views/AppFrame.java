package views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Game.Gameplay.Game;
import Game.Gameplay.GameType;

public class AppFrame extends JFrame{
	
	public static Game game;
	
	public static AppFrame appframe;
	
	public GamePanel gamePanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		AppFrame.appframe = new AppFrame();
	}
	
	public AppFrame() {
		super("Jeux de Lettres");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.initMenuPanel();
		
		this.setVisible(true);
	}
	
	private void relocate() {
		this.repaint();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension gameFrameSize = this.getSize();
		
		int locationX = Math.round((float) ((screenSize.getWidth()-gameFrameSize.getWidth())/2));
		int locationY = Math.round((float) ((screenSize.getHeight()-gameFrameSize.getHeight())/2));
		
		this.setLocation(locationX, locationY);	
	}


	public void initMenuPanel() {
		this.getContentPane().removeAll();
		this.setSize(300, 300);
		this.getContentPane().add(new MenuPanel());
		this.relocate();
	}
	
	public void initPlayerNumberPanel(GameType gameType) {
		this.getContentPane().removeAll();
		this.setSize(300, 150);
		this.getContentPane().add(new PlayerNumberPanel(gameType));
		this.relocate();
	}
	
	public void initGamePanel(GameType gameType, int nbPlayers) {
		this.getContentPane().removeAll();
		this.setSize(800, 600);
		this.gamePanel = new GamePanel(gameType, nbPlayers);
		this.getContentPane().add(gamePanel);
		this.relocate();
	}
	
	public void updateGamePanel() {
		this.gamePanel.updatePanel();
		Dimension gameFrameSize = this.getSize();
		this.setSize(gameFrameSize.width+1, gameFrameSize.height+1);
		this.setSize(gameFrameSize.width, gameFrameSize.height);
	}
}
