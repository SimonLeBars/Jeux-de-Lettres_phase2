package listeners.buttonsPanelListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Game.Tile.Tile;
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
		new ExchangeLettersFrame(rack, bag);
	}

}
