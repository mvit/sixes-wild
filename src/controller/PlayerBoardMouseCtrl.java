package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import model.PlayerModel;
import model.PlayerState;
import model.Point;

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
    if (event.getButton() != MouseEvent.BUTTON1) {
      return;
    }

    Point point = identifyPoint(event);
    switch (model.playerState) {
    case REMOVE:
      removeCtrl.mouseClicked(point);
      break;
    case SWAP:
      swapCtrl.mouseClicked(point);
      break;
    }
  }

  @Override
  public void mouseEntered(MouseEvent event) {}

  @Override
  public void mouseExited(MouseEvent event) {}

  @Override
  public void mousePressed(MouseEvent event) {
    if (event.getButton() != MouseEvent.BUTTON1) {
      return;
    }

    if (model.playerState == PlayerState.NONE) {
      startMoveCtrl.startMove(identifyPoint(event));
    }
  }

  @Override
  public void mouseReleased(MouseEvent event) {
    if (event.getButton() != MouseEvent.BUTTON1) {
      return;
    }

    if (model.playerState == PlayerState.SELECT) {
      finishMoveCtrl.finishMove(identifyPoint(event));
    }
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    if (event.getButton() != MouseEvent.BUTTON1) {
      return;
    }

    // we might be expanding the move!
    if (model.playerState == PlayerState.SELECT) {
      expandMoveCtrl.expandMove(identifyPoint(event));
    }
  }

  @Override
  public void mouseMoved(MouseEvent event) {}
}
