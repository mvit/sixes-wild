import boundary.PlayerApplication;
import boundary.PlayerLevelView;
import model.Level;
import model.PlayerModel;
import model.Rules;

public class PlayerLoadLevelCtrl {
  PlayerApplication app;
  PlayerModel model;

  /**
   * Create the controller with the context: the application and the model.
   */
  public PlayerLoadLevelCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * Load level from specified file into the model.
   *
   * @param {String} filename
   */
  public void loadLevel(String filename) {
    // model.level = new Level(new DataInputStream(new FileInputStream(filename)));
    model.level = new Level();
    app.setView(new PlayerLevelView(model));
  }
}
