import java.awt.*;
import javax.swing.*;

/*
 * Splash Screen for use in SixesWild by Maurizio Vitale
 */

public class splashScreen extends JWindow {
	
	private int duration;
	
	public splashScreen(int d) {
		duration = d;
	}
	
	public void showSplash() {

		JPanel content = (JPanel) getContentPane();
		content.setBackground(Color.white);
		
		int width = 1138;
		int height = 640;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (screen.width - width)/2;
		int y = (screen.height - height)/2;
		setBounds(x,y,width,height);
		
		JLabel label = new JLabel(new ImageIcon("../img/splash1.png"));
		content.add(label, BorderLayout.CENTER);
		setVisible(true);
		
		try{Thread.sleep(duration);} catch (Exception e) {}
		setVisible(false);
	}
}
