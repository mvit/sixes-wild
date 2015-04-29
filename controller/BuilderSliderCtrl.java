package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.BuilderModel;

/**
 * @author Cem Unsal
 */
public class BuilderSliderCtrl implements ChangeListener {
  BuilderApplication app;
  BuilderModel model;

  public BuilderSliderCtrl(BuilderApplication app, BuilderModel model, int num) {
    this.app = app;
    this.model = model;
  }


@Override
public void stateChanged(ChangeEvent e) { 
	JSlider source = (JSlider)e.getSource();
	int val= source.getValue();
	
	
	
}
}
