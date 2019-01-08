package listeners.buttonsPanelListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;

import Game.Board.FundoxBoard;
import Game.Board.ScrabbleBoard;
import Game.Gameplay.Game;
import Game.Gameplay.GameType;
import views.AppFrame;
import views.GamePanel;

public class BtnSauvegarderListener implements ActionListener {
	
	public BtnSauvegarderListener() {
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		GamePanel gamePanel = AppFrame.appframe.gamePanel;
		AppFrame.game.getBoard().retrieveTilesToRack(AppFrame.game.getCurrentPlayer().getRack(),
				gamePanel.getBoardPanel().getPlacedTilesPosition());
		gamePanel.resetPlacedTilesPosition();
		AppFrame.appframe.updateGamePanel();
		
		try(ObjectOutputStream write= new ObjectOutputStream (new FileOutputStream(AppFrame.GAME_SAVE_PATH)))
	    {
			
	        write.writeObject(AppFrame.game);
	        
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(ObjectOutputStream write= new ObjectOutputStream (new FileOutputStream(AppFrame.BOARD_SAVE_PATH)))
	    {
			
			if(AppFrame.game.getGameType()==GameType.SCRABBLE) {
				write.writeObject((ScrabbleBoard)(AppFrame.game.getBoard()));
			}else {
				write.writeObject((FundoxBoard)(AppFrame.game.getBoard()));
			}
			
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
