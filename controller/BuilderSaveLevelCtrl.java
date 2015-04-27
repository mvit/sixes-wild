package controller;

import boundary.BuilderApplication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.BuilderModel;

public class BuilderSaveLevelCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;

  /**
   * Create the controller with the context: the application and the model.
   */
  public BuilderSaveLevelCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }


  @Override
  public void actionPerformed(ActionEvent event) {
	  //Skeleton
	  
	  
  }
}
