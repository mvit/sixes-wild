import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
    SplashScreen splash = new SplashScreen();

    // load resources

    // close splash screen

    // start main app
    PlayerApplication app = new PlayerApplication();
    app.setView(new MainMenuView(app, model));
  }
}
