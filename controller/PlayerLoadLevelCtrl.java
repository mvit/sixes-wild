package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Level;
import model.PlayerModel;
import model.Variation;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Eli Skeggs, Maurizio Vitale and Bailey Sheridan
 */
public class PlayerLoadLevelCtrl implements ActionListener {
  PlayerApplication app;
  PlayerModel model;
  LightningTimer timer;
  String filename;

  /**
   * Create the controller with the context: the application and the model.
   */
  public PlayerLoadLevelCtrl(PlayerApplication app, PlayerModel model, String filename) {
    this.app = app;
    this.model = model;
    this.filename = filename;
  }

  /**
   * Load level from specified file into the model.
   *
   * @param filename
   */
  public void loadLevel(String filename) {
    try {
      model.level = new Level(new DataInputStream(new FileInputStream("resource/levels/" + filename)));
    } catch (IOException e) {
      return;
    }
    model.levelnum = Integer.parseInt(filename, 10);
    model.realizeLevel();
    app.setView(new PlayerLevelView(new PlayerMainMenuCtrl(app, model), app,
      model));
    // TODO: instantiate a new variation controller, and let it setup any
    // necessary long-term controllers, including lightning timer
    if (model.level.rules.variation == Variation.LIGHTNING) {
      timer = new LightningTimer(app, model);
    }
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    // TODO: actually though
    loadLevel(filename);
  }
}
