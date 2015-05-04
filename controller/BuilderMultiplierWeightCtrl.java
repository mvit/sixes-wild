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
    double val = (double) source.getValue() / source.getMaximum();

    Rules rules = model.level.rules;
    double weightDiff = val - rules.getMultiplierWeight(multiplier);
    double ratio = 1.0d / (1.0d + weightDiff);
    if (multiplier < rules.multiplierWeights.length) {
      rules.multiplierWeights[multiplier] = val;
    }
    for (int i = 0; i < rules.multiplierWeights.length; i++) {
      rules.multiplierWeights[i] *= ratio;
    }
    model.takeSnapshot();
  }
}
