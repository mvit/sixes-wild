package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JPanel;
import model.BuilderModel;
import model.Level;
import utils.StreamFileUtils;

/**
 * Open a level from the filesystem.
 *
 * @author Cem Unsal, and Eli Skeggs
 */
public class BuilderOpenLevelCtrl implements ActionListener {
  public static final String levelspath = new File("resource/levels")
    .getAbsolutePath();

  BuilderApplication app;
  BuilderModel model;

  /**
   * Create the controller with the application and the model.
   */
  public BuilderOpenLevelCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    FileDialog dialog = new FileDialog(app, "Open a Level", FileDialog.LOAD);
    dialog.setDirectory(BuilderOpenLevelCtrl.levelspath);
    dialog.setVisible(true);

    String filename = dialog.getFile();
    if (filename != null) {
      String path = dialog.getDirectory() + filename;
      Object result = StreamFileUtils.readStream(path, Level.getReadable());

      // TODO: dialog?
      if (result != null) {
        model.level = (Level) result;

        model.clearHistory();
        model.takeSnapshot();

        JPanel view = new BuilderLevelEditorView(app, model);
        app.setView(view);
        view.repaint();
      }
    }
  }
}
