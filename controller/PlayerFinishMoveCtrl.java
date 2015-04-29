package controller;

import boundary.PlayerApplication;
import model.PlayerModel;
import model.PlayerState;
import model.Point;

/**
 * @author Eli Skeggs, Nick Chaput
 */
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
    //TODO: implement variation controllers
    if (pVar.finishMove()) {
    	for (Point p : model.move.points) {
    	    model.level.currentBoard.grid[p.x][p.y].tile = null;
    	}
    	model.level.currentBoard.processBoard();
    	app.getView().repaint();
    }
    //TODO: implement everything else that happens after a move finishes (update score, counter, etc)
  }
  
}
