package controller;

import boundary.PlayerApplication;
import model.PlayerModel;

public class PlayerLightningCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerLightningCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public boolean specialMove() {
    return false;
  }

  public boolean finishMove() {
    return false;
  }
}
