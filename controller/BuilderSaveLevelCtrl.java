package controller;

import boundary.BuilderApplication;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.BuilderModel;
import utils.StreamFileUtils;

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
      String path = dialog.getDirectory() + filename;
      StreamFileUtils.writeStream(path, model.level);
    }
  }
}
