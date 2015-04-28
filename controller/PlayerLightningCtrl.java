package controller;

import boundary.PlayerApplication;
import model.LightningTimer;
import model.PlayerModel;

/**
 * Controller for the Lightning variation.
 * @author Bailey Sheridan
 *
 */
public class PlayerLightningCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;
  LightningTimer timer;

  public PlayerLightningCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
    timer = new LightningTimer(app, model);
  }

  public boolean specialMove() {
    return false;
  }

  public boolean finishMove() {
	// pretty sure nothing special has to go here, for now
    return true;
  }
}
