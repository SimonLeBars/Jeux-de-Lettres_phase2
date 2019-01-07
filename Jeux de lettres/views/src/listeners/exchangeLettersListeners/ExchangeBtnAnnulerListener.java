package listeners.exchangeLettersListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import views.AppFrame;
import views.gamePanel.controlPanel.ExchangeLettersFrame;

public class ExchangeBtnAnnulerListener implements ActionListener{

	private ExchangeLettersFrame exchangeLettersFrame;
	
	public ExchangeBtnAnnulerListener(ExchangeLettersFrame exchangeLettersFrame) {
		this.exchangeLettersFrame = exchangeLettersFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		exchangeLettersFrame.dispatchEvent(new WindowEvent(exchangeLettersFrame, WindowEvent.WINDOW_CLOSING));
	}
	
}
