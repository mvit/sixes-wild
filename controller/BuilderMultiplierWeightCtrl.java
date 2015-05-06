package controller;

import boundary.BuilderApplication;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.BuilderModel;
import model.Rules;

/**
 * The controller to handle altered multiplier weights.
 *
 * @author Cem Unsal
 */
public class BuilderMultiplierWeightCtrl implements ChangeListener {
  BuilderApplication app;
  BuilderModel model;
  int multiplier;

  /**
   * Create a BuilderMultiplierWeightCtrl, given a BuilderApplication and a
   * BuilderModel for context.
   *
   * @param app
   * @param model
   * @param multiplier The multiplier to which this controller applies.
   */
  public BuilderMultiplierWeightCtrl(BuilderApplication app, BuilderModel model,
      int multiplier) {
    this.app = app;
    this.model = model;
    this.multiplier = multiplier;
  }

  /**
   * Handle the slider's value changing.
   *
   * @param event Details about the ChangeEvent, including the relevant JSlider.
   */
  @Override
  public void stateChanged(ChangeEvent event) {
    JSlider source = (JSlider) event.getSource();
    Rules rules = model.level.rules;
    rules.multiplierWeights[multiplier] = source.getValue();

    model.takeSnapshot();

    if (model.level.currentBoard != null) {
      model.discardLevel();
      model.realizeLevel();
    }
  }
}
