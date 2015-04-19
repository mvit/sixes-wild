package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.BuilderModel;
import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;

public class BuilderRedoCtrl implements ActionListener{

	  BuilderApplication app;
	  BuilderModel model;

	  public BuilderRedoCtrl(BuilderApplication app, BuilderModel model) {
	    this.app = app;
	    this.model = model;
	  }

	  @Override
	  public void actionPerformed(ActionEvent e) {
	    if (model.redoIndex < (model.history.size()-1)) {
	      model.redoIndex++;
	      model.level = model.history.get(model.redoIndex).snapshot;
	      
	      ((BuilderLevelEditorView) app.getView()).updateView();
	    }
	  }
}
