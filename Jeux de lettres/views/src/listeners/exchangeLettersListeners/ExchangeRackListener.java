package listeners.exchangeLettersListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Game.Tile.Tile;
import views.gamePanel.controlPanel.ExchangeLettersFrame;

public class ExchangeRackListener implements ActionListener{

	private Tile tile;
	
	private ExchangeLettersFrame exchangeLettersFrame;
	
	public ExchangeRackListener(ExchangeLettersFrame exchangeLettersFrame, Tile tile) {
		this.tile = tile;
		
		this.exchangeLettersFrame = exchangeLettersFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.exchangeLettersFrame.getSelectedTiles().contains(tile)) {
			this.exchangeLettersFrame.getSelectedTiles().remove(tile);
		}else {
			this.exchangeLettersFrame.getSelectedTiles().add(tile);
		}
	}

}
