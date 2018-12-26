package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.Gameplay.GameType;
import views.AppFrame;

public class BtnPlayerNumberListener implements ActionListener{

	private int nbPlayers;
	private AppFrame gameFrame;
	private GameType gameType;
	
	public BtnPlayerNumberListener(int nbPlayers, GameType gameType, AppFrame gameFrame) {
		this.nbPlayers = nbPlayers;
		this.gameFrame = gameFrame;
		this.gameType = gameType;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.gameFrame.initGamePanel(this.gameType, this.nbPlayers);
	}

}
