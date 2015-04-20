package boundary;

import boundary.BuilderApplication;
import controller.BuilderMainMenuCtrl;
import controller.BuilderRedoCtrl;
import controller.BuilderUndoCtrl;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import model.BuilderModel;

/**
* BuilderLevelEditorView for the builder application
*
* @author Cem Unsal and Maurizio Vitale
*/
public class BuilderLevelEditorView extends JPanel {
    BuilderApplication app;
    BuilderModel model;
    public PlayerBoardView boardView;

    public BuilderLevelEditorView(BuilderApplication app, BuilderModel model) {
        this.app = app;
        this.model = model;

        setMinimumSize(new Dimension(600,480));
        setPreferredSize(new Dimension(600, 480));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        //panelControls contain all Top control buttons

        JPanel panelTopControls = new JPanel();
        panelTopControls.setAlignmentX(0);
        panelTopControls.setAlignmentY(0);
        panelTopControls.setLayout(new BoxLayout(panelTopControls, BoxLayout.X_AXIS));
        add(panelTopControls);

        JButton btnNew = new JButton("New");
        panelTopControls.add(btnNew);

        JButton btnOpen = new JButton("Open");
        panelTopControls.add(btnOpen);

        JButton btnSave = new JButton("Save");
        panelTopControls.add(btnSave);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new BuilderMainMenuCtrl(app, model));
        panelTopControls.add(btnClose);

        //panelGame contains all Game related options

        JPanel panelGame = new JPanel();
        panelGame.setAlignmentX(0);
        panelGame.setLayout(new BoxLayout(panelGame, BoxLayout.X_AXIS));
        add(panelGame);

        //panelControls contains controls related to the board

        JPanel panelControls = new JPanel();
        panelControls.setLayout(new BoxLayout(panelControls, BoxLayout.Y_AXIS));
        panelControls.setAlignmentY(0);
        panelGame.add(panelControls);

        JButton btnUndo = new JButton("Undo");
        btnUndo.addActionListener(new BuilderUndoCtrl(app, model));
        panelControls.add(btnUndo);

        JButton btnRedo = new JButton("Redo");
        btnUndo.addActionListener(new BuilderRedoCtrl(app, model));
        panelControls.add(btnRedo);

        JButton btnMakePlayable = new JButton("[  ]");
        panelControls.add(btnMakePlayable);

        JButton btnMakeInert = new JButton("[X]");
        panelControls.add(btnMakeInert);

        JButton btnMakeSix = new JButton("6");
        panelControls.add(btnMakeSix);

        JButton btnMakeSlot = new JButton("[6]");
        panelControls.add(btnMakeSlot);

        //panelBoard contains panelType and panelGrid

        JPanel panelBoard = new JPanel();
        panelBoard.setAlignmentY(0);
        panelGame.add(panelBoard);
        panelBoard.setLayout(new BoxLayout(panelBoard, BoxLayout.Y_AXIS));

        //panelType contains mutually exclusive radio buttons

        JPanel panelType = new JPanel();
        panelType.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelBoard.add(panelType);

        JRadioButton rdbtnPuzzle = new JRadioButton("Puzzle");
        panelType.add(rdbtnPuzzle);

        JRadioButton rdbtnLightning = new JRadioButton("Lightning");
        panelType.add(rdbtnLightning);

        JRadioButton rdbtnElimination = new JRadioButton("Elimination");
        panelType.add(rdbtnElimination);

        JRadioButton rdbtnRelease = new JRadioButton("Release");
        panelType.add(rdbtnRelease);

        //Button Group contains radio Buttons to make them mutually exclusive

        ButtonGroup gameType = new ButtonGroup();

        gameType.add(rdbtnPuzzle);
        gameType.add(rdbtnLightning);
        gameType.add(rdbtnElimination);
        gameType.add(rdbtnRelease);

        //panelGrid contains the Board Inside
        JPanel panelGrid = new JPanel();
        panelGrid.setLayout(new BoxLayout(panelGrid, BoxLayout.X_AXIS));
        panelGrid.setSize(400,400);
        panelBoard.add(panelGrid);


        //panelSliders contain probability Sliders for number generation
        JPanel panelSliders = new JPanel();
        panelGame.add(panelSliders);
        panelSliders.setAlignmentY(0);
        panelSliders.setLayout(new BoxLayout(panelSliders, BoxLayout.Y_AXIS));

