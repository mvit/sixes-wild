package model;

import java.util.Timer;

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

  public LightningTimer(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
    task = new LightningTimerTask(app, model);
    schedule(task, 1000); // counts down every second
  }
}