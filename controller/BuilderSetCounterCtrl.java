package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.BuilderModel;

/**
 * @author Cem Unsal
 */
public class BuilderSetCounterCtrl implements DocumentListener {
  BuilderApplication app;
  BuilderModel model;
  BuilderLevelEditorView levelView;

  public BuilderSetCounterCtrl(BuilderApplication app, BuilderModel model, BuilderLevelEditorView levelView) {
    this.app = app;
    this.model = model;
    this.levelView = levelView;
  }

@Override
public void changedUpdate(DocumentEvent arg0) {
	  String entry=levelView.tfCounter.getText();
	  int num = Integer.parseInt(0 +entry);
	  
	  model.level.rules.initialCounter=num;
	  model.takeSnapshot();
	  
}

@Override
public void insertUpdate(DocumentEvent arg0) {
	  String entry=levelView.tfCounter.getText();
	  int num = Integer.parseInt(0 +entry);
	  
	  model.level.rules.initialCounter=num;
	  model.takeSnapshot();
	  
}

@Override
public void removeUpdate(DocumentEvent arg0) {
	  String entry=levelView.tfCounter.getText();
	  int num = Integer.parseInt(0 +entry);
	  
	  model.level.rules.initialCounter=num;
	  model.takeSnapshot();
	  }
}
