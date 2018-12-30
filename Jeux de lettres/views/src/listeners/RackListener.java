package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.gamePanel.controlPanel.RackPanel;

public class RackListener implements ActionListener{

	private int index;
	
	private RackPanel rackPanel;
	
	public RackListener(int index, RackPanel rackPanel) {
		this.index = index;
		this.rackPanel = rackPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(rackPanel.getSelected()==this.index) {
			this.rackPanel.setSelected(-1);
		}else {
			this.rackPanel.setSelected(this.index);
		}
		this.rackPanel.initRack();
	}

}
