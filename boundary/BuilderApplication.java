package boundary;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.BuilderModel;

/**
 * @author Eli Skeggs, and Maurizio Vitale
 */
public class BuilderApplication extends JFrame {
  public static final String title = "Level Builder";

  protected BuilderModel model;
  protected JPanel view = null;

  public ResourceLoader loader;

  public BuilderApplication(BuilderModel model, ResourceLoader loader) {
    this.model = model;
    this.loader = loader;

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

  public static void main(String[] args) {
    ResourceLoader loader = new ResourceLoader();

    loader.addResource("questionmark.png");
    for (int i = 1; i <= 6; i++) {
      loader.addResource(i + ".png");
    }

    // start splash screen
    SplashScreen splash;
    try {
      splash = new SplashScreen("splash1levelbuilder.png");
    } catch (IOException err) {
      System.err.println(err.getMessage());
      err.printStackTrace();
      return;
    }

    // load resources
    long loadStart = System.currentTimeMillis();
    BuilderModel model = new BuilderModel();
    try {
      loader.loadResources();
    } catch (IOException err) {
      System.err.println(err.getMessage());
      err.printStackTrace();
      return;
    }

    // initialize main app
    BuilderApplication app = new BuilderApplication(model, loader);
    JPanel initialView = new BuilderMainMenuView(app, model);

    // extend time if necessary
    long loadElapsed = System.currentTimeMillis() - loadStart;
    if (loadElapsed < 2000) {
      try {
        Thread.sleep(4000);
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
