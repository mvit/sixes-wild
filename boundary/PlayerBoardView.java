package boundary;

import controller.PlayerBoardMouseCtrl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import model.Board;
import model.Cell;
import model.PlayerModel;
import model.PlayerState;
import model.Point;
import model.Rules;
import utils.ScaleImage;

/**
 * Actual view for the playable grid in-game.
 *
 * @author Eli Skeggs, Nick Chaput, and Maurizio Vitale
 */
public class PlayerBoardView extends BoardView {
  PlayerApplication app;
  PlayerModel model;

  BufferedImage[][] selectedCache = new BufferedImage[3][3];

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

    setCacheSource(app.loader);

    Color[] fallbackColors = new Color[] {Color.GREEN, Color.MAGENTA,
      Color.YELLOW, Color.RED, Color.BLUE, Color.GRAY};
    for (int i = 0; i < fallbackColors.length; i++) {
      addCache((i + 1) + ".png", fallbackColors[i]);
    }
    addCache("bucket.png", null);
    addCache("inert.png", null);
    
    addCache("marked.png", null);
    addCache("x2.png", null);
    addCache("x3.png", null);
  }

  /**
   * Cache the number images.
   *
   * @param width The new cell width to cache.
   * @param height The new cell height to cache.
   */
  @Override
  protected void cellSizeChange(int width, int height) {
    super.cellSizeChange(width, height);

    BufferedImage selectedSource = app.loader.getResource("selected.png");
    if (selectedSource == null) {
      // crap
    } else {
      selectedSource = ScaleImage.scaleImage(selectedSource, width, height);

      int lw = width / 3, th = height / 3, rw = width - lw, bh = height - th;
      selectedCache[0][0] = new BufferedImage(width, height, selectedSource.getType()); selectedCache[0][0].setData(selectedSource.getData(new Rectangle(0, 0, lw, th)));
      // selectedCache[0][1] = new BufferedImage(width, height, selectedSource.getType()); selectedCache[0][1].setData(selectedSource.getData(new Rectangle(0, th, lw, bh - th)));
      selectedCache[0][2] = new BufferedImage(width, height, selectedSource.getType()); selectedCache[0][2].setData(selectedSource.getData(new Rectangle(0, bh, lw, height - bh)));
      // selectedCache[1][0] = new BufferedImage(width, height, selectedSource.getType()); selectedCache[1][0].setData(selectedSource.getData(new Rectangle(lw, 0, rw - lw, th)));
      // selectedCache[1][1] = new BufferedImage(width, height, selectedSource.getType()); selectedCache[1][1].setData(selectedSource.getData(new Rectangle(lw, th, rw - lw, bh - th)));
      // selectedCache[1][2] = new BufferedImage(width, height, selectedSource.getType()); selectedCache[1][2].setData(selectedSource.getData(new Rectangle(lw, bh, rw - lw, height - bh)));
      selectedCache[2][0] = new BufferedImage(width, height, selectedSource.getType()); selectedCache[2][0].setData(selectedSource.getData(new Rectangle(rw, 0, width - rw, th)));
      // selectedCache[2][1] = new BufferedImage(width, height, selectedSource.getType()); selectedCache[2][1].setData(selectedSource.getData(new Rectangle(rw, th, width - rw, bh - th)));
      selectedCache[2][2] = new BufferedImage(width, height, selectedSource.getType()); selectedCache[2][2].setData(selectedSource.getData(new Rectangle(rw, bh, width - rw, height - bh)));

      selectedCache[1][1] = new BufferedImage(width, height, selectedSource.getType());
      selectedCache[1][1].setData(selectedSource.getData(new Rectangle(0, th, lw, bh - th)));
      selectedCache[1][1].setData(selectedSource.getData(new Rectangle(lw, 0, rw - lw, th)));
      selectedCache[1][1].setData(selectedSource.getData(new Rectangle(lw, th, rw - lw, bh - th)));
      selectedCache[1][1].setData(selectedSource.getData(new Rectangle(lw, bh, rw - lw, height - bh)));
      selectedCache[1][1].setData(selectedSource.getData(new Rectangle(rw, th, width - rw, bh - th)));

      selectedCache[0][1] = selectedSource.getSubimage(0, th, lw, bh - th);
      selectedCache[1][0] = selectedSource.getSubimage(lw, 0, rw - lw, th);
      selectedCache[1][2] = selectedSource.getSubimage(lw, bh, rw - lw, th);
      selectedCache[2][1] = selectedSource.getSubimage(rw, th, lw, bh - th);
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

    g.drawImage(getCache((cell.tile.number + 1) + ".png"), x1, y1, null);

    int multiplier = cell.tile.multiplier;
    if (multiplier > 1) {
      g.drawImage(getCache("x" + multiplier), x1, y1, null);
    }

    if (cell.marked) {
      g.drawImage(getCache("marked.png"), x1, y1, null);
    }
    
    if (cell.type != null) {
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

    Point tmp = new Point(x, y);
    if (model.move != null && model.move.points.contains(tmp) && model.playerState != PlayerState.NONE) {
      int width = x2 - x1, height = y2 - y1;
      int lw = width / 3, th = height / 3, rw = width - lw, bh = height - th;

      g.drawImage(selectedCache[1][1], x1, y1, null);
      boolean hasLeft, hasRight, hasAbove, hasBelow;
      tmp.x = x - 1;
      hasLeft = model.move.points.contains(tmp);
      tmp.x = x + 1;
      hasRight = model.move.points.contains(tmp);
      tmp.x = x;
      tmp.y = y - 1;
      hasAbove = model.move.points.contains(tmp);
      tmp.y = y + 1;
      hasBelow = model.move.points.contains(tmp);

      if (!hasLeft && !hasAbove) {
        g.drawImage(selectedCache[0][0], x1, y1, null);
      } else if (hasLeft && !hasAbove) {
        g.drawImage(selectedCache[1][0], x1, y1, null);
      } else if (hasAbove && !hasLeft) {
        g.drawImage(selectedCache[0][1], x1, y1, null);
      } else {
        g.drawImage(selectedCache[1][0], x1, y1, null);
      }

      if (!hasRight && !hasAbove) {
        g.drawImage(selectedCache[2][0], x1, y1, null);
      } else if (hasRight && !hasAbove) {
        g.drawImage(selectedCache[1][0], x1 + rw, y1, null);
      } else if (hasAbove && !hasRight) {
        g.drawImage(selectedCache[2][1], x1 + rw, y1, null);
      } else {
        g.drawImage(selectedCache[2][1], x1 + rw, y1, null);
      }

      if (!hasLeft && !hasBelow) {
        g.drawImage(selectedCache[0][2], x1, y1, null);
      } else if (hasLeft && !hasBelow) {
        g.drawImage(selectedCache[1][2], x1, y1 + bh, null);
      } else if (hasBelow && !hasLeft) {
        g.drawImage(selectedCache[0][1], x1, y1 + bh, null);
      } else {
        g.drawImage(selectedCache[1][2], x1, y1 + bh, null);
      }

      if (!hasRight && !hasBelow) {
        g.drawImage(selectedCache[2][2], x1, y1, null);
      } else if (hasRight && !hasBelow) {
        g.drawImage(selectedCache[1][2], x1 + rw, y1 + bh, null);
      } else if (hasBelow && !hasRight) {
        g.drawImage(selectedCache[2][1], x1 + rw, y1 + bh, null);
      } else {
        g.drawImage(selectedCache[0][1], x1 + rw, y1 + bh, null);
      }
    }
  }
}
