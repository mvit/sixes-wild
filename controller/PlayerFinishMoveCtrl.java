package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelView;
import model.Move;
import model.PlayerModel;
import model.PlayerState;
import model.Point;
import model.Variation;

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
    if (pVar.finishMove()) {
      updateScore(model.move);
      for (Point p : model.move.points) {
        model.level.currentBoard.grid[p.x][p.y].tile = null;
      }
      model.move = new Move();
      app.getView().paintImmediately(0, 0, 1500, 1500);
      PlayerUpdateBoardCtrl updateBoardCtrl = new PlayerUpdateBoardCtrl(app, model);
      updateBoardCtrl.processBoardSmooth();
      if (model.level.rules.variation == Variation.RELEASE) {
        	((PlayerReleaseCtrl)pVar).redundantFinish();
        }
    }
    // TODO: implement everything else that happens after a move finishes (update score, counter, etc)
    model.move = new Move();
    app.getView().repaint();
    ((PlayerLevelView) app.getView()).update();
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
