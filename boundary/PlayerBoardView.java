package boundary;

import controller.PlayerBoardMouseCtrl;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import model.Board;
import model.Cell;
import model.PlayerModel;

/**
 * Actual view for the playable grid in-game.
 *
 * @author Eli Skeggs
 */
public class PlayerBoardView extends BoardView {
  PlayerApplication app;
  PlayerModel model;

  public PlayerBoardView(PlayerApplication app, PlayerModel model) {
    super(false, Board.width, Board.height);

    this.app = app;
    this.model = model;

    addMouseListener(new PlayerBoardMouseCtrl(app, model));
  }

  @Override
  protected void paintCell(Graphics g, int x, int y,
      int x1, int y1, int x2, int y2) {
    Cell cell = model.level.currentBoard.grid[x][y];
    if (cell.tile == null) {
      return;
    }

    int number = cell.tile.number;
    BufferedImage image = app.loader.getResource((number + 1) + ".png");

    if (image == null) {
      System.err.println("[WARN] Bad number for color lookup: " + number);
      g.setColor(Color.WHITE);
      g.fillRect(x1, y1, x2 - x1, y2 - y1);
    } else {
      image = utils.ScaleImage.scaleImage(image, x2 - x1, y2 - y1);
      g.drawImage(image, x1, y1, null);
    }
  }
}
