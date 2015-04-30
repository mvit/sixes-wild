package boundary;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.PlayerLoadLevelSelectCtrl;
import model.PlayerModel;

public class PlayerEndLevelView extends JDialog{

	PlayerApplication app;
	PlayerModel model;
	
	public PlayerEndLevelView(PlayerApplication app, PlayerModel model) {
		this.app = app;
		this.model = model;
        final JFrame f = new JFrame();
        openDialog(f);
	}
	
    private void openDialog(Frame f)
    {
		String endMsg = "You won! Good job.";
		if(model.score < model.level.rules.scoreThresholds[0])
			endMsg = "You didn't pass.";
		else if(model.score < model.level.rules.scoreThresholds[1]) {
			endMsg += " 1 Star";
			model.progress.setAchievedScore(1, model.score);
		}
		else if(model.score < model.level.rules.scoreThresholds[2]) {
			endMsg += " 2 Star!";
			model.progress.setAchievedScore(1, model.score);
		}
		else {
			endMsg += " 3 Star!!";
			model.progress.setAchievedScore(1, model.score);
		}
		
        final JDialog dialog = new JDialog(app, "Level Finished", true);
        final JButton backBut = new JButton("Level Select");
        backBut.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerLoadLevelSelectCtrl pllsc = new PlayerLoadLevelSelectCtrl(app, model);
				pllsc.actionPerformed(e);
				dialog.dispose();
			}
        	
        });
        JPanel panel = new JPanel();
        panel.add(new JLabel(endMsg));
        panel.add(backBut);
        JButton[] buttons = { backBut };
        JOptionPane optionPane = new JOptionPane(panel,
                                                 JOptionPane.YES_NO_OPTION,
                                                 JOptionPane.PLAIN_MESSAGE,
                                                 null, buttons, backBut);
        dialog.getContentPane().add(optionPane);
        dialog.setSize(300,200);
        dialog.setLocationRelativeTo(f);
        dialog.setVisible(true);
    }
}
