package controller;

import boundary.PlayerApplication;
import boundary.PlayerMainMenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.PlayerModel;

public class PlayerMainMenuCtrl implements ActionListener {
  PlayerApplication app;
  PlayerModel model;

  /**
   * Create the controller with the context: the application and the model.
   */
  public PlayerMainMenuCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * Switch to the main menu.
   */
  public void loadMainMenu() {
    // discard the board if we're leaving the playerboardview
    if (model.level != null) {
      model.level.discardBoard();
    }
    app.setView(new PlayerMainMenuView(app, model));
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    loadMainMenu();
  }
}