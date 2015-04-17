package controller;

import boundary.PlayerApplication;
import model.PlayerModel;

public class PlayerExpandMoveCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerExpandMoveCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * TODO: are there anything in the boundary that needs to get updated?
   */
  public void expandMove(Point point) {
    model.move.expand(model.level.currentBoard, point);
  }
}
