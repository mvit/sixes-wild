package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import model.PlayerModel;
import model.PlayerProgress;
import boundary.PlayerApplication;

/**
 * Loads the player's progress from the progress file.
 *
 * Unlike the majority of controllers in sixes-wild, users will not directly
 * invoke this controller. Instead, the PlayerApplication will automatically
 * create and run this controller on startup, to load the player progress.
 *
 * Despite this difference from most controllers, this controller still receives
 * the top-level boundary, in the event that a saving icon is included in the
 * future.
 *
 * @author Eli Skeggs
 */
public class PlayerLoadProgressCtrl {
  public static final File progressdir = new File("resource/progress");
  public static final String progresspath = progressdir.getAbsolutePath();
  public static final File progressfile = new File("resource/progress/progress");
  public static final String progressfilepath = progressfile.getAbsolutePath();

  PlayerApplication app;
  PlayerModel model;

  /**
   * Create a PlayerLoadProgressCtrl.
   *
   * @constructor
   * @param app
   * @param model
   */
  public PlayerLoadProgressCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * This method loads the player's progress from a file. If the file does not
   * exist, it creates a new file with no player progress. If the file is
   * corrupted, it creates a backup, and then overwrites the original file with
   * an empty user progress file.
   *
   * TODO: clean up control flow.
   */
  public void loadProgress() {
    if (!progressdir.mkdirs() && !progressdir.isDirectory()) {
      // TODO: dialog?
      System.err.println("Unable to create the appropriate directory structure,"
        + "your progress will not be saved");
      model.progress = new PlayerProgress();
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
      return;
    }

    try {
      model.progress = new PlayerProgress(new DataInputStream(
        new FileInputStream(progressfile)));
    } catch (IOException err) {
      // TODO: dialog?
      System.err.println("Unable to load progress, your progress will not be" +
        "saved");
      model.progress = new PlayerProgress();
      return;
    }
  }
}
