package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.BuilderModel;
import model.CellType;
import boundary.BuilderApplication;

/**
 * @author Eli Skeggs
 */
public class BuilderSetCellTypeCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;
  CellType type;

  public BuilderSetCellTypeCtrl(BuilderApplication app, BuilderModel model, CellType type) {
    this.app = app;
    this.model = model;
    this.type = type;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    model.currentType = type;
    model.currentUseNumber = false;
  }
}
