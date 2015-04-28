package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * A generic splash screen which displays the given image until the close method
 * is called.
 *
 * @author Eli Skeggs, and Maurizio Vitale
 */
public class SplashScreen extends JWindow {
  /**
   * Create and display a splash screen with the given image.
   *
   * @constructor
   * @param {String} file The image file to display.
   */
  public SplashScreen(String file) throws IOException {
    JPanel content = (JPanel) getContentPane();
    content.setBackground(Color.WHITE);

    BufferedImage splashImg = ImageIO.read(new File("resource/img/" + file));

    int width = splashImg.getWidth();
    int height = splashImg.getHeight();

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    int x = (screen.width - width) / 2;
    int y = (screen.height - height) / 2;
    setBounds(x, y, width, height);

    JLabel label = new JLabel(new ImageIcon(splashImg));
    content.add(label, BorderLayout.CENTER);
    setVisible(true);
  }

  /**
   * Close the splash screen, and dispose of associated resources.
   */
  public void close() {
    setVisible(false);
    dispose();
  }
}
