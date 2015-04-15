package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/*import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio;
import javax.swing.*;*/

/**
 * Splash Screen for use in SixesWild by Maurizio Vitale
 */
public class SplashScreen extends JWindow {
  public SplashScreen(String file) {
    JPanel content = (JPanel) getContentPane();
    content.setBackground(Color.white);

    BufferedImage splashImg = ImageIO.read(new File("/img/" + file));
    int width = splashImg.getWidth();
    int height = splashImg.getHeight();

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    int x = (screen.width - width)/2;
    int y = (screen.height - height)/2;
    setBounds(x,y,width,height);

    JLabel label = new JLabel(new ImageIcon("../img/" + file));
    content.add(label, BorderLayout.CENTER);
    setVisible(true);
  }

  public void close() {
    setVisible(false);
    dispose();
  }
}
