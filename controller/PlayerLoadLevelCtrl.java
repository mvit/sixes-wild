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
  public void loadLevel(String fname) {
	  model.level = new Level();
	  try {
    	model.level = new Level(new DataInputStream(new FileInputStream("resource/levels/" + fname)));
	  }
	  catch (IOException e) {
		  return;
	  }
	model.levelnum = Integer.parseInt(fname, 10);
    model.realizeLevel();
    app.setView(new PlayerLevelView(new PlayerMainMenuCtrl(app, model), app, model));
    if(model.level.rules.variation == Variation.LIGHTNING) {
    	timer = new LightningTimer(app, model);
    }
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    // TODO: actually though
    loadLevel(filename);
  }
}
