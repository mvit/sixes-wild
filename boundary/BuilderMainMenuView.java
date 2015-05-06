package boundary;

import controller.BuilderNewLevelCtrl;
import controller.BuilderOpenLevelCtrl;
import controller.ExitCtrl;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.BuilderModel;

/**
 * BuilderMainMenuView for the builder application
 *
 * @author Cem Unsal, Eli Skeggs, and Maurizio Vitale
 */
public class BuilderMainMenuView extends JPanel {
  BuilderApplication app;
  BuilderModel model;
  public JButton btnNewLevel;

  public BuilderMainMenuView(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;

    setMinimumSize(new Dimension(600,480));
    setPreferredSize(new Dimension(600, 480));

    //Make Layout GridBagLayout

    GridBagLayout gridBagLayout = new GridBagLayout();
    setLayout(gridBagLayout);

    //Add ContentPanel in center

    JPanel panelContent = new JPanel();
    panelContent.setBorder(null);
    panelContent.setSize(300,500);
    panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS));
    add(panelContent);

    //Add Labels and Buttons to center ContentPanel

    JLabel lblBuilder = new JLabel("\u262D Builder");
    lblBuilder.setAlignmentX(Component.CENTER_ALIGNMENT);
    lblBuilder.setAlignmentY(Component.CENTER_ALIGNMENT);
    panelContent.add(lblBuilder);

    btnNewLevel = new JButton("New Level");
    btnNewLevel.addActionListener(new BuilderNewLevelCtrl(app, model));
    btnNewLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnNewLevel.setAlignmentY(Component.CENTER_ALIGNMENT);
    panelContent.add(btnNewLevel);

    JButton btnOpen = new JButton("Open Level");
    btnOpen.addActionListener(new BuilderOpenLevelCtrl(app, model));
    btnOpen.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnOpen.setAlignmentY(Component.CENTER_ALIGNMENT);
    panelContent.add(btnOpen);

    JButton btnQuitBuilder = new JButton("Quit Builder");
    btnQuitBuilder.addActionListener(new ExitCtrl());
    btnQuitBuilder.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnQuitBuilder.setAlignmentY(Component.CENTER_ALIGNMENT);
    panelContent.add(btnQuitBuilder);

  }
}
