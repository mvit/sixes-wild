package controller;

import boundary.BuilderApplication;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.BuilderModel;

/**
 * Preview a work-in-progress level from the builder application.
 *
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
    if (model.level.currentBoard == null) {
      model.realizeLevel();
    } else {
      model.discardLevel();
    }
    app.getView().repaint();
  }
}
