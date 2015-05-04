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
public class BuilderSetCounterCtrl implements DocumentListener {
  BuilderApplication app;
  BuilderModel model;
  BuilderLevelEditorView levelView;

  public BuilderSetCounterCtrl(BuilderApplication app, BuilderModel model,
      BuilderLevelEditorView levelView) {
    this.app = app;
    this.model = model;
    this.levelView = levelView;
  }

  protected void pushUpdate() {
	int num = 0;
    String entry = levelView.tfCounter.getText();
    try {
    	num = Integer.parseInt(0 + entry, 10);
    }
    catch(NumberFormatException e) {
    	System.out.println("Not a number");
    	levelView.tfCounter.setBackground(Color.red);
    	levelView.repaint();
    	return;
    }
	levelView.tfCounter.setBackground(Color.white);
    model.level.rules.initialCounter = num;
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
