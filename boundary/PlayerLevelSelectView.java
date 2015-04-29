package boundary;

import controller.PlayerLevelSelectionCtrl;
import controller.PlayerLoadLevelCtrl;
import controller.PlayerMainMenuCtrl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Level;
import model.PlayerModel;

/**
 * The View for Selecting a Level.
 *
 * @author Eli Skeggs, and Maurizio Vitale
 */
public class PlayerLevelSelectView extends JPanel {
  PlayerApplication app;
  PlayerModel model;
  String currentLevel;

  public PlayerLevelSelectView(PlayerApplication app, PlayerModel model, String currentLevel) {
    this.app = app;
    this.model = model;
    this.currentLevel = currentLevel;

    setMinimumSize(new Dimension(800,600));
    setPreferredSize(new Dimension(800, 600));
    setLayout(new GridLayout(0, 2, 0, 0));

    //Two SubPanels

    //panelLevel - contains level preview, name type and previous score, along with options for the level

    JPanel panelLevel = new JPanel();
    panelLevel.setLayout(new BorderLayout(0, 0));

    //panelLevelPreview - where gameboard will be loaded

    JPanel panelLevelPreview = new JPanel();
    panelLevel.add(panelLevelPreview, BorderLayout.CENTER);

    //panelLevelOptions - holds play level and reset score

    JPanel panelLevelOptions = new JPanel();
    panelLevelOptions.setBorder(null);
    panelLevel.add(panelLevelOptions, BorderLayout.SOUTH);

    //btnPLayLevel, explanatory

    JButton btnPlayLevel = new JButton("Play Level");
    updatePlayButton(btnPlayLevel);
    panelLevelOptions.add(btnPlayLevel);

    //debating heavily on this one, holds level info and navigation panel,
    //considering merging level info and gameboard into a single panel instead

    JPanel panelTopContent = new JPanel();
    panelTopContent.setLayout(new BoxLayout(panelTopContent,BoxLayout.Y_AXIS));
    panelLevel.add(panelTopContent, BorderLayout.NORTH);

    //panelNavigation holds whatever navigation purpose you need

    JPanel panelNavigation = new JPanel();
    panelTopContent.add(panelNavigation);

    //btnMainMenu

    JButton btnMainMenu = new JButton("Main Menu");
    btnMainMenu.setSize(btnMainMenu.getWidth(), 29);
    panelNavigation.add(btnMainMenu);
    btnMainMenu.addActionListener(new PlayerMainMenuCtrl(app, model));

    JPanel panelLevelInfo = new JPanel();

    panelLevelInfo.setLayout(new GridLayout(0, 1, 0, 0));

    JLabel lblLevel = new JLabel("Level ");
    panelLevelInfo.add(lblLevel);
    lblLevel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel lblType = new JLabel("Type: ");
    panelLevelInfo.add(lblType);

    JLabel lblScore = new JLabel("Score: ");
    panelLevelInfo.add(lblScore);

    panelTopContent.add(panelLevelInfo);

    add(panelLevel);

    add(makeLevelGrid());
  }

  /**
   * Make a grid of buttons for level selection by reading the levels resource
   * directory
   *
   * @return The new JPanel containing the level select buttons.
   */
  protected JPanel makeLevelGrid() {
    File dir = new File("resource/levels/");
    File[] list = dir.listFiles();

    JPanel content = new JPanel();

    if (list == null) {
      // TODO: dialog?
      return content;
    }

    int width = 4, height = (int) Math.ceil((double) list.length / width);
    content.setLayout(new GridLayout(height, width, 0, 0));

    for (File file : list) {
      // ensure the filename is numeric
      String name = file.getName();
      int levelNumber;
      try {
        levelNumber = Integer.parseInt(name, 10);
      } catch (NumberFormatException err) {
        continue;
      }

      // exclude other files by checking the header
      if (levelNumber > 0 && Level.checkHeader(file)) {
        JPanel panelLevel = new JPanel();
        // TODO: when we add the action listeners to these buttons, they should
        // reference (levelNumber - 1)
        JButton selectButton = new JButton(name);
        selectButton.addActionListener(new PlayerLevelSelectionCtrl(app, model, name));
        panelLevel.add(selectButton);
        content.add(panelLevel);
      }
    }
    return content;
  }
  
  private void updatePlayButton(JButton button) {
	  if(currentLevel == null) {
		  button.setEnabled(false);
	  }
	  else { 
		  button.addActionListener(new PlayerLoadLevelCtrl(app, model, currentLevel));
	  }
  }
}
