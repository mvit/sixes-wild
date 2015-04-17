package controller;

import boundary.PlayerApplication;
import model.PlayerModel;
import model.PlayerState;

public class PlayerStartMoveCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerStartMoveCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * TODO: are there anything in the boundary that needs to get updated?
   */
  public void startMove(Point point) {
    model.playerState = PlayerState.SELECT;
    model.move = new Move();
    model.move.expand(model.level.currentBoard, point);
  }
}
