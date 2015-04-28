package boundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * A quit listener. All subclasses must implement the onQuit method, which
 * handles a quit event.
 *
 * @author Eli Skeggs
 */
abstract public class QuitListener implements ActionListener, WindowListener {
  /**
   * Handle a quit event.
   */
  abstract public void onQuit();

  @Override
  public void actionPerformed(ActionEvent event) {
    onQuit();
  }

  @Override
  public void windowClosing(WindowEvent event) {
    onQuit();
  }

  @Override
  public void windowActivated(WindowEvent event) {}

  @Override
  public void windowClosed(WindowEvent event) {}

  @Override
  public void windowDeactivated(WindowEvent event) {}

  @Override
  public void windowDeiconified(WindowEvent event) {}

  @Override
  public void windowIconified(WindowEvent event) {}

  @Override
  public void windowOpened(WindowEvent event) {}
}
