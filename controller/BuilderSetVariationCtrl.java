package controller;

import boundary.BuilderApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.BuilderModel;
import model.Variation;

/**
 * @author Eli Skeggs
 */
public class BuilderSetVariationCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;
  Variation variation;

  /**
   * Create the controller for a given application and model.
   */
  public BuilderSetVariationCtrl(BuilderApplication app, BuilderModel model, Variation variation) {
    this.app = app;
    this.model = model;
    this.variation = variation;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    model.level.rules.variation = variation;
    model.takeSnapshot();
  }
}
