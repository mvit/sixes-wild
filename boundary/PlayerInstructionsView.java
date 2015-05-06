package boundary;

import controller.PlayerMainMenuCtrl;
import java.awt.BorderLayout;
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
    txtpnInSixesWild.setText("To play Sixes Wild, click and drag your mouse across adjacent numbered tiles that add up to 6. Try to get the highest score possible!\r\n\r\nThere are four current variations:\r\n  Puzzle: Classic! Complete moves within the move limit and get a high score.\r\n  Lightning: Complete moves against a timer!\r\n  Elimination: Try to cover the entire board with marked tiles!\r\n  Release: Drop those \"6\" tiles into buckets!\r\n\r\nThere are three available special moves:\r\n  Swap: Swap two adjacent tiles by clicking on one and then the other.\r\n  Scramble: Scramble the board.\r\n  Remove: Remove a tile by clicking on it.");
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
