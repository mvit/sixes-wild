package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Level;
import model.PlayerModel;

public class PlayerLoadLevelCtrl implements ActionListener {
  PlayerApplication app;
  PlayerModel model;

  /**
   * Create the controller with the context: the application and the model.
   */
  public PlayerLoadLevelCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * Load level from specified file into the model.
   *
   * @param {String} filename
   */
  public void loadLevel(String filename) {
    // model.level = new Level(new DataInputStream(new FileInputStream(filename)));
    model.level = new Level();
    model.level.realizeBoard();
    app.setView(new PlayerLevelView(app, model));
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    // TODO: actually though
    loadLevel("nope");
  }
}
