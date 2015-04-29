package boundary;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PlayerEndLevelView extends JDialog{

	public PlayerEndLevelView() {
		super(new JFrame(), "Level Over", true);
		JButton selBut = new JButton();
		JLabel explain = new JLabel("Level complete! You didn't win maybe!");
		add(explain);
		add(selBut);
	}
}
