package boundary;

import controller.PlayerLoadLevelCtrl;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import model.PlayerModel;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class PlayerLevelSelectView extends JPanel {
  PlayerApplication app;
  PlayerModel model;

  /**
   * Create the panel.
   */
  public PlayerLevelSelectView(PlayerApplication app, PlayerModel model) {
    
	this.app = app;
    this.model = model;
    setMinimumSize(new Dimension(800,600));
    setPreferredSize(new Dimension(800, 600));
    setLayout(new GridLayout(0, 2, 0, 0));
    
    //Two SubPanels
    
    //levelPreviewPanel - contains level preview, name type and previous score, along with options for the level
    JPanel panelLevel = new JPanel();
    panelLevel.setLayout(new BorderLayout(0, 0));
    this.add(panelLevel);
    
    JPanel panelLevelPreview = new JPanel();
    panelLevel.add(panelLevelPreview, BorderLayout.CENTER);
    
    JPanel panelLevelOptions = new JPanel();
    panelLevelOptions.setBorder(null);
    panelLevel.add(panelLevelOptions, BorderLayout.SOUTH);
   
    JButton btnPlayLevel = new JButton("Play Level");
    panelLevelOptions.add(btnPlayLevel);
    
    JPanel panelLevelInfo = new JPanel();
    panelLevelInfo.setLayout(new GridLayout(0, 1, 0, 0));
    
    JLabel lblLevel = new JLabel("Level ");
    panelLevelInfo.add(lblLevel);
    lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
        
    JLabel lblType = new JLabel("Type: ");
    panelLevelInfo.add(lblType);
            
    JLabel lblScore = new JLabel("Score: ");
    panelLevelInfo.add(lblScore);
    panelLevel.add(panelLevelInfo, BorderLayout.NORTH);
    
    btnPlayLevel.addActionListener(new PlayerLoadLevelCtrl(app, model));
    
  //panelLevelGrid - Contains Buttons for each level that exists
    JPanel panelLevelGrid = new JPanel();
    panelLevelGrid.setLayout(new GridLayout(0, 4, 0, 0));
    this.add(panelLevelGrid);
    
    //Buttons for Level Select Grid
    JButton btnLevel1 = new JButton("Level 1");
    panelLevelGrid.add(btnLevel1);

    JButton btnLevel2 = new JButton("Level 2");
    panelLevelGrid.add(btnLevel2);
    
    JButton btnLevel3 = new JButton("Level 3");
    panelLevelGrid.add(btnLevel3);
    
    JButton btnLevel4 = new JButton("Level 4");
    panelLevelGrid.add(btnLevel4);
    
    JButton btnLevel5 = new JButton("Level 5");
    panelLevelGrid.add(btnLevel5);
    
    JButton btnLevel6 = new JButton("Level 6");
    panelLevelGrid.add(btnLevel6);
    
    JButton btnLevel7 = new JButton("Level 7");
    panelLevelGrid.add(btnLevel7);
    
    JButton btnLevel8 = new JButton("Level 8");
    panelLevelGrid.add(btnLevel8);
    
    JButton btnLevel9 = new JButton("Level 9");
    panelLevelGrid.add(btnLevel9);
    
    JButton btnLevel10 = new JButton("Level 10");
    panelLevelGrid.add(btnLevel10);
    
    JButton btnLevel11 = new JButton("Level 11");
    panelLevelGrid.add(btnLevel11);
    
    JButton btnLevel12 = new JButton("Level 12");
    panelLevelGrid.add(btnLevel12);
    
    JButton btnLevel13 = new JButton("Level 13");
    panelLevelGrid.add(btnLevel13);
    
    JButton btnLevel14 = new JButton("Level 14");
    panelLevelGrid.add(btnLevel14);
    
    JButton btnLevel15 = new JButton("Level 15");
    panelLevelGrid.add(btnLevel15);
    
    JButton btnLevel16 = new JButton("Level 16");
    panelLevelGrid.add(btnLevel16);
  }
}
