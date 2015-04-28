package controller;

import boundary.PlayerApplication;
import model.PlayerModel;
import model.Point;

/**
 * @author Eli Skeggs
 */
public class PlayerSwapCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerSwapCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public void mouseClicked(Point point) {

  }
}
