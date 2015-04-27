package boundary;

// import controller.BuilderBoardMouseCtrl;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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

    // addMouseListener(new BuilderBoardMouseCtrl(app, model));
  }

  @Override
  protected void paintCell(Graphics g, int x, int y,
      int x1, int y1, int x2, int y2) {
	System.out.println("OH SHIT");
    Cell cell = model.level.initialBoard.grid[x][y];

    switch (cell.type) {
    case PLAYABLE:
      if (cell.tile == null) {
        g.setColor(Color.WHITE);
        g.fillRect(x1, y1, x2 - x1, y2 - y1);
      } else {
        int number = cell.tile.number;
        BufferedImage image = app.loader.getResource((number + 1) + ".png");
        if (image == null) {
          System.err.println("[WARN] Bad number for color lookup: " + number);
          g.setColor(Color.MAGENTA);
          g.fillRect(x1, y1, x2 - x1, y2 - y1);
        } else {
          image = utils.ScaleImage.scaleImage(image, x2 - x1, y2 - y1);
          g.drawImage(image, x1, y1, null);
        }
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
