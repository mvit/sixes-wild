package boundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

abstract public class QuitListener implements ActionListener, WindowListener {
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
