package boundary;

import controller.ExitCtrl;
import controller.PlayerLoadLevelSelectCtrl;
import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
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

    JLabel lblSixesWild = new JLabel("Sixes Wild");
    lblSixesWild.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 26));
    lblSixesWild.setHorizontalAlignment(SwingConstants.CENTER);

    JButton btnLevelSelect = new JButton("Level Select");
    btnLevelSelect.addActionListener(new PlayerLoadLevelSelectCtrl(app, model));
    btnLevelSelect.setFont(new Font("Lucida Bright", Font.PLAIN, 14));

    JButton btnNewButton = new JButton("Achievements");
    btnNewButton.setFont(new Font("Lucida Bright", Font.PLAIN, 14));

    JButton btnExit = new JButton("Exit");
    btnExit.setFont(new Font("Lucida Bright", Font.PLAIN, 14));
    btnExit.addActionListener(new ExitCtrl());

    ///////////////////
    //**DON'T TOUCH**//
    ///////////////////
    GroupLayout groupLayout = new GroupLayout(this);
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.TRAILING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(150)
          .addComponent(btnLevelSelect, GroupLayout.PREFERRED_SIZE, 208, Short.MAX_VALUE)
          .addGap(150))
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(175)
          .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 158, Short.MAX_VALUE)
          .addGap(175))
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(215)
          .addComponent(btnExit, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
          .addGap(215))
        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
          .addGap(180)
          .addComponent(lblSixesWild, GroupLayout.PREFERRED_SIZE, 136, Short.MAX_VALUE)
          .addGap(180))
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(20)
          .addComponent(lblSixesWild, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
          .addGap(50)
          .addComponent(btnLevelSelect, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
          .addGap(18)
          .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
          .addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
          .addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
          .addContainerGap())
    );
    setLayout(groupLayout);
  }
}
