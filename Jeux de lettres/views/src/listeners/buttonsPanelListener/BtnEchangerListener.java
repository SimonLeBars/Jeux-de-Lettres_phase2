package listeners.buttonsPanelListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Game.Gameplay.Game;
import Game.Tile.Tile;
import Game.Tools.Index2D;
import views.AppFrame;
import views.GamePanel;
import views.gamePanel.controlPanel.ExchangeLettersFrame;

public class BtnEchangerListener implements ActionListener{

	private ArrayList<Tile> rack;
	
	private ArrayList<Tile> bag;
	
	public BtnEchangerListener(ArrayList<Tile> rack, ArrayList<Tile> bag) {
		this.rack = rack;
		this.bag = bag;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GamePanel gamePanel = AppFrame.appframe.gamePanel;
		Game game = AppFrame.game;
		ArrayList<Tile> rack = game.getCurrentPlayer().getRack();
		ArrayList<Index2D> placedTiles = gamePanel.getPlacedTilesPosition();
		game.getBoard().retrieveTilesToRack(rack, placedTiles);
		gamePanel.resetPlacedTilesPosition();
		
		AppFrame.appframe.updateGamePanel();
		
		new ExchangeLettersFrame(rack, bag);
	}

}
