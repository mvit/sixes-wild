package controller;

import model.PlayerModel;
import boundary.BuilderApplication;
import boundary.PlayerApplication;
import boundary.QuitListener;

/**
 * @author Eli Skeggs
 */
public class BuilderQuitPreviewCtrl extends QuitListener {
  BuilderApplication app;
  PlayerApplication playerApp;
  PlayerModel playerModel;

  public BuilderQuitPreviewCtrl(BuilderApplication app,
      PlayerApplication playerApp, PlayerModel playerModel) {
    this.app = app;
    this.playerApp = playerApp;
    this.playerModel = playerModel;
  }

  public void onQuit() {
    playerModel.discardLevel();
    app.setVisible(true);
    playerApp.dispose();
  }
}
