package listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import Game.Gameplay.Player;
import Game.Tile.Tile;
import Game.Tools.Index2D;
import views.AppFrame;
import views.GamePanel;
import views.gamePanel.BoardPanel;
import views.gamePanel.controlPanel.RackPanel;

public class BoardListener implements MouseListener{
	
	private GamePanel gamePanel;
	
	public BoardListener(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX()+":"+e.getY());
		
		RackPanel rackPanel = gamePanel.getControlPanel().getRackPanel();
		BoardPanel boardPanel = gamePanel.getBoardPanel();
		
		int selected = rackPanel.getSelected();
		ArrayList<Tile> rack = AppFrame.game.getCurrentPlayer().getRack();
		int boardSize = AppFrame.game.getBoard().getBoardSize();
		int sideLenght = Math.min(boardPanel.getHeight(), boardPanel.getWidth());
		int line = Math.floorDiv(e.getY(), sideLenght/boardSize);
		int column = Math.floorDiv(e.getX(), sideLenght/boardSize);
		
		if(selected == -1) {
			if(!AppFrame.game.getBoard().getSquare(line, column).isEmpty()) {
				this.retrieveTileToRack(line, column);
			}
		}
		else if(line<boardSize && column<boardSize &&
				AppFrame.game.getBoard().getSquare(line, column).isEmpty()) {
			this.gamePanel.getPlacedTilesPosition().add(new Index2D(line, column));
			AppFrame.game.getBoard().playTile(line, column, rack.get(selected));
			rack.remove(selected);
			System.out.println("Tile added : selected = "
			+selected+", location = "+line+":"+column);
			rackPanel.setSelected(-1);
		}
		
		this.gamePanel.getBoardPanel().repaint();
		AppFrame.appframe.updateGamePanel();
	}

	private void retrieveTileToRack(int line, int column) {
		ArrayList<Index2D> placedTiles = AppFrame.appframe.gamePanel.getPlacedTilesPosition();
		for(Index2D location : placedTiles) {
			if(location.LINE == line && location.COLUMN == column) {
				
				
				ArrayList<Tile> rack = AppFrame.game.getCurrentPlayer().getRack();
				ArrayList<Index2D> tile = new ArrayList<Index2D>();
				tile.add(new Index2D(line, column));
				AppFrame.game.getBoard().retrieveTilesToRack(rack, tile);
				
				placedTiles.remove(location);
				
				AppFrame.appframe.updateGamePanel();
				return;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
