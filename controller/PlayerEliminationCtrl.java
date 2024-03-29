package controller;

import java.awt.EventQueue;

import boundary.PlayerApplication;
import model.CellType;
import model.PlayerModel;
import model.Point;

/**
 * Controller for the Elimination variation.
 *
 * @author Bailey Sheridan, Nick Chaput
 */
public class PlayerEliminationCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;
  boolean fullyMarked;

  /**
   * Creates a new PlayerEliminationCtrl.
   * @param app
   * @param model
   */
  public PlayerEliminationCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
    fullyMarked = false;
  }

  public boolean finishMove() {
    if (model.counter <= 0) {
      return false;
    }

    model.counter--;

    if (model.counter == 0) {
      EventQueue.invokeLater(new PlayerEndLevelCtrl(app, model, isFullyMarked()));
    }

    if (model.move.isValid()) {
      // set the cells to marked
      for (Point point : model.move.points) {
        model.level.currentBoard.grid[point.x][point.y].marked = true;
      }
      if(isFullyMarked()) {
    	  EventQueue.invokeLater(new PlayerEndLevelCtrl(app, model, isFullyMarked()));
      }
      return true;
    	  
    }

    return false;
  }

  private boolean isFullyMarked() {
      for (int i = 0; i < 9; i++) {
          for (int j = 0; j < 9; j++) {
            if (!model.level.currentBoard.grid[i][j].marked && model.level.currentBoard.grid[i][j].type!=CellType.INERT) {
              return false;
            }
          }
        }
        fullyMarked = true;
        return true;
  }
}
