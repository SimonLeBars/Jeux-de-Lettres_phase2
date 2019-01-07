package listeners.buttonsPanelListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.AppFrame;
import views.GamePanel;

public class BtnPasserListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		GamePanel gamePanel = AppFrame.appframe.gamePanel;
		AppFrame.game.getBoard().retrieveTilesToRack(AppFrame.game.getCurrentPlayer().getRack(),
				gamePanel.getBoardPanel().getPlacedTilesPosition());
		gamePanel.resetPlacedTilesPosition();
		AppFrame.game.getNextPlayer();
		AppFrame.appframe.updateGamePanel();
	}

}
