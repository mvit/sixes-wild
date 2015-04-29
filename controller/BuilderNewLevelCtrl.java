package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.BuilderModel;
import model.Level;

/**
 * Create a new level.
 *
 * @author Eli Skeggs
 */
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
   * Load the level select view.
   */
  public void loadEditorView() {
    model.level = new Level();
    model.clearHistory();
    model.takeSnapshot();
    app.setView(new BuilderLevelEditorView(app, model));
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    loadEditorView();
  }
}
