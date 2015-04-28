package boundary;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.PlayerModel;

public class PlayerApplication extends JFrame {
  public static final String title = "Sixes Wild";

  protected PlayerModel model;
  protected JPanel view = null;

  ResourceLoader loader;

  public PlayerApplication(PlayerModel model, ResourceLoader loader) {
    super(title);

    this.model = model;
    this.loader = loader;
  }

  public void addQuitListener(QuitListener onQuit) {
    if (onQuit == null) {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } else {
      addWindowListener(onQuit);
    }
  }

  public void setView(JPanel panel) {
    boolean wasHidden = view == null;
    view = panel;
    setContentPane(panel);
    pack();
    if (wasHidden) {
      setVisible(true);
    }
  }

  public JPanel getView() {
    return view;
  }

  /**
   * Creates the splash screen, loads resources, then initializes the main
   * window (PlayerApplication).
   */
  public static void main(String[] args) {
    ResourceLoader loader = new ResourceLoader();

    for (int i = 1; i <= 6; i++) {
      loader.addResource(i + ".png");
    }

    // start splash screen
    SplashScreen splash;
    try {
      splash = new SplashScreen("splash1.png");
    } catch (IOException err) {
      System.err.println(err.getMessage());
      err.printStackTrace();
      return;
    }

    // load resources
    long loadStart = System.currentTimeMillis();
    PlayerModel model = new PlayerModel();
    try {
      loader.loadResources();
    } catch (IOException err) {
      System.err.println(err.getMessage());
      err.printStackTrace();
      return;
    }

    // initialize main app
    PlayerApplication app = new PlayerApplication(model, loader);
    JPanel initialView = new PlayerMainMenuView(app, model);

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
}
