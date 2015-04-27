package controller;

import boundary.PlayerApplication;
import model.Move;
import model.PlayerModel;
import model.Point;
import model.Variation;

public class PlayerRemoveCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerRemoveCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public void mouseClicked(Point point) {
	    //TODO: generic implementation
	  PlayerVariationCtrl pVar = model.variation.createCtrl(app, model);
	  if (model.variation != Variation.RELEASE && model.level.currentBoard.grid[point.x][point.y].tile.number != 6)
	  {
		  //GO INTO PLAYERMODEL AND REPLACE PLAYERVARIATIONCTRL WITH VARIATION
		  //TODO: This is suboptimal, decide whether to change PlayerVariationCtrl.specialMove() to individual methods for each special move
		  //TODO: resolve the move
		  Move reMove = new Move();
		  reMove.expand(model.level.currentBoard, point);
	  }
	  pVar.specialMove();
  }
}
