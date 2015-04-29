package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelView;
import model.Move;
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
    // TODO: implement variation controllers
    if (model.move.isValid()) {
      if (pVar.finishMove()) {
        updateScore(model.move);
        for (Point p : model.move.points) {
          model.level.currentBoard.grid[p.x][p.y].tile = null;
        }
        model.level.currentBoard.processBoard();
      }
    } else {
      //I don't know what happens here
    }
    model.move = new Move();
    // TODO: implement everything else that happens after a move finishes (update score, counter, etc)

    app.getView().repaint();
  }

  public int updateScore(Move move) {
    int newScore = 10;
    newScore = newScore * move.points.size();
    for (Point p : move.points) {
      newScore = newScore * model.level.currentBoard.grid[p.x][p.y].tile.multiplier;
    }

    model.score += newScore;
    ((PlayerLevelView) app.getView()).update();

    return newScore;
  }
}
