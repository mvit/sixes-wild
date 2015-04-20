package boundary;

import controller.PlayerMainMenuCtrl;
import controller.PlayerRestartLevelCtrl;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.PlayerModel;

/**
 * The view when a player loads up a level.
 *
 * @author Bailey Sheridan, Maurizio Vitale, and Eli Skeggs
 */
public class PlayerLevelView extends JPanel {
  PlayerApplication app;
  PlayerModel model;
  public PlayerBoardView boardView;

  public PlayerLevelView(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;

    setMinimumSize(new Dimension(600, 480));
    setPreferredSize(new Dimension(600, 480));
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

    //Panel Left (Game Board)

    JPanel panelLeft = new JPanel();
    panelLeft.setAlignmentY(0);
    add(panelLeft);
    panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.X_AXIS));
    boardView = new PlayerBoardView(app, model);
    boardView.setAlignmentY(0);
    panelLeft.add(boardView);

    //Panel Controls

    JPanel panelControls = new JPanel();
    panelControls.setAlignmentY(0);
    panelLeft.add(panelControls);
    panelControls.setLayout(new BoxLayout(panelControls, BoxLayout.Y_AXIS));

    JButton btnSwap = new JButton("\u2194");
    panelControls.add(btnSwap);

    JButton btnRemove = new JButton("\u2718");
    panelControls.add(btnRemove);

    JButton btnScramble = new JButton("\u27F2");
    panelControls.add(btnScramble);

    //Panel Right

    JPanel panelRight = new JPanel();
    panelRight.setAlignmentY(0);
    add(panelRight);
    panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

    //Panel Navigation

    JPanel panelNavigation = new JPanel();
    panelNavigation.setAlignmentX(0);
    panelRight.add(panelNavigation);
    panelNavigation.setLayout(new BoxLayout(panelNavigation, BoxLayout.X_AXIS));

    JButton btnRestart = new JButton("Restart");
    btnRestart.addActionListener(new PlayerRestartLevelCtrl(app, model));
    panelNavigation.add(btnRestart);

    JButton btnMainMenu = new JButton("Main Menu");
    btnMainMenu.addActionListener(new PlayerMainMenuCtrl(app, model));
    panelNavigation.add(btnMainMenu);

    //Panel Info

    JPanel panelInfo = new JPanel();
    panelInfo.setAlignmentX(0);

    panelRight.add(panelInfo);
    panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

    JLabel lblScore = new JLabel("Score");
    panelInfo.add(lblScore);

    JLabel lblScoreNum = new JLabel("12345");
    panelInfo.add(lblScoreNum);

    JLabel lblCounter = new JLabel("Moves Left");
    panelInfo.add(lblCounter);

    JLabel lblCounterNum = new JLabel("20");
    panelInfo.add(lblCounterNum);
  }
}