        //Code for each slider
        int x = 20;//Major Tick Spacing
        int y = 10;//Minor Tick Spacing

        //Slider 1
        //Make the panel
        JPanel panelSlider1 = new JPanel();
        panelSlider1.setLayout(new BoxLayout(panelSlider1, BoxLayout.X_AXIS));
        panelSliders.add(panelSlider1);
        //Make the Label
        JLabel lblNum1 = new JLabel("1");
        panelSlider1.add(lblNum1);
        //Make the Slider
        JSlider slider1 = new JSlider();
        slider1.setPaintLabels(true);
        slider1.setMajorTickSpacing(x);
        slider1.setMinorTickSpacing(y);
        slider1.setSnapToTicks(true);
        slider1.setPaintTicks(true);
        panelSlider1.add(slider1);

        //Slider 2
        //Make the panel
        JPanel panelSlider2 = new JPanel();
        panelSlider2.setLayout(new BoxLayout(panelSlider2, BoxLayout.X_AXIS));
        panelSliders.add(panelSlider2);
        //Make the Label
        JLabel lblNum2 = new JLabel("2");
        panelSlider2.add(lblNum2);
        //Make the Slider
        JSlider slider2 = new JSlider();
        slider2.setPaintLabels(true);
        slider2.setMajorTickSpacing(x);
        slider2.setMinorTickSpacing(y);
        slider2.setSnapToTicks(true);
        slider2.setPaintTicks(true);
        panelSlider2.add(slider2);

        //Slider 3
        //Make the panel
        JPanel panelSlider3 = new JPanel();
        panelSlider3.setLayout(new BoxLayout(panelSlider3, BoxLayout.X_AXIS));
        panelSliders.add(panelSlider3);
        //Make the Label
        JLabel lblNum3 = new JLabel("3");
        panelSlider3.add(lblNum3);
        //Make the Slider
        JSlider slider3 = new JSlider();
        slider3.setPaintLabels(true);
        slider3.setMajorTickSpacing(x);
        slider3.setMinorTickSpacing(y);
        slider3.setSnapToTicks(true);
        slider3.setPaintTicks(true);
        panelSlider3.add(slider3);

        //Slider 4
        //Make the panel
        JPanel panelSlider4 = new JPanel();
        panelSlider4.setLayout(new BoxLayout(panelSlider4, BoxLayout.X_AXIS));
        panelSliders.add(panelSlider4);
        //Make the Label
        JLabel lblNum4 = new JLabel("4");
        panelSlider4.add(lblNum4);
        //Make the Slider
        JSlider slider4 = new JSlider();
        slider4.setPaintLabels(true);
        slider4.setMajorTickSpacing(x);
        slider4.setMinorTickSpacing(y);
        slider4.setSnapToTicks(true);
        slider4.setPaintTicks(true);
        panelSlider4.add(slider4);

        //Slider 5
        //Make the panel
        JPanel panelSlider5 = new JPanel();
        panelSlider5.setLayout(new BoxLayout(panelSlider5, BoxLayout.X_AXIS));
        panelSliders.add(panelSlider5);
        //Make the Label
        JLabel lblNum5 = new JLabel("5");
        panelSlider5.add(lblNum5);
        //Make the Slider
        JSlider slider5 = new JSlider();
        slider5.setPaintLabels(true);
        slider5.setMajorTickSpacing(x);
        slider5.setMinorTickSpacing(y);
        slider5.setSnapToTicks(true);
        slider5.setPaintTicks(true);
        panelSlider5.add(slider5);

        //Slider 6
        //Make the panel
        JPanel panelSlider6 = new JPanel();
        panelSlider6.setLayout(new BoxLayout(panelSlider6, BoxLayout.X_AXIS));
        panelSliders.add(panelSlider6);
        //Make the Label
        JLabel lblNum6 = new JLabel("6");
        panelSlider6.add(lblNum6);
        //Make the Slider
        JSlider slider6 = new JSlider();
        slider6.setPaintLabels(true);
        slider6.setMajorTickSpacing(x);
        slider6.setMinorTickSpacing(y);
        slider6.setSnapToTicks(true);
        slider6.setPaintTicks(true);
        panelSlider6.add(slider6);
    }
}
