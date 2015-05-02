package controller;

import boundary.PlayerApplication;
import boundary.PlayerLevelSelectView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JPanel;
import model.Level;
import model.PlayerModel;

/**
 *
 * @author Nicholas Chaput, and Maurizio Vitale
 */
public class PlayerLevelSelectionCtrl implements ActionListener {
  PlayerApplication app;
  PlayerModel model;
  String filename;

  public PlayerLevelSelectionCtrl(PlayerApplication app, PlayerModel model,
      String filename) {
    this.app = app;
    this.model = model;
    this.filename = filename;
  }

  public void loadLevel(String filename) {
    try {
      model.level = new Level(new DataInputStream(new FileInputStream(
        "resource/levels/" + filename)));
    } catch (IOException e) {
      return;
    }
    model.realizeLevel();
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    loadLevel(filename);
    app.setView(new PlayerLevelSelectView(app, model, filename));
    JPanel view = app.getView();
    view.revalidate();
    view.repaint();
  }
}
