package controller;

import boundary.BuilderApplication;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.BuilderModel;

/**
 * The controller to handle altered number weights.
 *
 * @author Cem Unsal
 */
public class BuilderNumberWeightCtrl implements ChangeListener {
  BuilderApplication app;
  BuilderModel model;
  int number;

  /**
   * Create a BuilderMultiplierWeightCtrl, given a BuilderApplication and a
   * BuilderModel for context.
   *
   * @param app
   * @param model
   * @param multiplier The multiplier to which this controller applies.
   */
  public BuilderNumberWeightCtrl(BuilderApplication app, BuilderModel model,
      int number) {
    this.app = app;
    this.model = model;
    this.number = number;
  }

  /**
   * Handle the slider's value changing.
   *
   * @param event Details about the ChangeEvent, including the relevant JSlider.
   */
  @Override
  public void stateChanged(ChangeEvent event) {
    JSlider source = (JSlider) event.getSource();
    int val = source.getValue();
    model.level.rules.numberWeights[number] = val;
    model.takeSnapshot();
  }
}