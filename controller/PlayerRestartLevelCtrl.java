package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.PlayerModel;

/**
 * @author Eli Skeggs, and Nick Chaput
 */
public class PlayerRestartLevelCtrl implements ActionListener{
  PlayerApplication app;
  PlayerModel model;

  public PlayerRestartLevelCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public void actionPerformed(ActionEvent event) {
    model.level.discardBoard();
    model.realizeLevel();
    model.reset();

    PlayerLevelView view = (PlayerLevelView) app.getView();
    view.update();
    view.boardView.repaint();
    view.revalidate();
  }
}
