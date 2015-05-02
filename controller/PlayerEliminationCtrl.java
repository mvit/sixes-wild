package controller;

import java.awt.EventQueue;

import boundary.PlayerApplication;
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

  /**
   * Creates a new PlayerEliminationCtrl.
   * @param app
   * @param model
   */
  public PlayerEliminationCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public boolean specialMove() {
    model.counter--;
    return false;
  }

  public boolean finishMove() {
    if (model.counter <= 0) {
      return false;
    }

    model.counter--;

    if (model.counter == 0) {
      EventQueue.invokeLater(new PlayerEndLevelCtrl(app, model));
    }

    if (model.move.isValid()) {
      // set the cells to marked
      for (Point point : model.move.points) {
        model.level.currentBoard.grid[point.x][point.y].marked = true;
      }

      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          if (!model.level.currentBoard.grid[i][j].marked) {
            return true;
          }
        }
      }
      EventQueue.invokeLater(new PlayerEndLevelCtrl(app, model));
      return true;
    }

    return false;
  }

  @Override
  public boolean remove() {
    // TODO: implement
    specialMove();
    return false;
  }

  @Override
  public boolean scramble() {
    // TODO: implement
    specialMove();
    return false;
  }

  @Override
  public boolean swap() {
    // TODO: implement
    specialMove();
    return false;
  }
}
