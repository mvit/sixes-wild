package boundary;

import controller.PlayerLevelSelectCtrl;
import controller.PlayerLoadLevelCtrl;
import controller.PlayerMainMenuCtrl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Level;
import model.LevelProgress;
import model.PlayerModel;
import utils.StreamFileUtils;

/**
 * The View for Selecting a Level.
 *
 * @author Eli Skeggs, and Maurizio Vitale
 */
public class PlayerLevelSelectView extends JPanel {
  PlayerApplication app;
  PlayerModel model;

  public JPanel panelTopContent, panelLevel;

  public JPanel panelLevelInfo = null;
  public JLabel lblLevel = null, lblType = null, lblScore = null;

  public PlayerPreviewBoardView boardView;

  public JButton[] selectButtons;
  public JButton btnPlayLevel;

  protected PlayerLoadLevelCtrl prevLoader;

  public PlayerLevelSelectView(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;

    setMinimumSize(new Dimension(800, 600));
    setPreferredSize(new Dimension(800, 600));
    setLayout(new GridLayout(0, 2, 0, 0));

    // level preview, name type and previous score, plus level options
    panelLevel = new JPanel();
    panelLevel.setLayout(new BorderLayout(0, 0));

    // debating heavily on this one, holds level info and navigation panel,
    // considering merging level info and gameboard into a single panel instead
    panelTopContent = new JPanel();
    panelTopContent.setLayout(new BoxLayout(panelTopContent, BoxLayout.Y_AXIS));
    panelLevel.add(panelTopContent, BorderLayout.NORTH);

    // holds whatever navigation purpose you need
    JPanel panelNavigation = new JPanel();
    panelTopContent.add(panelNavigation);

    JButton btnMainMenu = new JButton("Main Menu");
    btnMainMenu.setSize(btnMainMenu.getWidth(), 29);
    panelNavigation.add(btnMainMenu);
    btnMainMenu.addActionListener(new PlayerMainMenuCtrl(app, model));

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

    ArrayList<File> files = new ArrayList<File>();
    for (File file : list) {
      String name = file.getName();
      int levelNumber;
      try {
        levelNumber = Integer.parseInt(name, 10) - 1;
      } catch (NumberFormatException err) {
        continue;
      }

      if (levelNumber >= 0) {
        int count = files.size();
        if (levelNumber == count) {
          files.add(file);
        } else if (levelNumber < count) {
          files.set(levelNumber, file);
        } else {
          int additional = levelNumber - count + 1;
          while (--additional > 0) {
            files.add(null);
          }
          files.add(file);
        }
      }
    }

    selectButtons = new JButton[files.size()];

    int width = 4, height = (int) Math.ceil((double) files.size() / width);
    content.setLayout(new GridLayout(height, width, 0, 0));

    boolean cutOff = false;

    ListIterator<File> iterator = files.listIterator();
    while (iterator.hasNext()) {
      int levelNumber = iterator.nextIndex();
      File file = iterator.next();
      String name = file.getName();

      JPanel panelLevel = new JPanel();
      JButton selectButton = new JButton(name);

      // try to load the level, nulling the file if not available
      Level level = null;
      if (file != null) {
        Object result = StreamFileUtils.readStream(file, Level.getReadable());

        if (result == null) {
          file = null;
        } else {
          level = (Level) result;
        }
      }

      if (file == null) {
        selectButton.setText("<html>Level " + name +
          "<br>Not available</html>");
      } else {
        // get the level's progress object
        LevelProgress progress = model.progress.getLevelProgress(levelNumber);

        // if one of the previous levels was not completed, disable the button
        selectButton.setEnabled(!cutOff);

        // if we have beaten all previous levels, and we haven't beaten this one
        if (!cutOff && (progress == null || !progress.passedLevel ||
            (level.rules.scoreThresholds.length > 0 &&
            progress.bestScore < level.rules.scoreThresholds[0]))) {
          // no further levels can be attempted
          cutOff = true;
        }

        selectButton.addActionListener(new PlayerLevelSelectCtrl(app, model,
          name));
        selectButton.setText("<html>Level " + name + "<br>" + (progress == null
          ? "Not attempted" : (level.rules.variation.name.equals("Lightning") ? "Time Left: " + progress.getScore() : "Score: " + progress.getScore())) +
          "<br>Type: " + level.rules.variation.name + "</html>");
      }

      selectButtons[levelNumber] = selectButton;
      panelLevel.add(selectButton);
      content.add(panelLevel);
    }
    return content;
  }

  // switch the left display pane to the correct active view
  public void switchActive() {
    String variation = model.level.rules.variation.name;
    int levelNumber = model.levelnum;

    LevelProgress progress = model.progress.getLevelProgress(levelNumber);
    int score = progress == null ? 0 : progress.getScore();

    if (panelLevelInfo == null) {
      panelLevelInfo = new JPanel();

      panelLevelInfo.setLayout(new GridLayout(0, 1, 0, 0));

      lblLevel = new JLabel("Level " + (levelNumber + 1));
      panelLevelInfo.add(lblLevel);
      lblLevel.setHorizontalAlignment(SwingConstants.CENTER);

      lblType = new JLabel("Type: " + variation);
      panelLevelInfo.add(lblType);
      if (!variation.equals("Lightning")) {
    	  lblScore = new JLabel("Score: " + score);
      }
      else {
    	  lblScore = new JLabel("Time Left: " + score);
      }
      panelLevelInfo.add(lblScore);

      BufferedImage starImg = app.loader.getResource("star.png");
      BufferedImage blankStarImg = app.loader.getResource("starempty.png");

      JPanel panelStars = new JPanel();
      panelStars.setLayout(new GridLayout(0,3,0,0));
      for (int i = 0; i < 3; i++) {
    	  JLabel star;
    	  if (score >= model.level.rules.scoreThresholds[i]) {
    		  star = new JLabel(new ImageIcon(starImg));
    	  }
    	  else {
    		  star = new JLabel(new ImageIcon(blankStarImg));
    	  }
    	  panelStars.add(star);
      }
      panelLevelInfo.add(panelStars);
      panelTopContent.add(panelLevelInfo);

      // a preview of the game board
      JPanel panelLevelPreview = new JPanel();
      panelLevel.add(panelLevelPreview, BorderLayout.CENTER);

      // play level and reset score
      JPanel panelLevelOptions = new JPanel();
      panelLevelOptions.setBorder(null);
      panelLevel.add(panelLevelOptions, BorderLayout.SOUTH);

      btnPlayLevel = new JButton("Play Level");
      panelLevelOptions.add(btnPlayLevel);

      JButton btnResetScore = new JButton("Reset Score");
      btnResetScore.setEnabled(false);
      panelLevelOptions.add(btnResetScore);

      panelLevel.revalidate();
    } else {
      lblLevel.setText("Level " + (levelNumber + 1));
      lblType.setText("Type: " + variation);
      lblScore.setText("Score: " + score);

      btnPlayLevel.removeActionListener(prevLoader);

      if (boardView != null) {
        panelLevel.remove(boardView);
      }
    }

    prevLoader = new PlayerLoadLevelCtrl(app, model, (levelNumber + 1) + "");
    btnPlayLevel.addActionListener(prevLoader);

    boardView = new PlayerPreviewBoardView(app, model);
    boardView.setAlignmentY(0);
    panelLevel.add(boardView);
  }
}
