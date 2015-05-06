package controller;

import boundary.PlayerApplication;
import model.PlayerModel;

/**
 * Controller for the Lightning variation.
 *
 * @author Bailey Sheridan, Nick Chaput
 */
public class PlayerLightningCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerLightningCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public boolean finishMove() {
    return model.counter > 0 && model.move.isValid();
  }
}
