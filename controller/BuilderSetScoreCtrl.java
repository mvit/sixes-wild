package controller;

import boundary.BuilderApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import model.BuilderModel;

/**
 * @author Cem Unsal
 */
public class BuilderSetScoreCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;
  int tier;

  public BuilderSetScoreCtrl(BuilderApplication app, BuilderModel model, int tier) {
    this.app = app;
    this.model = model;
    this.tier = tier;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
	  String entry = ((JTextField) e.getSource()).getText();
	  int num = Integer.parseInt(entry);
	  
	  model.level.rules.scoreThresholds[tier]=num;
	  model.takeSnapshot();
	  
  }
}
