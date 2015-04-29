package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelSelectView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PlayerModel;
/**
 * 
 * @author Nicholas Chaput and Maurizio Vitale
 *
 */
public class PlayerLevelSelectionCtrl implements ActionListener{
  PlayerApplication app;
  PlayerModel model;
  String filename;

  public PlayerLevelSelectionCtrl(PlayerApplication app, PlayerModel model, String filename) {
    this.app = app;
    this.model = model;
    this.filename = filename;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    app.setView(new PlayerLevelSelectView(app, model, filename));
    app.getView().revalidate();
    app.getView().repaint();
  }
}
