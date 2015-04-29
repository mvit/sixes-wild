package boundary;

import boundary.BuilderApplication;
import controller.BuilderMainMenuCtrl;
import controller.BuilderMultiplierWeightCtrl;
import controller.BuilderNewLevelCtrl;
import controller.BuilderNumberWeightCtrl;
import controller.BuilderOpenLevelCtrl;
import controller.BuilderPreviewLevelCtrl;
import controller.BuilderRedoCtrl;
import controller.BuilderSaveLevelCtrl;
import controller.BuilderSetCellTypeCtrl;
import controller.BuilderSetTileCtrl;
import controller.BuilderSetVariationCtrl;
import controller.BuilderUndoCtrl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

import model.BuilderModel;
import model.CellType;
import model.Rules;
import model.Variation;

/**
 * BuilderLevelEditorView for the builder application
 *
 * TODO: score threshold, multiplier weight sliders, initial timer value
 *
 * @author Cem Unsal, and Eli Skeggs
 */
public class BuilderLevelEditorView extends JPanel {
  BuilderApplication app;
  BuilderModel model;
  public BuilderBoardView boardView;
  public ButtonGroup variationGroup;
  public Map<Variation, JRadioButton> variationButtons =
    new HashMap<Variation, JRadioButton>();

  public BuilderLevelEditorView(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;

    setMinimumSize(new Dimension(600,480));
    setPreferredSize(new Dimension(600, 480));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // contains all top control buttons

    JPanel panelTopControls = new JPanel();
    panelTopControls.setAlignmentX(0);
    panelTopControls.setAlignmentY(0);
    panelTopControls.setLayout(new BoxLayout(panelTopControls,
      BoxLayout.X_AXIS));
    add(panelTopControls);

    JButton btnNew = new JButton("New");
    btnNew.addActionListener(new BuilderNewLevelCtrl(app, model));
    panelTopControls.add(btnNew);

    JButton btnOpen = new JButton("Open");
    btnOpen.addActionListener(new BuilderOpenLevelCtrl(app, model));
    panelTopControls.add(btnOpen);

    JButton btnPreview = new JButton("Preview");
    btnPreview.addActionListener(new BuilderPreviewLevelCtrl(app, model));
    panelTopControls.add(btnPreview);

    JButton btnSave = new JButton("Save");
    btnSave.addActionListener(new BuilderSaveLevelCtrl(app, model));
    panelTopControls.add(btnSave);

    JButton btnClose = new JButton("Close");
    btnClose.addActionListener(new BuilderMainMenuCtrl(app, model));
    panelTopControls.add(btnClose);

    // contains all game-related options

    JPanel panelGame = new JPanel();
    panelGame.setAlignmentX(0);
    panelGame.setLayout(new BoxLayout(panelGame, BoxLayout.X_AXIS));
    add(panelGame);

    // contains controls related to the board

    JPanel panelControls = new JPanel();
    panelControls.setLayout(new BoxLayout(panelControls, BoxLayout.Y_AXIS));
    panelControls.setAlignmentY(0);
    panelGame.add(panelControls);

    JButton btnUndo = new JButton("Undo");
    btnUndo.addActionListener(new BuilderUndoCtrl(app, model));
    panelControls.add(btnUndo);

    JButton btnRedo = new JButton("Redo");
    btnRedo.addActionListener(new BuilderRedoCtrl(app, model));
    panelControls.add(btnRedo);

    JButton btnMakePlayable = new JButton("[  ]");
    btnMakePlayable.addActionListener(new BuilderSetCellTypeCtrl(app, model,
      CellType.PLAYABLE));
    panelControls.add(btnMakePlayable);

    JButton btnMakeInert = new JButton("[X]");
    btnMakeInert.addActionListener(new BuilderSetCellTypeCtrl(app, model,
      CellType.INERT));
    panelControls.add(btnMakeInert);

    JButton btnMakeSix = new JButton("6");
    btnMakeSix.addActionListener(new BuilderSetTileCtrl(app, model, 5, 1));
    panelControls.add(btnMakeSix);

    JButton btnMakeSlot = new JButton("[6]");
    btnMakeSlot.addActionListener(new BuilderSetCellTypeCtrl(app, model,
      CellType.BUCKET));
    panelControls.add(btnMakeSlot);

    // contains the variation radio buttons and the boardView

    JPanel panelBoard = new JPanel();
    panelBoard.setAlignmentY(0);
    panelGame.add(panelBoard);
    panelBoard.setLayout(new BorderLayout());

    // contains variation radio buttons

    JPanel panelType = new JPanel();
    panelType.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    panelBoard.add(panelType, BorderLayout.NORTH);

    for (Variation variation : Variation.values()) {
      JRadioButton button = new JRadioButton(variation.name);
      button.addActionListener(new BuilderSetVariationCtrl(app, model,
        variation));
      variationButtons.put(variation, button);
      panelType.add(button);
    }

    // contains radio buttons for mutual exclusivity

    variationGroup = new ButtonGroup();

    for (JRadioButton btn : variationButtons.values()) {
      variationGroup.add(btn);
    }

    // contains the board

    boardView = new BuilderBoardView(app, model);
    boardView.setAlignmentY(0);
    panelBoard.add(boardView);

    // contains the probability sliders for number generation

    JPanel panelSliders = new JPanel();
    panelGame.add(panelSliders);
    panelSliders.setAlignmentY(0);
    panelSliders.setLayout(new BoxLayout(panelSliders, BoxLayout.Y_AXIS));
    
    
    JTextField tfCounter = new JTextField();
    panelSliders.add(tfCounter);
    JPanel panelThresholds = new JPanel();
    panelSliders.add(panelThresholds);
    panelThresholds.setLayout(new BoxLayout(panelThresholds, BoxLayout.X_AXIS));
    JTextField tfThreshold1 = new JTextField();
    panelThresholds.add(tfThreshold1);
    JTextField tfThreshold2 = new JTextField();
    panelThresholds.add(tfThreshold2);
    JTextField tfThreshold3 = new JTextField();
    panelThresholds.add(tfThreshold3);    
    
    panelSliders.add(new JLabel("Number Probability"));
    // make the slider panels, styling specific to a given panel can be put in
    // an array, which would then be used in the loop
    for (int i = 0; i < Rules.maxNumber; i++) {
      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

      JLabel label = new JLabel("" + (i + 1));
      panel.add(label);

      JSlider slider = new JSlider();
      slider.addChangeListener(new BuilderNumberWeightCtrl(app, model, i));
      slider.setPaintLabels(true);
      slider.setMajorTickSpacing(20);
      slider.setMinorTickSpacing(10);
      slider.setSnapToTicks(true);
      slider.setPaintTicks(true);
      panel.add(slider);

      panelSliders.add(panel);
    }
    
    panelSliders.add(new JLabel("Multiplier Probability"));
    for (int i = 0; i < Rules.maxMultiplier; i++) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JLabel label = new JLabel("" + (i + 1));
        panel.add(label);

        JSlider slider = new JSlider();
        slider.addChangeListener(new BuilderMultiplierWeightCtrl(app, model, i));
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(10);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        panel.add(slider);

        panelSliders.add(panel);
      }
    

    updateView();
  }

  public void updateView() {
    // TODO: implement me!
    // TODO: level diffs
    variationButtons.get(model.level.rules.variation).setSelected(true);
  }
}
