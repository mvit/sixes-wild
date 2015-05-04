package controller;

import java.awt.EventQueue;

import boundary.PlayerApplication;
import model.PlayerModel;

/**
 * @author Eli Skeggs, Nick Chaput, Bailey Sheridan
 */
public class PlayerPuzzleCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerPuzzleCtrl(PlayerApplication app, PlayerModel model) {
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

    if (model.counter == 0) {
      EventQueue.invokeLater(new PlayerEndLevelCtrl(app, model, true));
    }

    return model.move.isValid();
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
