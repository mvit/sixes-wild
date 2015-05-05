package boundary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.ScaleImage;
import model.Board;
import model.PlayerModel;
import model.Cell;
import model.Rules;

/**
 * Preview view for the builder board view.
 *
 * @author Eli Skeggs and Maurizio Vitale
 */
public class PlayerPreviewBoardView extends BoardView {
  PlayerApplication app;
  PlayerModel model;

  BufferedImage[] numberCache = new BufferedImage[Rules.maxNumber];
  BufferedImage playableCache = null, numberFallbackCache = null;

  public PlayerPreviewBoardView(PlayerApplication app, PlayerModel model) {
    super(false, Board.width, Board.height);

    this.app = app;
    this.model = model;
  }

  /**
   * Cache the images.
   *
   * @param width The new cell width to cache.
   * @param height The new cell height to cache.
   */
  @Override
  protected void cellSizeChange(int width, int height) {
    numberFallbackCache = solidImage(Color.MAGENTA, width, height);

    for (int i = 0; i < numberCache.length; i++) {
      // scaleImage ensures we aren't resizing unnecessarily
      BufferedImage image = app.loader.getResource((i + 1) + ".png");
      if (image == null) {
        System.err.println("[WARN] Bad number for lookup: " + (i + 1));
        image = numberFallbackCache;
      } else {
        image = ScaleImage.scaleImage(image, width, height);
      }
      numberCache[i] = image;
    }

    BufferedImage playable = app.loader.getResource("playable.png");
    if (playable == null) {
      System.err.println("[WARN] Cannot get playable image");
      playableCache = solidImage(Color.WHITE, width, height);
    } else {
      playableCache = ScaleImage.scaleImage(playable, width, height);
    }
  }

  @Override
  protected void paintCell(Graphics g, int x, int y,
      int x1, int y1, int x2, int y2) {
    Cell cell = model.level.initialBoard.grid[x][y];

    switch (cell.type) {
    case PLAYABLE:
      // TODO: add multiplier
      if (cell.tile == null) {
        g.drawImage(playableCache, x1, y1, null);
      } else {
        int number = cell.tile.number;
        System.err.println("[WARN] Bad number for cache lookup: " + number);
        BufferedImage image = numberCache[number];
        if (image == null) {
          image = numberFallbackCache;
        }
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
