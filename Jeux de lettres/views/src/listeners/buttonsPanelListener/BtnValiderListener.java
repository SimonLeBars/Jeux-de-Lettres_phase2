package listeners.buttonsPanelListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Game.Board.Board;
import Game.Gameplay.Game;
import Game.Tile.Tile;
import Game.Tools.Direction;
import Game.Tools.Index2D;
import views.AppFrame;
import views.GamePanel;

public class BtnValiderListener implements ActionListener{
	
	private GamePanel gamePanel;
	
	public BtnValiderListener(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<Index2D> placedTilesPosition = this.gamePanel.getBoardPanel().getPlacedTilesPosition();
		
		if(placedTilesPosition.size()<AppFrame.game.getBoard().MIN_LETTER_PLACED_COUNT()) {
			this.showWarn("Pas assez de lettres placées");
			this.cancelTurn();
			return;
		}
		
		if(this.sameLine(placedTilesPosition)) {
			this.findWordLine();
		}else if(this.sameColumn(placedTilesPosition)) {
			this.findWordColumn();
		}else{
			this.showWarn("Les lettres ne sont pas sur la même ligne ou la même colonne");
			
			this.cancelTurn();
			return;
		}
	}

	private void cancelTurn() {
		AppFrame.game.getBoard().retrieveTilesToRack(AppFrame.game.getCurrentPlayer().getRack(),
				this.gamePanel.getBoardPanel().getPlacedTilesPosition());
		this.gamePanel.resetPlacedTilesPosition();
		AppFrame.game.getNextPlayer();
		AppFrame.appframe.updateGamePanel();
	}

	private void findWordColumn() {
		ArrayList<Index2D> placedTilesPosition = this.gamePanel.getBoardPanel().getPlacedTilesPosition();
		
		int min = placedTilesPosition.get(0).LINE;
		int max = placedTilesPosition.get(0).LINE;
		
		for(Index2D location : placedTilesPosition) {
			if(location.LINE<min) min = location.LINE;
			if(location.LINE>max) max = location.LINE;
		}
		
		Board board = AppFrame.game.getBoard();
		while(min>0 && !board.getSquare(min-1, placedTilesPosition.get(0).COLUMN).isEmpty()) {
			min--;
		}
		while(max<board.getBoardSize()-1 && !board.getSquare(placedTilesPosition.get(0).COLUMN, max+1).isEmpty()) {
			max++;
		}
		
		this.verifyWord(min, placedTilesPosition.get(0).COLUMN, max, Direction.VERTICAL);
	}

	private void findWordLine() {
		ArrayList<Index2D> placedTilesPosition = this.gamePanel.getBoardPanel().getPlacedTilesPosition();
		
		int min = placedTilesPosition.get(0).COLUMN;
		int max = placedTilesPosition.get(0).COLUMN;
		
		for(Index2D location : placedTilesPosition) {
			if(location.COLUMN<min) min = location.COLUMN;
			if(location.COLUMN>max) max = location.COLUMN;
		}
		
		Board board = AppFrame.game.getBoard();
		while(min>0 && !board.getSquare(placedTilesPosition.get(0).LINE, min-1).isEmpty()) {
			min--;
		}
		while(max<board.getBoardSize()-1 && !board.getSquare(placedTilesPosition.get(0).LINE, max+1).isEmpty()) {
			max++;
		}
		
		this.verifyWord(placedTilesPosition.get(0).LINE, min, max, Direction.HORIZONTAL);
	}

