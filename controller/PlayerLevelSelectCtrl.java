package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelSelectView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Level;
import model.PlayerModel;
import utils.StreamFileUtils;

/**
 *
 * @author Nicholas Chaput, and Maurizio Vitale
 */
public class PlayerLevelSelectCtrl implements ActionListener {
  PlayerApplication app;
  PlayerModel model;
  String filename;

  public PlayerLevelSelectCtrl(PlayerApplication app, PlayerModel model,
      String filename) {
    this.app = app;
    this.model = model;
    this.filename = filename;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    String path = "resource/levels/" + filename;
    Object result = StreamFileUtils.readStream(path, Level.getReadable());

    if (result != null) {
      model.level = (Level) result;
      model.levelnum = Integer.parseInt(filename, 10) - 1;

      System.out.println("Level: " + model.level + " Level Num: " + model.levelnum);
      ((PlayerLevelSelectView) app.getView()).switchActive();
    }
  }
}
