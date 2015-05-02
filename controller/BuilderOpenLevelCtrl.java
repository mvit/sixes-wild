package controller;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import model.BuilderModel;
import model.Level;

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
      try {
        model.level = new Level(new DataInputStream(new FileInputStream(dialog
          .getDirectory() + filename)));
      } catch (IOException err) {
        // TODO: dialog?
        System.err.println(err.getMessage());
        err.printStackTrace();
        return;
      }

      model.clearHistory();
      model.takeSnapshot();

      app.setView(new BuilderLevelEditorView(app, model));
    }
    app.getView().repaint();
  }
}
