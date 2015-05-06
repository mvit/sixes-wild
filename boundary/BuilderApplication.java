package boundary;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.BuilderModel;
import model.PlayerModel;

/**
 * The builder application boundary class.
 *
 * @author Cem Unsal, Eli Skeggs, and Maurizio Vitale
 */
public class BuilderApplication extends JFrame {
  public static final String title = "Level Builder";

  protected BuilderModel model;
  protected JPanel view = null;
  public JPanel initialView;

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
    SplashScreen splash = makeSplashScreen("splash2levelbuilder.png");
    this.initialize(splash);
  }

  private void initialize(SplashScreen splash) {
    initialView = new BuilderMainMenuView(this, model);
    long loadStart = System.currentTimeMillis();
    this.addQuitListener(null);
    this.initializeResources();
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
    this.setView(initialView);
  }

  private SplashScreen makeSplashScreen(String fname) {
    SplashScreen splash = null;
    try {
      splash = new SplashScreen(fname);
    } catch (IOException err) {
      System.err.println(err.getMessage());
      err.printStackTrace();
      return null;
    }
    return splash;
  }

  private void initializeResources() {
    for (int i = 1; i <= 6; i++) {
      loader.addResource(i + ".png");
    }
    for (int i = 2; i <= 3; i++) {
      loader.addResource("x" + i + ".png");
    }

    loader.addResource("playable.png");
    loader.addResource("bucket.png");
    loader.addResource("inert.png");

    loader.addResource("logo.png");

    try {
      loader.loadResources();
    } catch (IOException err) {
      System.err.println(err.getMessage());
      err.printStackTrace();
      return;
    }
  }

  /**
   * Add a quit listener for when the user clicks the close button on the
   * window.
   *
   * @param onQuit
   */
  public void addQuitListener(QuitListener onQuit) {
    if (onQuit == null) {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } else {
      addWindowListener(onQuit);
    }
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
    ResourceLoader loader = new ResourceLoader();
    BuilderModel model = new BuilderModel();
    // initialize main app
    BuilderApplication app = new BuilderApplication(model, loader);
  }
}
