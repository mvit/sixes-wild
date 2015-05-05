package controller;

import boundary.PlayerApplication;
import java.io.File;
import model.PlayerModel;
import model.PlayerProgress;
import utils.ReadStream;
import utils.StreamFileUtils;

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
  public static final File progressfile = new File("resource/progress/progress");

  PlayerApplication app;
  PlayerModel model;

  /**
   * Create a PlayerLoadProgressCtrl.
   *
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
   * TODO: should we have alert dialogs?
   */
  public void loadProgress() {
    if (StreamFileUtils.ensureParent(progressfile)) {
      if (progressfile.isFile()) {
        ReadStream readStream = PlayerProgress.getReadable();
        Object result = StreamFileUtils.readStream(progressfile, readStream);

        if (result == null) {
          model.progress = new PlayerProgress();

          File backup = new File("resource/progress/progress-" +
            System.currentTimeMillis() + ".bak");
          if (StreamFileUtils.backup(progressfile, backup)) {
            StreamFileUtils.writeStream(progressfile, model.progress);
            System.err.println("Unable to load progress, your existing progress"
              + " file has been backed up and overwritten");
          } else {
            System.err.println("Unable to load progress, your progress will " +
              "not be saved");
            model.disableProgress = true;
          }
        } else {
          model.progress = (PlayerProgress) result;
        }
      } else {
        model.progress = new PlayerProgress();
        StreamFileUtils.writeStream(progressfile, model.progress);
      }
    } else {
      System.err.println("Unable to create the appropriate directory structure,"
        + " your progress will not be saved");
      model.progress = new PlayerProgress();
    }
  }
}
