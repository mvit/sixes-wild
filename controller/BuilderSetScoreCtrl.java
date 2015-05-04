package controller;

import java.awt.Color;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.BuilderModel;

/**
 * @author Cem Unsal and Maurizio Vitale
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
	int num = 0;

    try {
    	num = Integer.parseInt(0 + entry, 10);
    }
    catch(NumberFormatException e) {
    	System.out.println("Not a number");
    	levelView.tfThreshold[tier].setBackground(Color.red);
    	levelView.repaint();
    	return;
    }
	levelView.tfThreshold[tier].setBackground(Color.white);
    model.level.rules.scoreThresholds[tier] = num;
	levelView.repaint();
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
