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
		String endMsg = "You won! Good job.";
		if(model.level.currentScore < model.level.rules.scoreThresholds[0])
			endMsg = "You didn't pass!";
		else if(model.level.currentScore < model.level.rules.scoreThresholds[1])
			endMsg += " 1 Star";
		else if(model.level.currentScore < model.level.rules.scoreThresholds[2])
			endMsg += " 2 Star!";
		else
			endMsg += " 3 Star!!";
		
		//JOptionPane.showMessageDialog(null, "Level Complete!", new PlayerEndLevelView(app, model));
		JOptionPane jop = new JOptionPane();
		String[] s = { "Level Select" };
		jop.setOptions(s);
		JOptionPane.showMessageDialog(jop, endMsg, "Level Finished", JOptionPane.PLAIN_MESSAGE);
	}
}
