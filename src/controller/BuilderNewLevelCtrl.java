package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;
import model.Level;
import model.BuilderModel;
import model.Rules;

public class BuilderNewLevelCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;

  /**
   * Create the controller with the context: the application and the model.
   */
  public BuilderNewLevelCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * Load levelselect
   */
  public void loadEditorView() {
    app.setView(new BuilderLevelEditorView(app, model));
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    loadEditorView();
  }
}
