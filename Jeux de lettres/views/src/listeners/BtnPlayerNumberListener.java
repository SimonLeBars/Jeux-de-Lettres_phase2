package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.Gameplay.GameType;
import views.AppFrame;

public class BtnPlayerNumberListener implements ActionListener{

	private int nbPlayers;
	private GameType gameType;
	
	public BtnPlayerNumberListener(int nbPlayers, GameType gameType) {
		this.nbPlayers = nbPlayers;
		this.gameType = gameType;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AppFrame.appframe.initGamePanel(this.gameType, this.nbPlayers);
	}

}
