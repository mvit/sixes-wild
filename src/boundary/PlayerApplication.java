import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayerApplication {
  public static final String title = "Sixes Wild";

  protected JFrame frame;
  protected JPanel view;

  public PlayerApplication() {
    frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    /*frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });*/

    frame.pack();
    frame.setVisible(true);
  }

  public void setView(JPanel panel)
  {
    view = panel;
    frame.setContentPane(panel);
    frame.pack();
  }

  public JPanel getView()
  {
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
  }
}
