package controller;

import boundary.PlayerApplication;
import boundary.PlayerBoardView;
import boundary.PlayerLevelView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.PlayerModel;

public class PlayerRestartLevelCtrl implements ActionListener{
  PlayerApplication app;
  PlayerModel model;

  public PlayerRestartLevelCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public void actionPerformed(ActionEvent event) {
    model.level.discardBoard();
    model.level.realizeBoard();

    PlayerBoardView boardView = ((PlayerLevelView) app.getView()).boardView;
    boardView.repaint();
  }
}
