package views;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Game.Gameplay.Dictionary;
import Game.Gameplay.Game;
import Game.Gameplay.GameType;
import Game.Gameplay.Player;
import Game.Tools.ANSI_Color;
import views.gamePanel.BoardPanel;
import views.gamePanel.ControlPanel;

public class GamePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String fileDicoPath = ".."+File.separator+"resources"+File.separator+"Dico.txt";
	
	private static final int DEFAULT_HEIGHT = 600;
	
	private static final int DEFAULT_WIDTH = 800;
	
	private BoardPanel boardPanel;
	
	private ControlPanel controlPanel;

	public GamePanel(GameType gameType, int nbPlayers) {
		
		try {
            Dictionary.load(fileDicoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AppFrame.game = new Game(gameType);
        this.setupPlayers(nbPlayers);
        
        AppFrame.game.setupBoardAndRacks();
        
        this.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        this.initBoardPanel();
        this.initControlPanel();
	}

	private void setupPlayers(int nbPlayers) {
		for(int i = 1; i<nbPlayers+1; i++) {
			if(AppFrame.game.getGameType()==GameType.SCRABBLE || i==1) {
				AppFrame.game.getPlayers().add(new Player("Player "+i, new ArrayList<>(), (i < AppFrame.game.getPlayerColors().size() ? AppFrame.game.getPlayerColors().get(i) : ANSI_Color.RESET)));
			}else {
				AppFrame.game.getPlayers().add(new Player("Player "+i, AppFrame.game.getCommonRack(), (i < AppFrame.game.getPlayerColors().size() ? AppFrame.game.getPlayerColors().get(i) : ANSI_Color.RESET)));
			}
		}
	}

	private void initControlPanel() {
		this.controlPanel = new ControlPanel();
		this.add(this.controlPanel);
	}

	private void initBoardPanel() {
		this.boardPanel = new BoardPanel();
		this.add(this.boardPanel);
	}
}