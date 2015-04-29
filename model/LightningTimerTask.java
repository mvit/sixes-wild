package model;

import java.util.TimerTask;

import boundary.PlayerApplication;

/**
 * TimerTask for the LightningTimer for the Lightning variation.
 *
 * @author Bailey Sheridan
 */
public class LightningTimerTask extends TimerTask {
  PlayerApplication app;
  PlayerModel model;

  public LightningTimerTask(PlayerApplication app, PlayerModel model){
    this.model = model;
  }

  /**
   * Runs until the counter hits zero.
   */
  @Override
  public void run() {
    if (model.counter > 0) {
      model.counter--;
    }

    if (model.counter == 0) {
      // TODO: cancel()
      // TODO: endLevel()
    }
  }
}