package boundary;

import controller.PlayerMainMenuCtrl;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import model.PlayerModel;

/**
 * The view containing the instructions for sixes-wild.
 *
 * @author Bailey Sheridan
 */
public class PlayerInstructionsView extends JPanel {
  PlayerModel model;
  PlayerApplication app;

  /**
   * Creates an instruction page.
   *
   * @param app
   * @param model
   */
  public PlayerInstructionsView(PlayerApplication app, PlayerModel model) {
    setMinimumSize(new Dimension(600,480));
    setPreferredSize(new Dimension(600, 480));
    setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    add(panel, BorderLayout.CENTER);
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

    JTextPane txtpnInSixesWild = new JTextPane();
    txtpnInSixesWild.setEditable(false);
    txtpnInSixesWild.setText("To play Sixes Wild, click and drag your mouse " +
      "across adjacent numbered tiles that add up to 6! Try to get the " +
      "highest score possible!\r\n\r\nThere are three special moves you can " +
      "do:\r\n  Swap: Swap two adjacent tiles\r\n  Scramble: Scramble the " +
      "board\r\n  Remove: Remove a tile");
    panel.add(txtpnInSixesWild);

    JLabel lblInstructions = new JLabel("Instructions");
    lblInstructions.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
    add(lblInstructions, BorderLayout.NORTH);

    JButton btnBack = new JButton("Back");
    btnBack.addActionListener(new PlayerMainMenuCtrl(app, model));
    add(btnBack, BorderLayout.SOUTH);
  }
}
