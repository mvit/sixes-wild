package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelView;
import model.PlayerModel;
import model.Point;

/**
 * @author Eli Skeggs, Nick Chaput and Maurizio Vitale
 */
public class PlayerExpandMoveCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerExpandMoveCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * TODO: are there anything in the boundary that needs to get updated?
   * Also we need sanity checks (tiles actualy adjacent to each other)
   */
  public void expandMove(Point point) {
    model.move.expand(model.level.currentBoard, point);
    ((PlayerLevelView) app.getView()).boardView.repaint();
  }
}
