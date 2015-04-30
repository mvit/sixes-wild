package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelView;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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

  @Override
  public void mouseClicked(MouseEvent event) {
    Point point;
    if (event.getButton() != MouseEvent.BUTTON1 ||
        (point = identifyPoint(event)) == null) {
      return;
    }

    switch (model.playerState) {
    case REMOVE:
      removeCtrl.mouseClicked(point);
      break;
    case SWAP:
      swapCtrl.mouseClicked(point);
      break;
    default:
      // do nothing!
    }
  }

  @Override
  public void mouseEntered(MouseEvent event) {}


  @Override
  public void mouseExited(MouseEvent event) {
    if (model.playerState == PlayerState.SELECT) {
      model.playerState = PlayerState.NONE;
      model.move = new Move();
      if(model.counter>0)
    	  model.counter--;
      // ends the level from here
      if(model.counter==0) {
      	EventQueue.invokeLater(new Runnable() {
  			@Override
  			public void run() {
  	            PlayerEndLevelCtrl end = new PlayerEndLevelCtrl(app, model);
  				end.endLevel();
  			}
      	});
      }
      ((PlayerLevelView)app.getView()).update();
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
    ((PlayerLevelView)app.getView()).repaint();
  }

  @Override
  public void mouseReleased(MouseEvent event) {
    Point point;
    if (event.getButton() == MouseEvent.BUTTON1 &&
        model.playerState == PlayerState.SELECT &&
        (point = identifyPoint(event)) != null) {
      finishMoveCtrl.finishMove(point);
    }
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    Point point;
    point = identifyPoint(event);
    if (model.playerState == PlayerState.SELECT) {
      if (point != null) {
    	expandMoveCtrl.expandMove(point);
      }
      else {
          model.playerState = PlayerState.NONE;
          model.move = new Move();
          if(model.counter>0)
        	  model.counter--;
          // ends the level from here
          if(model.counter==0) {
          	EventQueue.invokeLater(new Runnable() {
      			@Override
      			public void run() {
      	            PlayerEndLevelCtrl end = new PlayerEndLevelCtrl(app, model);
      				end.endLevel();
      			}
          	});
          }
          ((PlayerLevelView)app.getView()).update();
      }
    }
  }

  @Override
  public void mouseMoved(MouseEvent event) {}
}
