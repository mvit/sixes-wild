package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PlayerModel;
import boundary.PlayerApplication;
import boundary.PlayerInstructionsView;

/**
 * Display the instructions view.
 *
 * @author Bailey Sheridan
 */
public class PlayerInstructionsCtrl implements ActionListener {
  PlayerApplication app;
  PlayerModel model;

  /**
   * Create the instructions view controller.
   *
   * @param app
   * @param model
   */
  public PlayerInstructionsCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * Handle the instructions button being pressed.
   *
   * @param event Details about the action event.
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    app.setView(new PlayerInstructionsView(app, model));
  }
}
