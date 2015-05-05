package boundary;

import controller.PlayerRemoveCtrl;
import controller.PlayerRestartLevelCtrl;
import controller.PlayerScrambleCtrl;
import controller.PlayerSwapCtrl;

import java.awt.Dimension;
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

    JButton btnSwap = new JButton(new ImageIcon(app.loader.getResource("swap.png").getScaledInstance(64, 64, 0)));
    btnSwap.addActionListener(new PlayerSwapCtrl(app, model));
    panelControls.add(btnSwap);

    JButton btnRemove = new JButton(new ImageIcon(app.loader.getResource("remove.png").getScaledInstance(64, 64, 0)));
    btnRemove.addActionListener(new PlayerRemoveCtrl(app, model));
    panelControls.add(btnRemove);

    JButton btnScramble = new JButton(new ImageIcon(app.loader.getResource("scramble.png").getScaledInstance(64, 64, 0)));
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

    JButton btnRestart = new JButton("Restart");
    btnRestart.addActionListener(new PlayerRestartLevelCtrl(app, model));
    panelNavigation.add(btnRestart);

    JButton btnMainMenu = new JButton("Quit");
    btnMainMenu.addActionListener(onQuit);
    panelNavigation.add(btnMainMenu);

    //Panel Info

    JPanel panelInfo = new JPanel();
    panelInfo.setAlignmentX(0);

    panelRight.add(panelInfo);
    panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

    JLabel lblScore = new JLabel("Score");
    panelInfo.add(lblScore);

    lblScoreNum = new JLabel("12345");
    panelInfo.add(lblScoreNum);

    JLabel lblCounter = new JLabel("Moves Left");
    panelInfo.add(lblCounter);

    lblCounterNum = new JLabel("20");
    panelInfo.add(lblCounterNum);

    update();
    repaint();
  }

  /*public void drawMove(Point curr, Point prev) {
    int cellSize;

    if (this.boardView.getHeight() > this.boardView.getWidth()) cellSize = this.boardView.getHeight()/9;
    else cellSize = this.boardView.getWidth()/9;

    int x1 = curr.x * cellSize;
    int y1 = curr.y * cellSize;
    //int x2 = prev.x * cellSize;
    //int y2 = prev.y * cellSize;
    //CORNERS
    BufferedImage blob = app.loader.getResource("selected.png");
    BufferedImage blob_tlc = blob.getSubimage(0, 0, blob.getWidth()/3, blob.getHeight()/3);
    BufferedImage blob_trc = blob.getSubimage(blob.getWidth() * (2/3), 0, blob.getWidth()/3, blob.getHeight()/3);
    BufferedImage blob_blc = blob.getSubimage(0,blob.getHeight() * (2/3),blob.getWidth()/3, blob.getHeight()/3);
    BufferedImage blob_brc = blob.getSubimage(blob.getWidth()* (2/3), blob.getHeight() * (3*2), blob.getWidth()/3, blob.getHeight()/3);
    //SIDES
    BufferedImage blob_top = blob.getSubimage(blob.getWidth() /3, 0, blob.getWidth() /3, blob.getHeight()/3);
    BufferedImage blob_bot = blob.getSubimage(blob.getWidth() /3, blob.getHeight()* (2/3), blob.getWidth()/3, blob.getHeight()/3);
    BufferedImage blob_left = blob.getSubimage(0, blob.getHeight() /3, blob.getWidth()/3, blob.getHeight()/3);
    BufferedImage blob_right = blob.getSubimage(blob.getWidth() * (2/3), blob.getHeight() / 3, blob.getWidth()/3, blob.getHeight()/3);
    //FILL
    BufferedImage blob_center = blob.getSubimage(blob.getWidth()/3, blob.getWidth()/3, blob.getWidth()/3, blob.getWidth()/3);
  }*/

  public void update() {
    lblScoreNum.setText(Integer.toString(model.score));
    lblCounterNum.setText(Integer.toString(model.counter));
    this.validate();
    this.repaint();
  }
}
