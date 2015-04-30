package controller;

import model.PlayerModel;
import boundary.PlayerApplication;
import boundary.PlayerEndLevelView;

/**
 * PlayerEndLevelCtrl handles what occurs at the end of a level.
 * @author Kyra
 *
 */
public class PlayerEndLevelCtrl {

	PlayerApplication app;
	PlayerModel model;
	
	public PlayerEndLevelCtrl(PlayerApplication app, PlayerModel model) {
		this.app = app;
		this.model = model;
	}
	
	public void endLevel() {		
		PlayerEndLevelView endView = new PlayerEndLevelView(app, model);
		
		String endMsg = "You won! Good job! You got ";
		
		if(model.score < model.level.rules.scoreThresholds[0])
			endMsg = "You didn't pass.";
		else if(model.score < model.level.rules.scoreThresholds[1]) {
			endMsg += "1 Star";
			model.progress.setAchievedScore(1, model.score);
		}
		else if(model.score < model.level.rules.scoreThresholds[2]) {
			endMsg += "2 Stars!";
			model.progress.setAchievedScore(1, model.score);
		}
		else {
			endMsg += "3 Stars!!";
			model.progress.setAchievedScore(1, model.score);
		}
		
		endView.openDialog(endMsg);
	}
}
