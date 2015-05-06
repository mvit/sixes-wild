package controller;

import java.awt.EventQueue;

import boundary.PlayerApplication;
import model.Board;
import model.CellType;
import model.PlayerModel;

/**
 * @author Eli Skeggs, Nick Chaput and Bailey Sheridan
 */
public class PlayerReleaseCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerReleaseCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public boolean finishMove() {
    if (model.counter <= 0) {
      return false;
    }

    model.counter--;

    return model.move.isValid();
  }

  private boolean allBucketsFull() {
    for (int x = 0; x < Board.width; x++) {
      for (int y = 0; y < Board.height; y++) {
          if (model.level.currentBoard.grid[x][y].type == CellType.BUCKET) {
            if (model.level.currentBoard.grid[x][y].tile == null) {
              return false;
            }
          }
      }
    }
    return true;
  }

  public void redundantFinish() {
    if (model.counter == 0) {
      EventQueue.invokeLater(new PlayerEndLevelCtrl(app, model, allBucketsFull()));
    }
    if (allBucketsFull()) {
       EventQueue.invokeLater(new PlayerEndLevelCtrl(app, model, true));
    }
  }

}
