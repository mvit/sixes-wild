package boundary;

import boundary.BuilderApplication;
import controller.BuilderMainMenuCtrl;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SpringLayout;

import model.BuilderModel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BuilderLevelEditorView extends JPanel {
  BuilderApplication app;
  BuilderModel model;

  public BuilderLevelEditorView(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;

    JPanel panel = new JPanel();
    panel.setAlignmentY(Component.TOP_ALIGNMENT);

    JButton btnNew = new JButton("New");

    JButton btnOpen = new JButton("Open");

    JButton btnSave = new JButton("Save");
    JButton btnClose = new JButton("Close");
    btnClose.addActionListener(new BuilderMainMenuCtrl(app, model));

    JButton btnUndo = new JButton("Undo");

    JButton btnRedo = new JButton("Redo");
    GroupLayout gl_panel = new GroupLayout(panel);
    gl_panel.setHorizontalGroup(
      gl_panel.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel.createSequentialGroup()
          .addGap(10)
          .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_panel.createSequentialGroup()
              .addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
              .addGap(6)
              .addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
            .addGroup(gl_panel.createSequentialGroup()
              .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
              .addGap(6)
              .addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
            .addGroup(gl_panel.createSequentialGroup()
              .addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
              .addGap(7)
              .addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))))
    );
    gl_panel.setVerticalGroup(
      gl_panel.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel.createSequentialGroup()
          .addGap(10)
          .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
            .addComponent(btnNew)
            .addComponent(btnOpen))
          .addGap(6)
          .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
            .addComponent(btnSave)
            .addComponent(btnClose))
          .addGap(6)
          .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
            .addComponent(btnUndo)
            .addComponent(btnRedo)))
    );
    panel.setLayout(gl_panel);

    JPanel panel_1 = new JPanel();

    JPanel panel_4 = new JPanel();
    panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

    JRadioButton rdbtnPuzzle = new JRadioButton("Puzzle");
    panel_4.add(rdbtnPuzzle);

    JRadioButton rdbtnLightning = new JRadioButton("Lightning");
    panel_4.add(rdbtnLightning);

    JRadioButton rdbtnElimination = new JRadioButton("Elimination");
    panel_4.add(rdbtnElimination);

    JRadioButton rdbtnRelease = new JRadioButton("Release");
    panel_4.add(rdbtnRelease);

    JPanel gridPanel = new JPanel();

    JPanel panel_5 = new JPanel();
    SpringLayout sl_panel_5 = new SpringLayout();
    panel_5.setLayout(sl_panel_5);

    JPanel panel_6 = new JPanel();
    sl_panel_5.putConstraint(SpringLayout.SOUTH, panel_6, 45, SpringLayout.NORTH, panel_5);
    sl_panel_5.putConstraint(SpringLayout.NORTH, panel_6, 0, SpringLayout.NORTH, panel_5);
    sl_panel_5.putConstraint(SpringLayout.WEST, panel_6, 0, SpringLayout.WEST, panel_5);
    panel_5.add(panel_6);
    panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

    JLabel label = new JLabel("1");
    panel_6.add(label);

    JSlider slider = new JSlider();
    panel_6.add(slider);

    JPanel panel_7 = new JPanel();
    sl_panel_5.putConstraint(SpringLayout.NORTH, panel_7, 6, SpringLayout.SOUTH, panel_6);
    sl_panel_5.putConstraint(SpringLayout.WEST, panel_7, 0, SpringLayout.WEST, panel_5);
    sl_panel_5.putConstraint(SpringLayout.EAST, panel_7, 0, SpringLayout.EAST, panel_5);
    sl_panel_5.putConstraint(SpringLayout.EAST, panel_6, 0, SpringLayout.EAST, panel_7);
    
    JLabel label_6 = new JLabel("50%");
    panel_6.add(label_6);
    panel_5.add(panel_7);
    panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));

    JLabel label_1 = new JLabel("2");
    panel_7.add(label_1);

    JSlider slider_1 = new JSlider();
    panel_7.add(slider_1);

    JPanel panel_8 = new JPanel();
    sl_panel_5.putConstraint(SpringLayout.NORTH, panel_8, 102, SpringLayout.NORTH, panel_5);
    sl_panel_5.putConstraint(SpringLayout.WEST, panel_8, 0, SpringLayout.WEST, panel_5);
    sl_panel_5.putConstraint(SpringLayout.EAST, panel_8, 0, SpringLayout.EAST, panel_5);
    sl_panel_5.putConstraint(SpringLayout.SOUTH, panel_7, -6, SpringLayout.NORTH, panel_8);
    
    JLabel label_7 = new JLabel("50%");
    panel_7.add(label_7);
    panel_5.add(panel_8);
    panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));

    JLabel label_2 = new JLabel("3");
    panel_8.add(label_2);

    JSlider slider_2 = new JSlider();
    panel_8.add(slider_2);

    JPanel panel_9 = new JPanel();
    sl_panel_5.putConstraint(SpringLayout.NORTH, panel_9, 153, SpringLayout.NORTH, panel_5);
    sl_panel_5.putConstraint(SpringLayout.WEST, panel_9, 0, SpringLayout.WEST, panel_5);
    sl_panel_5.putConstraint(SpringLayout.EAST, panel_9, 0, SpringLayout.EAST, panel_5);
    sl_panel_5.putConstraint(SpringLayout.SOUTH, panel_8, -6, SpringLayout.NORTH, panel_9);
    
    JLabel label_8 = new JLabel("50%");
    panel_8.add(label_8);
    panel_5.add(panel_9);
    panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));

    JLabel label_3 = new JLabel("4");
    panel_9.add(label_3);

    JSlider slider_3 = new JSlider();
    panel_9.add(slider_3);

    JPanel panel_10 = new JPanel();
    sl_panel_5.putConstraint(SpringLayout.SOUTH, panel_9, -6, SpringLayout.NORTH, panel_10);
    sl_panel_5.putConstraint(SpringLayout.SOUTH, panel_10, 249, SpringLayout.NORTH, panel_5);
    sl_panel_5.putConstraint(SpringLayout.NORTH, panel_10, 204, SpringLayout.NORTH, panel_5);
    sl_panel_5.putConstraint(SpringLayout.WEST, panel_10, 0, SpringLayout.WEST, panel_5);
    sl_panel_5.putConstraint(SpringLayout.EAST, panel_10, 0, SpringLayout.EAST, panel_5);
    
    JLabel label_9 = new JLabel("50%");
    panel_9.add(label_9);
    panel_5.add(panel_10);
    panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.X_AXIS));

    JLabel label_4 = new JLabel("5");
    panel_10.add(label_4);

    JSlider slider_4 = new JSlider();
    panel_10.add(slider_4);
    
    JLabel label_10 = new JLabel("50%");
    panel_10.add(label_10);
    GroupLayout gl_panel_1 = new GroupLayout(panel_1);
    gl_panel_1.setHorizontalGroup(
      gl_panel_1.createParallelGroup(Alignment.LEADING)
        .addComponent(gridPanel, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        .addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
          .addContainerGap()
          .addComponent(panel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addContainerGap())
    );
    gl_panel_1.setVerticalGroup(
      gl_panel_1.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel_1.createSequentialGroup()
          .addGap(10)
          .addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
          .addGap(10)
          .addComponent(gridPanel, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE))
    );
    panel_1.setLayout(gl_panel_1);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    add(panel);
    add(panel_1);
    add(panel_5);
    
    JPanel panel_2 = new JPanel();
    sl_panel_5.putConstraint(SpringLayout.NORTH, panel_2, 6, SpringLayout.SOUTH, panel_10);
    sl_panel_5.putConstraint(SpringLayout.WEST, panel_2, 0, SpringLayout.WEST, panel_6);
    sl_panel_5.putConstraint(SpringLayout.SOUTH, panel_2, 51, SpringLayout.SOUTH, panel_10);
    sl_panel_5.putConstraint(SpringLayout.EAST, panel_2, 0, SpringLayout.EAST, panel_6);
    panel_5.add(panel_2);
    panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
    
    JLabel label_5 = new JLabel("6");
    panel_2.add(label_5);
    
    JSlider slider_5 = new JSlider();
    panel_2.add(slider_5);
    
    JLabel label_11 = new JLabel("50%");
    panel_2.add(label_11);
    ButtonGroup gameType = new ButtonGroup();
    gameType.add(rdbtnPuzzle);
    gameType.add(rdbtnLightning);
    gameType.add(rdbtnElimination);
    gameType.add(rdbtnRelease);
  }
}
