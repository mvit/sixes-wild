package boundary;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.PlayerLoadLevelSelectCtrl;
import model.PlayerModel;

public class PlayerEndLevelView extends JDialog{

	PlayerApplication app;
	PlayerModel model;
	
	public PlayerEndLevelView(PlayerApplication app, PlayerModel model) {
		super(new JFrame(), "Level Over", true);
		JButton selBut = new JButton();
		selBut.addActionListener(new PlayerLoadLevelSelectCtrl(app, model));
		JLabel explain = new JLabel("Level complete! You didn't win maybe!");
		add(explain);
		add(selBut);
	}
}
