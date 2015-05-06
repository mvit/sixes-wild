package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Level;
import model.PlayerModel;
import model.Variation;
import utils.StreamFileUtils;

/**
 * @author Eli Skeggs, Maurizio Vitale, and Bailey Sheridan
 */
public class PlayerLoadLevelCtrl implements ActionListener {
  PlayerApplication app;
  PlayerModel model;
  String filename;

  /**
   * Create the controller with the application, the model, and the file to read
   * from if invoked.
   */
  public PlayerLoadLevelCtrl(PlayerApplication app, PlayerModel model,
      String filename) {
    this.app = app;
    this.model = model;
    this.filename = filename;
  }

  /**
   * Load level from constructor-defined file into the model.
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    String path = "resource/levels/" + filename;
    Object result = StreamFileUtils.readStream(path, Level.getReadable());

    if (result != null) {
      model.level = (Level) result;
      model.levelnum = Integer.parseInt(filename, 10) - 1;
      model.realizeLevel();

      PlayerMainMenuCtrl exitCtrl = new PlayerMainMenuCtrl(app, model);
      app.setView(new PlayerLevelView(exitCtrl, app, model));

      // TODO: instantiate a new variation controller, and let it setup any
      // necessary long-term controllers, including lightning timer
      if (model.level.rules.variation == Variation.LIGHTNING) {
        model.timer = new LightningTimer(app, model);
      }
    }
  }
}
