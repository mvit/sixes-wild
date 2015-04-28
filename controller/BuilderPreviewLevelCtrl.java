package controller;

import boundary.BuilderApplication;
import boundary.PlayerApplication;
import boundary.PlayerLevelView;
import boundary.QuitListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import model.BuilderModel;
import model.PlayerModel;

/**
 * @author Eli Skeggs
 */
public class BuilderPreviewLevelCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;

  public BuilderPreviewLevelCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }

  public void actionPerformed(ActionEvent event) {
    PlayerModel playerModel = new PlayerModel(model);
    playerModel.realizeLevel();
    app.setVisible(false);
    PlayerApplication playerApp = new PlayerApplication(playerModel,
      app.loader);
    QuitListener onQuit = new BuilderQuitPreviewCtrl(app, playerApp,
      playerModel);
    JPanel initialView = new PlayerLevelView(onQuit, playerApp, playerModel);
    playerApp.addQuitListener(onQuit);
    playerApp.setView(initialView);
  }
}
