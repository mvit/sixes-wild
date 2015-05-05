package controller;

import boundary.BuilderApplication;
import boundary.PlayerApplication;
import boundary.PlayerLevelView;
import boundary.QuitListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import model.BuilderModel;
import model.PlayerModel;

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
