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

    setCacheSource(app.loader);

    Color[] fallbackColors = new Color[] {Color.GREEN, Color.MAGENTA,
      Color.YELLOW, Color.RED, Color.BLUE, Color.GRAY};
    for (int i = 0; i < fallbackColors.length; i++) {
      addCache((i + 1) + ".png", fallbackColors[i]);
    }

    addCache("playable.png", Color.WHITE);
    addCache("bucket.png", Color.GRAY);
    addCache("inert.png", Color.BLACK);
    addCache("x2.png", null);
    addCache("x3.png", null);
  }

  @Override
  protected void paintCell(Graphics g, int x, int y,
      int x1, int y1, int x2, int y2) {
    Cell cell = model.level.initialBoard.grid[x][y];

    switch (cell.type) {
    case PLAYABLE:
      g.drawImage(getCache(cell.tile == null ? "playable.png" :
        (cell.tile.number + 1) + ".png"), x1, y1, null);

      if (cell.tile != null && cell.tile.multiplier > 1) {
        g.drawImage(getCache("x" + cell.tile.multiplier + ".png"), x1, y1, null);
      }
      break;

    case BUCKET:
      g.drawImage(getCache("bucket.png"), x1, y1, null);
      break;

    // TODO: should probably warn in case of default
    case INERT:
    default:
      g.drawImage(getCache("inert.png"), x1, y1, null);
    }
  }
}
