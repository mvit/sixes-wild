package boundary;

import java.awt.Color;
import java.awt.Graphics;

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
    Board board = model.level.currentBoard;
    if (board == null) {
      board = model.level.initialBoard;
    }

    Cell cell = board.grid[x][y];
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
