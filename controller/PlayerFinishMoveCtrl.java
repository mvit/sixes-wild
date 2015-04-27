package controller;

import boundary.PlayerApplication;
import model.PlayerModel;
import model.PlayerState;
import model.Point;

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
    PlayerVariationCtrl pVar = model.variation.createCtrl(app, model);
    if (pVar.finishMove()) {
      // TODO: finish the move!
    }
  }
}
