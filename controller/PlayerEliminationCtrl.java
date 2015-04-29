package controller;

import boundary.PlayerApplication;
import model.PlayerModel;
import model.Point;

/**
 * Controller for the Elimination variation.
 *
 * @author Bailey Sheridan
 */
public class PlayerEliminationCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerEliminationCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public boolean specialMove() {
    return false;
  }

  public boolean finishMove() {
    if (model.counter <= 0) {
      return false;
    }

    model.counter--;

    // set the cells to marked
    for (Point point : model.move.points) {
      model.level.currentBoard.grid[point.x][point.y].marked = true;
    }

    // TODO: if all required cells are marked, endLevel()
    for(int i = 0; i<9; i++) {
    	for(int j = 0; j<9; j++) {
    		if(!model.level.currentBoard.grid[i][j].marked)
    			return true;
    	}
    }
    PlayerEndLevelCtrl end = new PlayerEndLevelCtrl(app, model);
    end.endLevel();
    return true;
  }
}
