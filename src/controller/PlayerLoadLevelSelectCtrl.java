package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelSelectView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.PlayerModel;

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
