package boundary;

import controller.PlayerRemoveCtrl;
import controller.PlayerRestartLevelCtrl;
import controller.PlayerScrambleCtrl;
import controller.PlayerSwapCtrl;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PlayerModel;

/**
 * The view when a player loads up a level.
 *
 * @author Bailey Sheridan, Eli Skeggs, and Maurizio Vitale
 */
public class PlayerLevelView extends JPanel {
  PlayerApplication app;
  PlayerModel model;
  public PlayerBoardView boardView;

  JLabel lblScoreNum;
  JLabel lblCounterNum;
  JLabel lblStar[] = new JLabel[3];

  public JButton btnRemove;
  public JButton btnSwap;
  public JButton btnScramble;

  public JButton btnRestart;
  public JButton btnMainMenu;


  /**
   * Create a level view for the player application.
   *
   * The quit listener is invoked when the quit button is clicked, indicating
   * either that the player is done playing the level, or the builder is done
   * previewing the level.
   *
   * The PlayerApplication and PlayerModel are used to create the constituent
   * views and controllers.
   *
   * @param onQuit
   * @param app
   * @param model
   */
  public PlayerLevelView(ActionListener onQuit, PlayerApplication app,
      PlayerModel model) {
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

    btnSwap = new JButton(new ImageIcon(app.loader.getResource("swap.png").getScaledInstance(64, 64, 0)));
    btnSwap.addActionListener(new PlayerSwapCtrl(app, model));
    panelControls.add(btnSwap);

    btnRemove = new JButton(new ImageIcon(app.loader.getResource("remove.png").getScaledInstance(64, 64, 0)));
    btnRemove.addActionListener(new PlayerRemoveCtrl(app, model));
    panelControls.add(btnRemove);

    btnScramble = new JButton(new ImageIcon(app.loader.getResource("scramble.png").getScaledInstance(64, 64, 0)));
    btnScramble.addActionListener(new PlayerScrambleCtrl(app, model));
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

    btnRestart = new JButton("Restart");
    btnRestart.addActionListener(new PlayerRestartLevelCtrl(app, model));
    panelNavigation.add(btnRestart);

    btnMainMenu = new JButton("Quit");
    btnMainMenu.addActionListener(onQuit);
    panelNavigation.add(btnMainMenu);

    //Panel Info

    JPanel panelInfo = new JPanel();
    panelInfo.setAlignmentX(0);

    panelRight.add(panelInfo);
    panelInfo.setLayout(new BoxLayout(panelInfo,BoxLayout.Y_AXIS));

    JLabel lblScore = new JLabel("Score");
    panelInfo.add(lblScore);

    lblScoreNum = new JLabel("12345");
    panelInfo.add(lblScoreNum);

    JLabel lblCounter = new JLabel("Moves Left");
    panelInfo.add(lblCounter);

    lblCounterNum = new JLabel("20");
    panelInfo.add(lblCounterNum);

    JPanel panelStars = new JPanel();

    panelStars.setLayout(new GridLayout(0,3,0,0));

    for (int i = 0; i < 3; i++) {
  	  lblStar[i] = new JLabel(new ImageIcon(app.loader.getResource("stargameempty.png")));
  	  panelStars.add(lblStar[i]);
    }

    panelStars.setMaximumSize(new Dimension(100,32));
    panelStars.setAlignmentX(0);
    panelInfo.add(panelStars);

    update();
    repaint();
  }

  public void update() {
    lblScoreNum.setText(Integer.toString(model.score));
    lblCounterNum.setText(Integer.toString(model.counter));
    for (int i = 0; i < 3; i++) {
    	if (model.score >= model.level.rules.scoreThresholds[i]) {
    	  lblStar[i].setIcon(new ImageIcon(app.loader.getResource("stargame.png")));;
    	}
      }
    this.validate();
    this.repaint();
  }
}
