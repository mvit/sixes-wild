package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.BuilderModel;

public class BuilderSetTypePuzzleCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;

  /**
   * Create the controller with the context: the application and the model.
   */
  public BuilderSetTypePuzzleCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }


  @Override
  public void actionPerformed(ActionEvent event) {
	  //Skeleton
	  
	  
  }
}