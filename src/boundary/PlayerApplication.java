package boundary;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.PlayerModel;

public class PlayerApplication {
  public static final String title = "Sixes Wild";

  protected PlayerModel model;
  protected JFrame frame;
  protected JPanel view = null;

  public PlayerApplication(PlayerModel model) {
    this.model = model;

    frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // use instead of setDefaultCloseOeration for advanced window close handling
    /*frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });*/
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

  /**
   * Creates the splash screen, loads resources, then initializes the main
   * window (PlayerApplication).
   */
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
    PlayerModel model = new PlayerModel();
    try {
      Thread.sleep(4000);
    } catch (InterruptedException err) {
      return;
    } finally {
      // close splash screen
      splash.close();
    }

    // start main app
    PlayerApplication app = new PlayerApplication(model);
    app.setView(new MainMenuView(app, model));
  }
}
