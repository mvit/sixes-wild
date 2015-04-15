import boundary.BuilderApplication;
import boundary.BuilderEditorView;
import model.Level;
import model.BuilderModel;
import model.Rules;

public class BuilderLoadEditorCtrl {
  BuilderApplication app;
  BuilderModel model;

  /**
   * Create the controller with the context: the application and the model.
   */
  public BuilderLoadEditor(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }
  
  /**
   * Load new level into the model.
   *
   * @param
   */
  public void loadEditor(String filename) {
	// model.level = new Level(new DataInputStream(new FileInputStream(filename)));
	model.level = new Level();
	app.setView(new BuilderEditorView(model));
  }

  /**
   * Load level from specified file into the model.
   *
   * @param {String} filename
   */
  public void loadEditor(String filename) {
    model.level = new Level(new DataInputStream(new FileInputStream(filename)));
    //model.level = new Level();
    app.setView(new BuilderEditorView(model));
  }
}
