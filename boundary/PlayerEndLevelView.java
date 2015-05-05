package boundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.LightningTimer;
import controller.PlayerLoadLevelSelectCtrl;
import controller.PlayerRestartLevelCtrl;
import model.PlayerModel;
import model.Variation;

/**
 * PlayerEndLevelView comes up when a level is completed.
 *
 * @author Bailey Sheridan
 */
public class PlayerEndLevelView extends JDialog{
  PlayerApplication app;
  PlayerModel model;
  LightningTimer timer;

  public PlayerEndLevelView(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public void openDialog(String endMsg) {
    JFrame f = new JFrame();

    final JDialog dialog = new JDialog(app, "Level Finished", true);
    
    JButton backBut = new JButton("Level Select");
    JButton retryBut = new JButton("Replay?");

    // Ugly, I know. Can be extracted out later if someone really wants to.
    backBut.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        PlayerLoadLevelSelectCtrl load = new PlayerLoadLevelSelectCtrl(app, model);
        load.actionPerformed(e);
        dialog.dispose();
      }
    });

    retryBut.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        PlayerRestartLevelCtrl restart = new PlayerRestartLevelCtrl(app, model);
        restart.actionPerformed(e);
        if (model.level.rules.variation == Variation.LIGHTNING) {
          timer = new LightningTimer(app, model);
        }
        dialog.dispose();
      }
    });

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(new JLabel(endMsg));
    panel.add(new JLabel("Score: " + model.score));
    backBut.setAlignmentX(CENTER_ALIGNMENT);
    retryBut.setAlignmentX(CENTER_ALIGNMENT);
    panel.add(backBut);
    panel.add(retryBut);
    dialog.setContentPane(panel);
    dialog.setSize(300,200);
    panel.setSize(300, 200);
    dialog.setLocationRelativeTo(f);
    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    dialog.setVisible(true);
  }
}
