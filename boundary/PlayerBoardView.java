package boundary;

import controller.PlayerBoardMouseCtrl;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import model.Board;
import model.Cell;
import model.PlayerModel;

/**
 * Actual view for the playable grid in-game.
 *
 * @author Eli Skeggs & Maurizio Vitale
 */
public class PlayerBoardView extends BoardView {
  PlayerApplication app;
  PlayerModel model;

  public PlayerBoardView(PlayerApplication app, PlayerModel model) {
    super(false, Board.width, Board.height);

    this.app = app;
    this.model = model;

    PlayerBoardMouseCtrl ctrl = new PlayerBoardMouseCtrl(app, model);
    addMouseListener(ctrl);
    addMouseMotionListener(ctrl);
  }

  @Override
  protected void paintCell(Graphics g, int x, int y,
      int x1, int y1, int x2, int y2) {
    Cell cell = model.level.currentBoard.grid[x][y];
    if (cell.tile == null) {
      return;
    }

    int number = cell.tile.number;
    int multiplier = cell.tile.multiplier;
    BufferedImage image = app.loader.getResource((number + 1) + ".png");

    if (image == null) {
      System.err.println("[WARN] Bad number for image lookup: " + number);
      g.setColor(Color.WHITE);
      g.fillRect(x1, y1, x2 - x1, y2 - y1);
    } else {
      image = utils.ScaleImage.scaleImage(image, x2 - x1, y2 - y1);
      g.drawImage(image, x1, y1, null);
    }

    if (multiplier > 1) {
      BufferedImage multImage = app.loader.getResource("x" + multiplier +
        ".png");
      if (multImage == null) {
        System.err.println("[WARN] Bad multiplier for image lookup: " +
          multiplier);
      } else {
        multImage = utils.ScaleImage.scaleImage(multImage, x2 - x1, y2 - y1);
        g.drawImage(multImage, x1, y1, null);
      }
    }
  }
}
