package controller;

import java.io.File;

import utils.StreamFileUtils;
import model.PlayerModel;
import boundary.PlayerApplication;
import boundary.PlayerEndLevelView;

/**
 * PlayerEndLevelCtrl handles what occurs at the end of a level.
 *
 * @author Bailey Sheridan, and Eli Skeggs
 */
public class PlayerEndLevelCtrl implements Runnable {
  PlayerApplication app;
  PlayerModel model;
  boolean isComplete;
  public static final File progressfile = PlayerLoadProgressCtrl.progressfile;
  public static final File progressdir = PlayerLoadProgressCtrl.progressdir;

  /**
   * Creates a new PlayerEndLevelCtrl.
   *
   * @param app
   * @param model
   */
  public PlayerEndLevelCtrl(PlayerApplication app, PlayerModel model, boolean isComplete) {
    this.app = app;
    this.model = model;
    this.isComplete = isComplete;
  }

  /**
   * Get the message associated with the threshold the user passed.
   *
   * @return The message for the corresponding score threshold.
   */
  protected String getWonMessage() {
    int[] thresholds = model.level.rules.scoreThresholds;

    if (thresholds.length > 0) {
      for (int i = thresholds.length - 1; i > 0; i--) {
        if (model.score > thresholds[i] && isComplete) {
          return " You got " + (i + 1) + (i == 0 ? " star!" : " stars!");
        }
      }

      if(!isComplete)
    	  return "Didn't complete level objective. :(";
      return "You didn't pass the score thresholds.";
    }

    return "";
  }

  /**
   * Ends the level via pop-up window.
   */
  public void endLevel() {
    // always set the achieved score, even if we haven't crossed a threshold
    model.progress.setAchievedScore(model.levelnum, model.score, isComplete);

    // writes progress to file
    if (!model.disableProgress) {
      if (StreamFileUtils.ensureParent(progressfile)) {
        if (!StreamFileUtils.writeStream(progressfile, model.progress)) {
          System.err.println("Unable to save progress");
        }
      } else {
        System.err.println("Your progress will not be saved");
      }
    }

    PlayerEndLevelView endView = new PlayerEndLevelView(app, model);

    endView.openDialog(getWonMessage());
  }

  /**
   * Allow this to be deferred, calls endLevel.
   */
  @Override
  public void run() {
    endLevel();
  }
}
