package controller;

import boundary.PlayerApplication;
import model.PlayerModel;

/**
 * @author Eli Skeggs, and Nick Chaput
 */
public class PlayerReleaseCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerReleaseCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public boolean specialMove() {
    model.counter--;
    return false;
  }

  public boolean finishMove() {
    if (model.counter <= 0) {
      return false;
    }

    model.counter--;

    if (model.move.isValid()) {
      // TODO: bucket checking
      return true;
    }

    return false;
  }

  @Override
  public boolean remove() {
    // TODO: implement
    specialMove();
    return false;
  }

  @Override
  public boolean scramble() {
    // TODO: implement
    specialMove();
    return false;
  }

  @Override
  public boolean swap() {
    // TODO: implement
    specialMove();
    return false;
  }
}
