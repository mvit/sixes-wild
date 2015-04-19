package controller;

import boundary.PlayerApplication;
import model.PlayerModel;

public class PlayerEliminationCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerEliminationCtrl(PlayerApplication app, PlayerModel model) {
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
