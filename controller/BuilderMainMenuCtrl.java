package controller;

import boundary.BuilderApplication;
import boundary.BuilderMainMenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.BuilderModel;

/**
 * Switch builder views to the main menu.
 *
 * @author Eli Skeggs
 */
public class BuilderMainMenuCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;

  /**
   * Create the controller with the context: the application and the model.
   */
  public BuilderMainMenuCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * Switch to the main menu.
   */
  public void loadMainScreen() {
    model.clearHistory();
    app.setView(new BuilderMainMenuView(app, model));
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    loadMainScreen();
  }
}
