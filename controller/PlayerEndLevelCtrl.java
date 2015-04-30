package controller;

import javax.swing.JOptionPane;

import model.PlayerModel;
import boundary.PlayerApplication;
import boundary.PlayerEndLevelView;

public class PlayerEndLevelCtrl {

	PlayerApplication app;
	PlayerModel model;
	
	public PlayerEndLevelCtrl(PlayerApplication app, PlayerModel model) {
		this.app = app;
		this.model = model;
	}
	
	public void endLevel() {		
		PlayerEndLevelView endView = new PlayerEndLevelView(app, model);
		
		/*JOptionPane jop = new JOptionPane();
		String[] s = { "Level Select" };
		jop.setOptions(s);
		JOptionPane.showMessageDialog(jop, endMsg, "Level Finished", JOptionPane.PLAIN_MESSAGE);*/
	}
}
