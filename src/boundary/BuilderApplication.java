import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BuilderApplication {
  public static final String title = "LeveL Builder";

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
    SplashScreen splash = new SplashScreen();

    // load resources

    // close splash screen

    // start main app
    BuilderApplication app = new BuilderApplication();
    app.setView(new MainMenuView(app, model));
  }
}
