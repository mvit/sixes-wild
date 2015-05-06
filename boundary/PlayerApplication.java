package boundary;

import controller.PlayerLoadProgressCtrl;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.PlayerModel;

/**
 * The player application boundary class.
 *
 * @author Eli Skeggs and Maurizio Vitale
 */
public class PlayerApplication extends JFrame {
  public static final String title = "Sixes Wild";

  protected PlayerModel model;
  protected JPanel view = null;

  ResourceLoader loader;

  /**
   * Create a player application from the given model and resource loader.
   *
   * @param model
   * @param loader
   */
  public PlayerApplication(PlayerModel model, ResourceLoader loader) {
    this(model, loader, 2000);
  }

  /**
   * Create a player application from the given model, resource loader, and
   * minimum splash screen time.
   *
   * @param model
   * @param loader
   */
  public PlayerApplication(PlayerModel model, ResourceLoader loader,
      long duration) {
    super(title);

    this.model = model;
    this.loader = loader;
    SplashScreen splash = showSplash("splash2.png");
    initializeResources();
    initializeView(splash, duration);
  }

  private void initializeView(SplashScreen splash, long duration) {
    JPanel initialView = new PlayerMainMenuView(this, model);
    long loadStart = System.currentTimeMillis();
    this.addQuitListener(null);

    (new PlayerLoadProgressCtrl(this, model)).loadProgress();

    long loadElapsed = System.currentTimeMillis() - loadStart;
    if (loadElapsed < duration) {
      try {
        Thread.sleep(duration - loadElapsed);
      } catch (InterruptedException err) {
        // if we've been interrupted, just close the splash screen
      } finally {
        splash.close();
      }
    }

    // start main app
    this.setView(initialView);
  }

  private SplashScreen showSplash(String name) {
    SplashScreen splash = null;

    try {
      splash = new SplashScreen(name);
    } catch (IOException err) {
      System.err.println(err.getMessage());
      err.printStackTrace();
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
	      loader.addResource("selected.png");
	      loader.addResource("marked.png");
	      loader.addResource("bucket.png");
	      loader.addResource("inert.png");
	      loader.addResource("stargame.png");
	      loader.addResource("stargameempty.png");

	      loader.addResource("logo.png");
	      loader.addResource("star.png");
	      loader.addResource("starempty.png");

	      loader.addResource("swap.png");
	      loader.addResource("scramble.png");
	      loader.addResource("remove.png");


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
   * window (PlayerApplication).
   *
   * @param args The command-line parameters for the application.
   */
  public static void main(String[] args) {
    ResourceLoader loader = new ResourceLoader();
    PlayerModel model = new PlayerModel();
    // initialize main app
    new PlayerApplication(model, loader);
  }
}
