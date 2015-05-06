package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
  public BuilderSetVariationCtrl(BuilderApplication app, BuilderModel model,
      Variation variation) {
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
        model.level.rules.numberWeights[Rules.maxNumber - 1] = 0;
      }

      model.level.rules.variation = variation;
      model.takeSnapshot();

      if (model.level.currentBoard != null) {
        model.discardLevel();
        model.realizeLevel();
      }

      view.updateView();
    }
  }
}
