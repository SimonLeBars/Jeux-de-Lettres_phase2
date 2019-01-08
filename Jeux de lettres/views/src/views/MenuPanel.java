package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.Board.Board;
import Game.Board.FundoxBoard;
import Game.Board.ScrabbleBoard;
import Game.Gameplay.Game;
import Game.Gameplay.GameType;

public class MenuPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton btnScrabble = new JButton("Nouvelle partie de Scrabble");
	
	private JButton btnFundox = new JButton("Nouvelle partie de Fundox");
	
	private JButton btnCharger = new JButton("Charger la dernière partie");
	
	private JButton btnQuitter = new JButton("Quitter");
	
	public MenuPanel() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		this.initBtn(btnScrabble);
		this.add(Box.createRigidArea(new Dimension(0,40)));
		this.initBtn(btnFundox);
		this.add(Box.createRigidArea(new Dimension(0,40)));
		this.initBtn(btnCharger);
		this.add(Box.createRigidArea(new Dimension(0,40)));
		this.initBtn(btnQuitter);
	}

	private void initBtn(JButton button) {
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.addActionListener(this);
		
		this.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnQuitter) {
			AppFrame.appframe.dispatchEvent(new WindowEvent(AppFrame.appframe, WindowEvent.WINDOW_CLOSING));
		}
		
		else if(e.getSource()==btnScrabble) {
			AppFrame.appframe.initPlayerNumberPanel(GameType.SCRABBLE);
		}
		
		else if(e.getSource()==btnFundox) {
			AppFrame.appframe.initPlayerNumberPanel(GameType.FUNDOX);
		}
		else if(e.getSource()==btnCharger){
			
			Game game = null;
			
			try(ObjectInputStream inFile = new ObjectInputStream(
					new FileInputStream(AppFrame.GAME_SAVE_PATH))){
				
				game = (Game) inFile.readObject();
					
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Board board = null;
			
			try(ObjectInputStream inFile = new ObjectInputStream(
					new FileInputStream(AppFrame.BOARD_SAVE_PATH))){
				
				if(game != null && game.getGameType()==GameType.SCRABBLE) {
					board = (ScrabbleBoard) inFile.readObject();
				}else if(game!=null) {
					board = (FundoxBoard) inFile.readObject();
				}
				
								
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(game!=null && board !=null) {
				AppFrame.appframe.initGamePanel(game, board);
			}
			
		}
	}
}
