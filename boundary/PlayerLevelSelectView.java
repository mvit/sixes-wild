package boundary;

import controller.PlayerLevelSelectionCtrl;
import controller.PlayerLoadLevelCtrl;
import controller.PlayerMainMenuCtrl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Level;
import model.LevelProgress;
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

  int score = 0;
  String variation = "";

  public PlayerPreviewBoardView boardView;

  public PlayerLevelSelectView(PlayerApplication app, PlayerModel model,
      String currentLevel) {
    this.app = app;
    this.model = model;
    this.currentLevel = currentLevel;

    if (currentLevel != null) {
      variation = model.level.rules.variation.name;

      int levelNumber = Integer.parseInt(currentLevel);
      LevelProgress progress = model.progress.getLevelProgress(levelNumber);
      score = progress == null ? 0 : progress.getScore();
    }

    setMinimumSize(new Dimension(800, 600));
    setPreferredSize(new Dimension(800, 600));
    setLayout(new GridLayout(0, 2, 0, 0));

    //Two SubPanels

    //panelLevel - contains level preview, name type and previous score, along with options for the level

    JPanel panelLevel = new JPanel();
    panelLevel.setLayout(new BorderLayout(0, 0));

    //panelLevelPreview - where gameboard will be loaded

    JPanel panelLevelPreview = new JPanel();
    panelLevel.add(panelLevelPreview, BorderLayout.CENTER);
    if (currentLevel != null) {
      boardView = new PlayerPreviewBoardView(app, model);
      boardView.setAlignmentY(0);
      panelLevel.add(boardView);
    }

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

    JLabel lblType = new JLabel("Type: " + variation);
    panelLevelInfo.add(lblType);

    JLabel lblScore = new JLabel("Score: " + score);
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

    // TODO: this will not necessarily iterate in order; put it in a sorted set
    // of some kind?
    for (File file : list) {
      // ensure the filename is numeric
      String name = file.getName();
      int levelNumber;
      try {
        levelNumber = Integer.parseInt(name, 10) - 1;
      } catch (NumberFormatException err) {
        continue;
      }

      if (levelNumber >= 0) {
        Level level;
        try {
          level = new Level(new DataInputStream(new FileInputStream(file)));
        } catch (IOException err) {
          // not a valid level
          continue;
        }

        JPanel panelLevel = new JPanel();
        JButton selectButton = new JButton(name);

        if (model.progress.completedLevels() >= levelNumber) {
          LevelProgress progress = model.progress.getLevelProgress(levelNumber);
          selectButton.addActionListener(new PlayerLevelSelectionCtrl(app,
            model, name));
          selectButton.setEnabled(true);
          selectButton.setText("<html>" + name + "<br>Score: " +
            (progress == null ? 0 : progress.getScore()) + "<br>Variation: " +
            level.rules.variation.name + "</html>");
        } else {
          selectButton.setEnabled(false);
        }

        panelLevel.add(selectButton);
        content.add(panelLevel);
      }
    }
    return content;
  }

  protected void updatePlayButton(JButton button) {
    if (currentLevel == null) {
      button.setEnabled(false);
    } else {
      button.addActionListener(new PlayerLoadLevelCtrl(app, model, currentLevel));
    }
  }
}
