package boundary;

import controller.PlayerMainMenuCtrl;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import model.PlayerModel;

public class PlayerLevelView extends JPanel {
  /**
   * The view when a player loads up a level.
   * @author Bailey Sheridan
   */
  PlayerApplication app;
  PlayerModel model;
  public PlayerBoardView boardView;

  public PlayerLevelView(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;

    //I don't know how to initialize the board. Haven't tested.
    boardView = new PlayerBoardView(app, model);

    JButton btnRestart = new JButton("Restart");
    JButton btnMainMenu = new JButton("Main Menu");
    btnMainMenu.addActionListener(new PlayerMainMenuCtrl(app, model));

    JPanel panel_1 = new JPanel();
    panel_1.setLayout(new GridLayout(0, 3, 0, 0));

    JButton button = new JButton("<->");
    panel_1.add(button);

    JButton button_1 = new JButton("?");
    panel_1.add(button_1);

    JButton button_2 = new JButton("??");
    panel_1.add(button_2);

    JPanel panel_2 = new JPanel();

    JLabel lblScore = new JLabel("Score");
    lblScore.setFont(new Font("Snap ITC", Font.PLAIN, 18));

    JLabel lblNewLabel = new JLabel("12345");
    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));

    JLabel lblMovesLeft = new JLabel("Moves Left");
    lblMovesLeft.setFont(new Font("Snap ITC", Font.PLAIN, 18));

    JLabel label = new JLabel("20");
    label.setFont(new Font("Tahoma", Font.PLAIN, 22));

    ////////////////////
    //**DO NOT TOUCH**//
    ////////////////////
    GroupLayout groupLayout = new GroupLayout(this);
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(10)
          .addComponent(boardView, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE)
          .addGap(34)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
            .addGroup(groupLayout.createSequentialGroup()
              .addComponent(btnRestart, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
              .addComponent(btnMainMenu, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
            .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
            .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
            .addGroup(groupLayout.createSequentialGroup()
              .addGap(65)
              .addComponent(lblScore, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
            .addGroup(groupLayout.createSequentialGroup()
              .addGap(84)
              .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
            .addGroup(groupLayout.createSequentialGroup()
              .addGap(65)
              .addComponent(lblMovesLeft, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
            .addGroup(groupLayout.createSequentialGroup()
              .addGap(101)
              .addComponent(label, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
          .addGap(10))
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(11)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(boardView, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE)
            .addGroup(groupLayout.createSequentialGroup()
              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                .addComponent(btnRestart, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnMainMenu, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
              .addGap(11)
              .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
              .addGap(31)
              .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
              .addGap(21)
              .addComponent(lblScore, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
              .addGap(11)
              .addComponent(lblNewLabel)
              .addGap(110)
              .addComponent(lblMovesLeft, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
              .addGap(10)
              .addComponent(label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
              .addGap(79)))
          .addGap(44))
    );
    setLayout(groupLayout);
  }
}
