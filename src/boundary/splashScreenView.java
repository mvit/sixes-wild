import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio;
import javax.swing.*;


/*
 * Splash Screen for use in SixesWild by Maurizio Vitale
 */

public class SplashScreenView extends JWindow {
	public SplashScreenView(String file) {
		JPanel content = getContentPane();
		content.setBackground(Color.white);

        BufferedImage splashImg = ImageIO.read(new File("/img/" + file));
        int width = splashImg.getWidth();
        int height = splashImg.getHeight();
        
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (screen.width - width)/2;
		int y = (screen.height - height)/2;
		setBounds(x,y,width,height);

		JLabel label = new JLabel(new ImageIcon("../img/" + filename));
		content.add(label, BorderLayout.CENTER);
		setVisible(true);
	}

    public void close() {
        	setVisible(false);
            dispose();
    }
}
