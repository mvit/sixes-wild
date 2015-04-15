package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.PlayerApplication;
import boundary.MainMenuView;
import model.Level;
import model.PlayerModel;
import model.Rules;

public class MainMenuCtrl implements ActionListener {
  PlayerApplication app;
  PlayerModel model;

  /**
   * Create the controller with the context: the application and the model.
   */
  public MainMenuCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * Switch to the main menu.
   */
  public void loadMainMenu() {
    model.level.discardBoard();
    app.setView(new MainMenuView(app, model));
  }

  public void actionPerformed(ActionEvent event) {
    loadMainMenu();
  }
}
