package views;

import java.awt.BorderLayout;
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

public class GamePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String fileDicoPath = ".."+File.separator+"resources"+File.separator+"Dico.txt";

	private AppFrame gameFrame;
	
	private Game game;
	
	private BoardPanel boardPanel;
	
	private ControlPanel controlPanel;

	public GamePanel(AppFrame gameFrame, GameType gameType, int nbPlayers) {
		this.gameFrame = gameFrame;
		
		try {
            Dictionary.load(fileDicoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.game = new Game(gameType);
        this.game.setupBoardAndRacks();
        
        this.setupPlayers(nbPlayers);
        
        this.setSize(new Dimension(800, 600));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        this.initBoardPanel();
        this.initControlPanel();
	}

	private void setupPlayers(int nbPlayers) {
		for(int i = 1; i<nbPlayers+1; i++) {
			if(game.getGameType()==GameType.SCRABBLE || i==1) {
				this.game.getPlayers().add(new Player("Player "+i, new ArrayList<>(), (i < this.game.getPlayerColors().size() ? this.game.getPlayerColors().get(i) : ANSI_Color.RESET)));
			}else {
				this.game.getPlayers().add(new Player("Player "+i, this.game.getCurrentPlayer().getRack(), (i < this.game.getPlayerColors().size() ? this.game.getPlayerColors().get(i) : ANSI_Color.RESET)));
			}
		}
	}

	private void initControlPanel() { 
		this.controlPanel = new ControlPanel(game, this.gameFrame);
		this.add(this.controlPanel);
		
	}

	private void initBoardPanel() {
		this.boardPanel = new BoardPanel(game);
		this.add(this.boardPanel);
	}
}