import boundary.BuilderApplication;
import boundary.BuilderMenuView;
import model.Level;
import model.BuilderModel;
import model.Rules;

public class BuilderLoadMenuCtrl {
  BuilderApplication app;
  BuilderModel model;

  /**
   * Create the controller with the context: the application and the model.
   */
  public BuilderLoadMenuCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * Load builder menu
   *
   * @param
   */
  public void loadMenu() {
    app.setView(new BuilderMenuView(model));
  }
}
