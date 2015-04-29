package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import model.BuilderModel;

/**
 * @author Cem Unsal
 */
public class BuilderSetTimerCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;

  public BuilderSetTimerCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
	  
	  //Skeleton
  }
}
