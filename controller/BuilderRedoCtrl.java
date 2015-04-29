package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.BuilderModel;

/**
 * @author Cem Unsal
 */
public class BuilderRedoCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;

  public BuilderRedoCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (model.redoIndex < model.history.size() - 1) {
      model.level = model.history.get(++model.redoIndex).snapshot;
      model.clearTempState();

      BuilderLevelEditorView view = (BuilderLevelEditorView) app.getView();
      view.updateView();
      view.boardView.repaint();
      view.revalidate();
    }
  }
}
