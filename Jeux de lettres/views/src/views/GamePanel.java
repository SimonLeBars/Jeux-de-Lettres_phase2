package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import Game.Gameplay.Dictionary;
import Game.Gameplay.Game;
import Game.Gameplay.GameType;

public class GamePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String fileDicoPath = ".."+File.separator+"resources"+File.separator+"Dico.txt";

	private AppFrame gameFrame;
	
	private Game game;
	
	private BoardPanel boardPanel;

	public GamePanel(AppFrame gameFrame, GameType gameType) {
		this.gameFrame = gameFrame;
		
		try {
            Dictionary.load(fileDicoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.game = new Game(gameType);
        this.game.setupBoardAndRacks();
        
        this.setSize(new Dimension(800, 600));
        this.setLayout(new BorderLayout());
        
        this.initBoardPanel();
        this.initControlPanel();
	}

	private void initControlPanel() {
		ControlPanel controlPanel = new ControlPanel(game);
		this.add(controlPanel, BorderLayout.EAST);
		
	}

	private void initBoardPanel() {
		BoardPanel boardPanel = new BoardPanel(game);
		this.add(boardPanel, BorderLayout.CENTER);
	}
}