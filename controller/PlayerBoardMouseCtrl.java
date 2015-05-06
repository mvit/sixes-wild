package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelView;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import model.CellType;
import model.Move;
import model.PlayerModel;
import model.PlayerState;
import model.Point;

/**
 * @author Eli Skeggs, Nick Chaput
 *
 * TODO: implement "Swap" special move, add validity checks to basic moves
 */
public class PlayerBoardMouseCtrl implements MouseListener, MouseMotionListener
{
  PlayerApplication app;
  PlayerModel model;

  PlayerRemoveCtrl removeCtrl;
  PlayerSwapCtrl swapCtrl;

  PlayerExpandMoveCtrl expandMoveCtrl;
  PlayerFinishMoveCtrl finishMoveCtrl;
  PlayerStartMoveCtrl startMoveCtrl;

  public PlayerBoardMouseCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;

    removeCtrl = new PlayerRemoveCtrl(app, model);
    swapCtrl = new PlayerSwapCtrl(app, model);

    expandMoveCtrl = new PlayerExpandMoveCtrl(app, model);
    finishMoveCtrl = new PlayerFinishMoveCtrl(app, model);
    startMoveCtrl = new PlayerStartMoveCtrl(app, model);
  }

  Point identifyPoint(MouseEvent event) {
    PlayerLevelView view = (PlayerLevelView) app.getView();
    return view.boardView.identifyPoint(event.getX(), event.getY());
  }

  protected void fizzleMove() {
    // make the move fizzle
    model.playerState = PlayerState.NONE;
    model.move = new Move();
    if (model.counter > 0) {
      model.counter--;
    }

    // ends the level from here
    if (model.counter == 0) {
      EventQueue.invokeLater(new PlayerEndLevelCtrl(app, model, true));
    }
  }

  @Override
  public void mouseClicked(MouseEvent event) {}

  @Override
  public void mouseEntered(MouseEvent event) {}

  @Override
  public void mouseExited(MouseEvent event) {
    if (model.playerState == PlayerState.SELECT) {
      fizzleMove();
      ((PlayerLevelView) app.getView()).update();
    }
  }

  @Override
  public void mousePressed(MouseEvent event) {
    Point point;
    if (event.getButton() == MouseEvent.BUTTON1 &&
        model.playerState == PlayerState.NONE &&
        (point = identifyPoint(event)) != null) {
      startMoveCtrl.startMove(point);
    }
    ((PlayerLevelView) app.getView()).repaint();
  }

  @Override
  public void mouseReleased(MouseEvent event) {
    Point point;

    if (event.getButton() == MouseEvent.BUTTON1 &&
        (point = identifyPoint(event)) != null) {
    	switch (model.playerState) {
        case REMOVE:
          removeCtrl.startRemove(point);
          break;
        case SWAP:
          swapCtrl.startSwap(point);
          break;
        case SELECT:
            finishMoveCtrl.finishMove(point);
            break;
        default:
        	break;
        	}
    }

  }

  @Override
  public void mouseDragged(MouseEvent event) {
    if (model.playerState == PlayerState.SELECT) {
      Point point = identifyPoint(event);
      if (point != null
    		  && model.level.currentBoard.grid[point.x][point.y].type == CellType.PLAYABLE) {
        expandMoveCtrl.expandMove(point);
      } else {
        fizzleMove();
        ((PlayerLevelView)app.getView()).update();
      }
    }
  }

  @Override
  public void mouseMoved(MouseEvent event) {}


  public boolean isNullTile (Point point) {
	  return (model.level.currentBoard.grid[point.x][point.y].tile == null
			  	|| model.level.currentBoard.grid[point.x][point.y].type == CellType.BUCKET
			  	|| model.level.currentBoard.grid[point.x][point.y].type == CellType.INERT);
  }

}
