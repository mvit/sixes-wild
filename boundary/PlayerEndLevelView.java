package boundary;

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

/**
 * PlayerEndLevelView comes up when a level is completed.
 * @author Bailey Sheridan
 *
 */
public class PlayerEndLevelView extends JDialog{

	PlayerApplication app;
	PlayerModel model;
	
	public PlayerEndLevelView(PlayerApplication app, PlayerModel model) {
		this.app = app;
		this.model = model;
	}
	
    public void openDialog(String endMsg)
    {
    	JFrame f = new JFrame();
		
        final JDialog dialog = new JDialog(app, "Level Finished", true);
        final JButton backBut = new JButton("Level Select");
        
        // Ugly, I know. Can be extracted out later if someone really wants to.
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
        panel.add(new JLabel("Score: " + model.score));
        panel.add(backBut);
        JButton[] buttons = { backBut };
        JOptionPane optionPane = new JOptionPane(panel,
                                                 JOptionPane.YES_NO_OPTION,
                                                 JOptionPane.PLAIN_MESSAGE,
                                                 null, buttons, backBut);
        dialog.getContentPane().add(optionPane);
        dialog.setSize(300,200);
        dialog.setLocationRelativeTo(f);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);
    }
}
