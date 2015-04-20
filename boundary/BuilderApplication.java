package boundary;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.BuilderModel;

public class BuilderApplication {
  public static final String title = "Level Builder";

  protected BuilderModel model;
  protected JFrame frame;
  protected JPanel view = null;

  public BuilderApplication(BuilderModel model) {
    this.model = model;

    frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void setView(JPanel panel) {
    boolean wasHidden = view == null;
    view = panel;
    frame.setContentPane(panel);
    frame.pack();
    if (wasHidden) {
      frame.setVisible(true);
    }
  }

  public JPanel getView() {
    return view;
  }

  public static void main(String[] args) {
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
    BuilderModel model = new BuilderModel();
    try {
      Thread.sleep(4000);
    } catch (InterruptedException err) {
      return;
    } finally {
      // close splash screen
      splash.close();
    }

    // start main app
    BuilderApplication app = new BuilderApplication(model);
    app.setView(new BuilderMainMenuView(app, model));
  }
}
