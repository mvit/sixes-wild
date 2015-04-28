package controller;

import boundary.BuilderApplication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FileDialog;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import model.BuilderModel;

/**
 * @author Cem Unsal, and Eli Skeggs
 */
public class BuilderSaveLevelCtrl implements ActionListener {
  BuilderApplication app;
  BuilderModel model;

  /**
   * Create the controller with the context: the application and the model.
   */
  public BuilderSaveLevelCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    FileDialog dialog = new FileDialog(app, "Save Level", FileDialog.SAVE);
    dialog.setDirectory(BuilderOpenLevelCtrl.levelspath);
    dialog.setVisible(true);
    String filename = dialog.getFile();
    if (filename != null) {
      try {
        model.level.write(new DataOutputStream(new FileOutputStream(dialog
          .getDirectory() + filename)));
      } catch (IOException err) {
        // TODO: dialog?
        System.err.println(err.getMessage());
        err.printStackTrace();
        return;
      }
    }
  }
}
