package controller;

import boundary.BuilderApplication;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.BuilderModel;
import model.Rules;

/**
 * The controller to handle altered number weights.
 *
 * Example probability distributions:
 *
 * <pre>
 * 16.7%, 16.7%, 16.7%, 16.7%, 16.7%, 16.7%
 * 1,     1,     1,     1,     1,     1     => 6
 *
 * 10%,   18%,   18%,   18%,   18%,   18%
 * 5,     9,     9,     9,     9,     9     => 50
 *
 * 10%,   20%,   17.5%, 17.5%, 17.5%, 17.5%
 * 4,     8,     7,     7,     7,     7     => 40
 * </pre>
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
    double val = (double) source.getValue() / source.getMaximum();

    Rules rules = model.level.rules;
    double weightDiff = val - rules.getNumberWeight(number);
    double ratio = 1.0d / (1.0d + weightDiff);
    if (number < rules.numberWeights.length) {
      rules.numberWeights[number] = val;
    }
    for (int i = 0; i < rules.numberWeights.length; i++) {
      rules.numberWeights[i] *= ratio;
    }
    model.takeSnapshot();
  }
}
