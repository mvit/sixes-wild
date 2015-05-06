package boundary;

import controller.ExitCtrl;
import controller.PlayerInstructionsCtrl;
import controller.PlayerLoadLevelSelectCtrl;
import controller.ResetProgressCtrl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PlayerModel;

/**
 * PlayerMainMenuView which displays the main menu for the player application.
 *
 * @author Bailey Sheridan, Eli Skeggs, and Maurizio Vitale
 */
public class PlayerMainMenuView extends JPanel {
  PlayerModel model;
  PlayerApplication app;

  public JButton btnLevelSelect;
  public JButton btnInstructions;
  public JButton btnResetProgress;
  public JButton btnExit;

  /**
   * Create a new main menu view.
   *
   * PlayerApplication and PlayerModel are used to create the controllers.
   *
   * @param app
   * @param model
   */
  public PlayerMainMenuView(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;

    //set Sizes
    setMinimumSize(new Dimension(600,480));
    setPreferredSize(new Dimension(600, 480));

    //set Layout to gridBagLayout
    GridBagLayout gridBagLayout = new GridBagLayout();
    setLayout(gridBagLayout);

    //make a panel for menu content

    JPanel panelContent = new JPanel();
    panelContent.setBorder(null);
    panelContent.setSize(300,500);
    panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS));
    add(panelContent);

    //add labels and buttons to menu content

    JLabel lblSixesWild = new JLabel(new ImageIcon(app.loader.getResource("logo.png").getScaledInstance(184, 120, 0)));
    panelContent.add(lblSixesWild);
    lblSixesWild.setAlignmentX(Component.CENTER_ALIGNMENT);
    lblSixesWild.setAlignmentY(Component.CENTER_ALIGNMENT);

    btnLevelSelect = new JButton("Level Select");
    panelContent.add(btnLevelSelect);
    btnLevelSelect.addActionListener(new PlayerLoadLevelSelectCtrl(app, model));
    btnLevelSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnLevelSelect.setAlignmentY(Component.CENTER_ALIGNMENT);

    btnInstructions = new JButton("Instructions");
    btnInstructions.addActionListener(new PlayerInstructionsCtrl(app, model));
    panelContent.add(btnInstructions);
    btnInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnInstructions.setAlignmentY(Component.CENTER_ALIGNMENT);

    btnResetProgress = new JButton("Reset Progress");
    btnResetProgress.addActionListener(new ResetProgressCtrl(app, model));
    btnResetProgress.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnResetProgress.setAlignmentY(Component.CENTER_ALIGNMENT);
    panelContent.add(btnResetProgress);
    
    btnExit = new JButton("Exit");
    panelContent.add(btnExit);
    btnExit.addActionListener(new ExitCtrl());
    btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnExit.setAlignmentY(Component.CENTER_ALIGNMENT);
  }
}
