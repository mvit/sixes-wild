package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.BuilderModel;

public class BuilderUndoCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;

  public BuilderUndoCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (model.redoIndex > 0) {
      model.redoIndex--;
      model.level = model.history.get(model.redoIndex).snapshot;
      
      ((BuilderLevelEditorView) app.getView()).updateView();
    }
  }
}
