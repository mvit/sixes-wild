package controller;

import boundary.PlayerApplication;
import model.PlayerModel;

public class PlayerFinishMoveCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerFinishMoveCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * TODO: are there anything in the boundary that needs to get updated?
   */
  public void finishMove(Point point) {
    model.playerState = PlayerState.NONE;
    model.move.expand(model.level.currentBoard, point);
    if (model.variation.finishMove()) {
      // TODO: finish the move!
    }
  }
}
