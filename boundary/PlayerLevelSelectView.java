package boundary;

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

import model.PlayerModel;

/**
 * The View for Selecting a Level.
 *
 * @author Maurizio Vitale
 */
public class PlayerLevelSelectView extends JPanel {

  PlayerApplication app;
  PlayerModel model;

  public PlayerLevelSelectView(PlayerApplication app, PlayerModel model) {

  this.app = app;
  this.model = model;

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
  panelLevelOptions.add(btnPlayLevel);
  btnPlayLevel.addActionListener(new PlayerLoadLevelCtrl(app, model));

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



  this.add(panelLevel);

  this.add(makeLevelGrid());
  }
  
  private JPanel makeLevelGrid() {
	  JPanel content = new JPanel();
	  content.setLayout(new GridLayout(4,4,0,0));
	  int i = 1;
	  File dir = new File("resource/levels/");
	  File[] list = dir.listFiles();
	  if (list != null){
		  for(File file: list) {
			 if (!file.getName().equals("README.md")) {
			  JPanel panelLevel = new JPanel();
			  JButton btnLevel = new JButton(file.getName());
			  panelLevel.add(btnLevel);
			  content.add(panelLevel);
			 }
		  }
	  }
	  return content;
  }
}
