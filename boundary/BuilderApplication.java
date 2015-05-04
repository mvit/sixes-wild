package boundary;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.BuilderModel;

/**
 * The builder application boundary class.
 *
 * @author Cem Unsal, Eli Skeggs, and Maurizio Vitale
 */
public class BuilderApplication extends JFrame {
  public static final String title = "Level Builder";

  protected BuilderModel model;
  protected JPanel view = null;

  public ResourceLoader loader;

  /**
   * Create a builder application from the given model and resource loader.
   *
   * @param model
   * @param loader
   */
  public BuilderApplication(BuilderModel model, ResourceLoader loader) {
    this.model = model;
    this.loader = loader;

    // always exit the JVM when the user closes the window
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Set the currently displayed JPanel. Makes the application visible if it's
   * not already.
   *
   * @param panel
   */
  public void setView(JPanel panel) {
    boolean wasHidden = view == null;
    view = panel;
    setContentPane(panel);
    pack();
    if (wasHidden) {
      setVisible(true);
    }
  }

  /**
   * Get the currently displayed JPanel.
   *
   * @return The currently visible JPanel.
   */
  public JPanel getView() {
    return view;
  }

  /**
   * Creates the splash screen, loads resources, then initializes the main
   * window (BuilderApplication).
   *
   * @param args The command-line parameters for the application.
   */
  public static void main(String[] args) {
    // start splash screen
    SplashScreen splash;
    try {
      splash = new SplashScreen("splash1levelbuilder.png");
    } catch (IOException err) {
      System.err.println(err.getMessage());
      err.printStackTrace();
      return;
    }

    long loadStart = System.currentTimeMillis();
    BuilderModel model = new BuilderModel();

    ResourceLoader loader = load();

    if (loader == null) {
      return;
    }

    // initialize main app
    BuilderApplication app = new BuilderApplication(model, loader);
    JPanel initialView = new BuilderMainMenuView(app, model);

    // extend time if necessary
    long loadElapsed = System.currentTimeMillis() - loadStart;
    if (loadElapsed < 2000) {
      try {
        Thread.sleep(2000 - loadElapsed);
      } catch (InterruptedException err) {
        // if we've been interrupted, just close the splash screen
      } finally {
        // close splash screen
        splash.close();
      }
    }

    // start main app
    app.setView(initialView);
  }

  public static ResourceLoader load() {
    ResourceLoader loader = new ResourceLoader();

    for (int i = 1; i <= 6; i++) {
      loader.addResource(i + ".png");
    }
    for (int i = 2; i <= 3; i++) {
      loader.addResource("x" + i + ".png");
    }

    loader.addResource("playable.png");
    loader.addResource("selected.png");
    loader.addResource("marked.png");

    // load resources
    try {
      loader.loadResources();
    } catch (IOException err) {
      System.err.println(err.getMessage());
      err.printStackTrace();
      return null;
    }

    return loader;
  }
}
