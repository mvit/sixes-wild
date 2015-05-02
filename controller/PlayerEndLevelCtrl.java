package controller;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import model.PlayerModel;
import model.PlayerProgress;
import boundary.PlayerApplication;
import boundary.PlayerEndLevelView;

/**
 * PlayerEndLevelCtrl handles what occurs at the end of a level.
 * @author Bailey Sheridan
 *
 */
public class PlayerEndLevelCtrl {

	PlayerApplication app;
	PlayerModel model;
	public static final File progressfile = new File("resource/progress/progress");
	public static final File progressdir = new File("resource/progress");
	
	/**
	 * Creates a new PlayerEndLevelCtrl.
	 * @param app
	 * @param model
	 */
	public PlayerEndLevelCtrl(PlayerApplication app, PlayerModel model) {
		this.app = app;
		this.model = model;
	}
	
	/**
	 * Ends the level via pop-up window.
	 */
	public void endLevel() {		
		PlayerEndLevelView endView = new PlayerEndLevelView(app, model);
		
		String endMsg = "You won! Good job! You got ";
		
		// Sets end message.
		if(model.score < model.level.rules.scoreThresholds[0])
			endMsg = "You didn't pass.";
		else if(model.score < model.level.rules.scoreThresholds[1]) {
			endMsg += "1 Star";
			model.progress.setAchievedScore(model.levelnum, model.score);
		}
		else if(model.score < model.level.rules.scoreThresholds[2]) {
			endMsg += "2 Stars!";
			model.progress.setAchievedScore(model.levelnum, model.score);
		}
		else {
			endMsg += "3 Stars!!";
			model.progress.setAchievedScore(model.levelnum, model.score);
		}
		
		// Writes progress to file.
		if (!progressdir.mkdirs() && !progressdir.isDirectory()) {
		      System.err.println("Unable to create the appropriate directory structure,"
		        + "your progress will not be saved");
		      return;
		    }

		    if (!progressfile.isFile()) {
		      model.progress = new PlayerProgress();
		      try {
		        model.progress.write(new DataOutputStream(new FileOutputStream(
		          progressfile)));
		      } catch (IOException err) {
		        System.err.println("Unable to save progress");
		      }
		    }
		
		endView.openDialog(endMsg);
	}
}
