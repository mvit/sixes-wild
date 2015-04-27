package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.BuilderModel;
import model.Level;

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
    // model.level = new Level(new DataInputStream(new FileInputStream(filename)));
    model.level = new Level();
    app.setView(new BuilderLevelEditorView(app, model));
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    loadEditorView();
  }
}
