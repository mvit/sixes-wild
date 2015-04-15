package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.BuilderApplication;
import boundary.BuilderMainScreen;
import model.BuilderModel;

public class BuilderMainScreenCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;

  /**
   * Create the controller with the context: the application and the model.
   */
  public BuilderMainScreenCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * Switch to the main menu.
   */
  public void loadMainScreen() {
    app.setView(new BuilderMainScreen(app, model));
  }

  public void actionPerformed(ActionEvent event) {
    loadMainScreen();
  }
}
