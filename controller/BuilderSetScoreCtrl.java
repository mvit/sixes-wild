package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.BuilderModel;

/**
 * @author Cem Unsal
 */
public class BuilderSetScoreCtrl implements DocumentListener {
  BuilderApplication app;
  BuilderModel model;
  int tier;
  BuilderLevelEditorView levelView;

  public BuilderSetScoreCtrl(BuilderApplication app, BuilderModel model,
      int tier, BuilderLevelEditorView levelView) {
    this.app = app;
    this.model = model;
    this.tier = tier;
    this.levelView = levelView;
  }

  protected void pushUpdate() {
    String entry = levelView.tfThreshold[tier].getText();
    int num = Integer.parseInt(0 + entry, 10);

    model.level.rules.scoreThresholds[tier] = num;
    model.takeSnapshot();
  }

  @Override
  public void changedUpdate(DocumentEvent event) {
    pushUpdate();
  }

  @Override
  public void insertUpdate(DocumentEvent event) {
    pushUpdate();
  }

  @Override
  public void removeUpdate(DocumentEvent event) {
    pushUpdate();
  }
}
