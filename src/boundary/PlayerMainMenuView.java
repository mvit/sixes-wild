package boundary;

import controller.ExitCtrl;
import controller.PlayerLoadLevelSelectCtrl;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.PlayerModel;

/**
 * PlayerMainMenuView for the player application
 *
 * @author Bailey Sheridan
 */
public class PlayerMainMenuView extends JPanel {
  PlayerModel model;
  PlayerApplication app;

  public PlayerMainMenuView(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;

    setMinimumSize(new Dimension(600,480));
    setPreferredSize(new Dimension(600, 480));

    GridBagLayout gridBagLayout = new GridBagLayout();

    setLayout(gridBagLayout);

    JPanel panelContent = new JPanel();
    panelContent.setBorder(null);
    panelContent.setSize(300,500);

    add(panelContent);
    panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS));

    JLabel lblSixesWild = new JLabel("\u262D");
    panelContent.add(lblSixesWild);
    lblSixesWild.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 26));
    lblSixesWild.setAlignmentX(Component.CENTER_ALIGNMENT);
    lblSixesWild.setAlignmentY(Component.CENTER_ALIGNMENT);

    JButton btnLevelSelect = new JButton("Level Select");
    panelContent.add(btnLevelSelect);
    btnLevelSelect.addActionListener(new PlayerLoadLevelSelectCtrl(app, model));
    btnLevelSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnLevelSelect.setAlignmentY(Component.CENTER_ALIGNMENT);

    JButton btnNewButton = new JButton("Instructions");
    panelContent.add(btnNewButton);
    btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnNewButton.setAlignmentY(Component.CENTER_ALIGNMENT);

    JButton btnExit = new JButton("Exit");
    panelContent.add(btnExit);
    btnExit.addActionListener(new ExitCtrl());
    btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnExit.setAlignmentY(Component.CENTER_ALIGNMENT);
  }
}