	private void verifyWord(int line, int column, int max, Direction direction) {
		// TODO verifier => pas de trou entre les lettres
		// + mot valide (voir cas des jokers)
		// + lancer l'ajout des points
		
		int i = -1;
		if(direction==Direction.HORIZONTAL) {
			i = column;
		}else {
			i = line;
		}
		
		Board board = AppFrame.game.getBoard();
		
		if(direction==Direction.HORIZONTAL) {
			while(i<max && !board.getSquare(line, i).isEmpty()) {
				i++;
			}
			if(board.getSquare(line, i).isEmpty()) {
				//TODO
				this.showWarn("Vous ne pouvez placer qu'un seul mot par tour");
				this.cancelTurn();
				return;
			}
		}else {
			while(i<max && !board.getSquare(i, column).isEmpty()) {
				i++;
			}
			if(board.getSquare(i, column).isEmpty()) {
				//TODO
				this.showWarn("Vous ne pouvez placer qu'un seul mot par tour");
				this.cancelTurn();
				return;
			}
		}
		
		ArrayList<Index2D> placedTiles = this.gamePanel.getBoardPanel().getPlacedTilesPosition();
		
		if(!this.wordLinkedToMiddleSquare(line, column, max, direction)) {
			this.showWarn("Le mot n'est pas relié au centre du plateau");
			this.cancelTurn();
			return;
		}
		
		if(!board.areWordsCreatedAreCorrect(placedTiles, direction)) {
			this.showWarn("Mot(s) invalide");
			this.cancelTurn();
			return;
		}
		
		board.countPoints(line, column, direction, placedTiles, AppFrame.game.getCurrentPlayer());
		
		Game game = AppFrame.game;
		ArrayList<Tile> rack = game.getCurrentPlayer().getRack();
		ArrayList<Tile> bag = game.getBag();
		int tileAmount = this.gamePanel.getPlacedTilesPosition().size();
		game.refillRack(rack, bag, tileAmount);
		
		this.gamePanel.resetPlacedTilesPosition();
		
		game.getNextPlayer();
		AppFrame.appframe.updateGamePanel();
	}

	private boolean wordLinkedToMiddleSquare(int line, int column, int max, Direction direction) {
		
		Board board = AppFrame.game.getBoard();
		ArrayList<Index2D> placedTiles = this.gamePanel.getBoardPanel().getPlacedTilesPosition();
		
		if(direction == Direction.HORIZONTAL) {
			for(int i = column; i<=max; i++) {
				if(idOnMiddleSquare(line, i, board) 
						|| !isInPlacedTiles(line,i, placedTiles)) return true;
				if(line>0 && !board.getSquare(line-1, i).isEmpty()) return true;
				if(line<board.getBoardSize()-1 && ! board.getSquare(line+1, i).isEmpty()) return true;
			}
		}else {
			for(int i = column; i<=max; i++) {
				if(idOnMiddleSquare(i, column, board) 
						|| !isInPlacedTiles(i,column, placedTiles)) return true;
				if(column>0 && !board.getSquare(i, column-1).isEmpty()) return true;
				if(column<board.getBoardSize()-1 && ! board.getSquare(i, column+1).isEmpty()) return true;
			}
		}
		
		return false;
	}
	
	private boolean idOnMiddleSquare(int i, int j, Board board) {
		return i==j && i==Math.floorDiv(board.getBoardSize(), 2);
	}

	private boolean isInPlacedTiles(int i, int j, ArrayList<Index2D> placedTiles) {
		for(Index2D location : placedTiles) {
			if(i==location.LINE && j==location.COLUMN) return true;
		}
		
		return false;
	}

	private boolean sameColumn(ArrayList<Index2D> placedTilesPosition) {		
		int column = placedTilesPosition.get(0).COLUMN;
		for(Index2D location : placedTilesPosition) {
			if(location.COLUMN!=column) return false;
		}
		return true;
	}

	private boolean sameLine(ArrayList<Index2D> placedTilesPosition) {
		if(placedTilesPosition.size() == 0) return false;
		
		int line = placedTilesPosition.get(0).LINE;
		for(Index2D location : placedTilesPosition) {
			if(location.LINE!=line) return false;
		}
		return true;
	}

	private void showWarn(String msg) {
		JOptionPane.showMessageDialog(this.gamePanel.getBoardPanel(), msg+"\nTour suivant...", "Attention", JOptionPane.WARNING_MESSAGE);
	}
}
