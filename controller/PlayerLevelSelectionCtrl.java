package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelSelectView;
import boundary.PlayerLevelView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import model.Level;
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

  public void loadLevel(String fname) {
	  model.level = new Level();
	  try {
    	model.level = new Level(new DataInputStream(new FileInputStream("resource/levels/" + fname)));
	  }
	  catch (IOException e) {
		  return;
	  }
    model.realizeLevel();
    app.setView(new PlayerLevelView(new PlayerMainMenuCtrl(app, model), app, model));
  }
  
  @Override
  public void actionPerformed(ActionEvent event) {
	loadLevel(filename);
    app.setView(new PlayerLevelSelectView(app, model, filename));
    app.getView().revalidate();
    app.getView().repaint();
  }
}
