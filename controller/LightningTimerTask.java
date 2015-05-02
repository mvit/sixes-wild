package controller;

import java.awt.EventQueue;
import java.util.TimerTask;

import model.PlayerModel;
import boundary.PlayerApplication;
import boundary.PlayerLevelView;

/**
 * TimerTask for the LightningTimer for the Lightning variation.
 *
 * @author Bailey Sheridan
 */
public class LightningTimerTask extends TimerTask {
  PlayerApplication app;
  PlayerModel model;

  public LightningTimerTask(PlayerApplication app, PlayerModel model){
    this.app = app;
    this.model = model;
  }

  /**
   * Runs until the counter hits zero.
   */
  @Override
  public void run() {
    if (model.counter > 0) {
      model.counter--;
      ((PlayerLevelView) app.getView()).update();
    }

    // TODO: ensure we don't queue multiple endLevel invocations
    if (model.counter == 0) {
      cancel();
      EventQueue.invokeLater(new PlayerEndLevelCtrl(app, model));
    }
  }
}
