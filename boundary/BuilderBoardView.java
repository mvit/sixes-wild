package boundary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controller.BuilderBoardMouseCtrl;
import model.Board;
import model.BuilderModel;
import model.Cell;

/**
 * Actual view for the builder board view.
 *
 * @author Eli Skeggs
 */
public class BuilderBoardView extends BoardView {
  BuilderApplication app;
  BuilderModel model;

  public BuilderBoardView(BuilderApplication app, BuilderModel model) {
    super(true, Board.width, Board.height);

    this.app = app;
    this.model = model;

    BuilderBoardMouseCtrl ctrl = new BuilderBoardMouseCtrl(app, model);
    addMouseListener(ctrl);
    addMouseMotionListener(ctrl);
  }

  @Override
  protected void paintCell(Graphics g, int x, int y,
      int x1, int y1, int x2, int y2) {
    Cell cell = model.level.initialBoard.grid[x][y];

    switch (cell.type) {
    case PLAYABLE:
      // TODO: add multiplier
      String filename = null;
      Color fallback = null;
      if (cell.tile == null) {
        filename = "selected.png";
        fallback = Color.WHITE;
      } else {
        filename = (cell.tile.number + 1) + ".png";
        fallback = Color.MAGENTA;
      }
      BufferedImage image = app.loader.getResource(filename);
      if (image == null) {
        System.err.println("[WARN] Bad filename for image lookup: " + filename);
        g.setColor(fallback);
        g.fillRect(x1, y1, x2 - x1, y2 - y1);
      } else {
        image = utils.ScaleImage.scaleImage(image, x2 - x1, y2 - y1);
        g.drawImage(image, x1, y1, null);
      }
      break;

    case BUCKET:
      g.setColor(Color.GRAY);
      g.fillRect(x1, y1, x2 - x1, y2 - y1);
      break;

    // TODO: should probably warn in case of default
    case INERT:
    default:
      g.setColor(Color.BLACK);
      g.fillRect(x1, y1, x2 - x1, y2 - y1);
    }
  }
}
