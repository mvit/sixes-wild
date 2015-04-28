package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.BuilderModel;
import model.CellType;
import boundary.BuilderApplication;

/**
 * @author Eli Skeggs
 */
public class BuilderSetTileCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;
  int number, multiplier;

  public BuilderSetTileCtrl(BuilderApplication app, BuilderModel model, int number, int multiplier) {
    this.app = app;
    this.model = model;

    this.number = number;
    this.multiplier = multiplier;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    model.currentType = CellType.PLAYABLE;
    model.currentUseNumber = true;
    model.currentNumber = number;
    model.currentMultiplier = multiplier;
  }
}
