package controller;

import java.util.Timer;

import model.PlayerModel;
import boundary.PlayerApplication;

/**
 * Timer class for the Lightning variation's countdown.
 *
 * @author Bailey Sheridan
 */
public class LightningTimer extends Timer {
  PlayerApplication app;
  PlayerModel model;
  LightningTimerTask task;

  /**
   * Creates a new LightningTimer.
   * @param app
   * @param model
   */
  public LightningTimer(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
    task = new LightningTimerTask(app, model);
    schedule(task, 1000); // counts down every second
  }
}
