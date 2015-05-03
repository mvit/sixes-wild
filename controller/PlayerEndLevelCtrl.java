package controller;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
  public static final File progressfile = PlayerLoadProgressCtrl.progressfile;
  public static final File progressdir = PlayerLoadProgressCtrl.progressdir;

  /**
   * Creates a new PlayerEndLevelCtrl.
   *
   * @param app
   * @param model
   */
  public PlayerEndLevelCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * Get the message associated with the threshold the user passed.
   *
   * @return The message for the corresponding score threshold.
   */
  protected String getWonMessage() {
    int[] thresholds = model.level.rules.scoreThresholds;

    if (thresholds.length > 0) {
      for (int i = thresholds.length - 1; i > 0; i++) {
        if (model.score > thresholds[i]) {
          return " You got " + (i + 1) + (i == 0 ? " star!" : " stars!");
        }
      }

      return " You didn't pass.";
    }

    return "";
  }

  /**
   * Ends the level via pop-up window.
   */
  public void endLevel() {
    // always set the achieved score, even if we haven't crossed a threshold
    model.progress.setAchievedScore(model.levelnum, model.score);

    // writes progress to file
    if (progressdir.mkdirs() || progressdir.isDirectory()) {
      DataOutputStream out = null;
      try {
        out = new DataOutputStream(new FileOutputStream(progressfile));
        model.progress.write(out);
      } catch (IOException err) {
        System.err.println("Unable to save progress");
        System.err.println(err.getMessage());
        err.printStackTrace();
      } finally {
        try {
          if (out != null) {
            out.close();
          }
        } catch (IOException err) {
          System.err.println(err.getMessage());
          err.printStackTrace();
        }
      }
    } else {
      System.err.println("Unable to create the appropriate directory structure,"
        + "your progress will not be saved");
    }

    PlayerEndLevelView endView = new PlayerEndLevelView(app, model);

    endView.openDialog("You won! Good job!" + getWonMessage());
  }

  /**
   * Allow this to be deferred, calls endLevel.
   */
  @Override
  public void run() {
    endLevel();
  }
}
