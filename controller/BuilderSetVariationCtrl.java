package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSlider;
import model.BuilderModel;
import model.Rules;
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
    Rules rules = model.level.rules;
    Variation old = rules.variation;
    if (old != variation) {
      BuilderLevelEditorView view = (BuilderLevelEditorView) app.getView();

      if (variation == Variation.RELEASE) {
        int total = 0;
        for (int i = 0; i < Rules.maxNumber - 1; i++) {
          total += view.numberSliders[i].getValue();
        }

        double doubleTotal = (double) total;
        for (int i = 0; i < Rules.maxNumber - 1; i++) {
          JSlider slider = view.numberSliders[i];
          model.level.rules.numberWeights[i] = slider.getValue() / doubleTotal;
        }
      }

      model.level.rules.variation = variation;
      model.takeSnapshot();

      view.updateView();
    }
  }
}
