package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.PlayerApplication;
import boundary.PlayerLevelSelectView;
import model.Level;
import model.PlayerModel;
import model.Rules;

public class PlayerLoadLevelSelectCtrl implements ActionListener {
  PlayerApplication app;
  PlayerModel model;

  /**
   * Create the controller with the context: the application and the model.
   */
  public PlayerLoadLevelSelectCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * Load levelselect
   *
   * @param
   */
  public void loadLevelSelect() {
    app.setView(new PlayerLevelSelectView(app, model));
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    loadLevelSelect();
  }
}
