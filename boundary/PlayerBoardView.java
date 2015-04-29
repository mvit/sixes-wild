package boundary;

import controller.PlayerBoardMouseCtrl;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import model.Board;
import model.Cell;
import model.PlayerModel;
import model.Rules;
import utils.ScaleImage;

/**
 * Actual view for the playable grid in-game.
 *
 * @author Eli Skeggs, and Maurizio Vitale
 */
public class PlayerBoardView extends BoardView {
  PlayerApplication app;
  PlayerModel model;

  BufferedImage[] numberCache = new BufferedImage[Rules.maxNumber];

  /**
   * Create a PlayerBoardView.
   */
  public PlayerBoardView(PlayerApplication app, PlayerModel model) {
    super(false, Board.width, Board.height);

    this.app = app;
    this.model = model;

    PlayerBoardMouseCtrl ctrl = new PlayerBoardMouseCtrl(app, model);
    addMouseListener(ctrl);
    addMouseMotionListener(ctrl);
  }

  /**
   * Cache the number images.
   *
   * @param width The new cell width to cache.
   * @param height The new cell height to cache.
   */
  @Override
  protected void cellSizeChange(int width, int height) {
    for (int i = 0; i < numberCache.length; i++) {
      // scaleImage ensures we aren't resizing unnecessarily
      BufferedImage image = app.loader.getResource((i + 1) + ".png");
      if (image == null) {
        System.err.println("[WARN] Bad number for image lookup: " + (i + 1));
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
      } else {
        image = ScaleImage.scaleImage(image, width, height);
      }
      numberCache[i] = image;
    }
  }

  /**
   * Paint a cell for the player board.
   *
   * @param g The graphics object to use to draw.
   * @param x The grid coordinate to paint, used to locate the Cell in question.
   * @param y The grid coordinate to paint, used to locate the Cell in question.
   * @param x1 The left side of the cell to draw, translate to the screen.
   * @param y1 The top side of the cell to draw, translate to the screen.
   * @param x2 The right side of the cell to draw, translate to the screen.
   * @param y2 The bottom side of the cell to draw, translate to the screen.
   */
  @Override
  protected void paintCell(Graphics g, int x, int y,
      int x1, int y1, int x2, int y2) {
    Cell cell = model.level.currentBoard.grid[x][y];
    if (cell.tile == null) {
      return;
    }

    int number = cell.tile.number;
    int multiplier = cell.tile.multiplier;
    g.drawImage(numberCache[number], x1, y1, null);

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
