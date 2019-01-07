package listeners.exchangeLettersListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import Game.Gameplay.Game;
import Game.Tile.Tile;
import Game.Tools.Index2D;
import views.AppFrame;
import views.GamePanel;
import views.gamePanel.controlPanel.ExchangeLettersFrame;

public class ExchangeBtnValiderListener implements ActionListener{
	
	private ExchangeLettersFrame exchangeLettersFrame;
	
	public ExchangeBtnValiderListener(ExchangeLettersFrame exchangeLettersFrame) {
		this.exchangeLettersFrame = exchangeLettersFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Game game = AppFrame.game;
		
		for(Tile tile : exchangeLettersFrame.getSelectedTiles()) {
			if(game.getCurrentPlayer().getRack().remove(tile)) {
				game.getBag().add(tile);
			}else {
				throw new Error("Erreur pour enlever une lettre du ratelier");
			}
		}
		game.refillRack(game.getCurrentPlayer().getRack(), game.getBag(),
				exchangeLettersFrame.getSelectedTiles().size());
		
		exchangeLettersFrame.dispatchEvent(new WindowEvent(exchangeLettersFrame, WindowEvent.WINDOW_CLOSING));
		
		AppFrame.appframe.updateGamePanel();
		
		game.getNextPlayer();
		AppFrame.appframe.updateGamePanel();
	}

}
