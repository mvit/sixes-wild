package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.BuilderModel;

/**
 * @author Cem Unsal
 */
public class BuilderUndoCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;

  public BuilderUndoCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (model.undo()) {
      model.clearTempState();

      BuilderLevelEditorView view = (BuilderLevelEditorView) app.getView();
      view.updateView();
      view.boardView.repaint();
      view.revalidate();

      model.finishHistoryChange();
    }
  }
}
