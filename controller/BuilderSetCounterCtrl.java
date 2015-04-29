package controller;

import boundary.BuilderApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import model.BuilderModel;

/**
 * @author Cem Unsal
 */
public class BuilderSetCounterCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;

  public BuilderSetCounterCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
	  String entry = ((JTextField) e.getSource()).getText();
	  int num = Integer.parseInt(entry);
	  
	  model.level.rules.initialCounter=num;
	  model.takeSnapshot();
	  
  }
}
