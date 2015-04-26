package controller;

import boundary.PlayerApplication;
import model.PlayerModel;
import model.Point;

public class PlayerRemoveCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerRemoveCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public void mouseClicked(Point point) {
	    //TODO: generic implementation
	  model.variation.specialMove();
  }
}
