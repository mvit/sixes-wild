package boundary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import model.Point;

/**
 * An abstract board view to be shared between PlayerLevelView,
 * PlayerLevelSelectView, and BuilderLevelEditorView.
 *
 * TODO: add double-buffering
 *
 * @author Eli Skeggs
 */
public abstract class BoardView extends JPanel {
  protected boolean paintBorders;
  protected int boardWidth, boardHeight;

  protected int prevWidth, prevHeight;

  /**
   * Create a board view with the given grid dimensions. The paintBorders flag
   * specifies whether to paint borders between cells.
   *
   * @param paintBorders
   * @param width
   * @param height
   */
  public BoardView(boolean paintBorders, int width, int height) {
    this.paintBorders = paintBorders;
    boardWidth = width;
    boardHeight = height;
  }

  /**
   * Update the board grid size to the given width and height.
   *
   * @param width
   * @param height
   */
  protected void setBoardSize(int width, int height) {
    boardWidth = width;
    boardHeight = height;
  }

  /**
   * Provides a cache hook to resize any necessary images.
   *
   * Defaults to doing nothing.
   *
   * @param width The new cell width to cache.
   * @param height The new cell height to cache.
   */
  protected void cellSizeChange(int width, int height) {}

  /**
   * Abstract paint cell method, paints the cell with the implementation-
   * specific painting code.
   *
   * x and y are the logical cell coordinates, x1, y1, x2, and y2 are the screen
   * coordinates of the rectangle to draw in
   *
   * @param g The graphics object to use to draw.
   * @param x The grid coordinate to paint, used to locate the Cell in question.
   * @param y The grid coordinate to paint, used to locate the Cell in question.
   * @param x1 The left side of the cell to draw, translate to the screen.
   * @param y1 The top side of the cell to draw, translate to the screen.
   * @param x2 The right side of the cell to draw, translate to the screen.
   * @param y2 The bottom side of the cell to draw, translate to the screen.
   */
  protected abstract void paintCell(Graphics g, int x, int y,
    int x1, int y1, int x2, int y2);

  /**
   * Identify the given mouse coordinates as a grid Point.
   *
   * @param mouseX
   * @param mouseY
   * @return The identified point.
   */
  public Point identifyPoint(int mouseX, int mouseY) {
    Dimension size = getSize();

    int cellSize = Math.min(size.width / boardWidth, size.height / boardHeight);
    int xOffset = (size.width - boardWidth * cellSize) / 2;
    int yOffset = (size.height - boardHeight * cellSize) / 2;

    if (mouseX < xOffset || mouseX > xOffset + cellSize * boardWidth ||
        mouseY < yOffset || mouseY > yOffset + cellSize * boardHeight) {
      return null;
    }

    // the Math.min handles the bottom and right edges of the board
    return new Point(Math.min((mouseX - xOffset) / cellSize, boardWidth - 1),
      Math.min((mouseY - yOffset) / cellSize, boardHeight - 1));
  }

  /**
   * Get the bounding box for the given x and y (which represent grid
   * coordinates as opposed to screen coordinates).
   *
   * @return The bounding box for the identified cell.
   */
  public Box identifyCell(int x, int y) {
    Dimension size = getSize();

    int cellSize = Math.min(size.width / boardWidth, size.height / boardHeight);
    int xOffset = (size.width - boardWidth * cellSize) / 2;
    int yOffset = (size.height - boardHeight * cellSize) / 2;

    int left = xOffset + x * cellSize, top = yOffset + y * cellSize;

    return new Box(left, top, left + cellSize, top + cellSize);
  }

  /**
   * Get the bounding box for the given Point.
   *
   * @return The bounding box for the identified cell.
   */
  public Box identifyCell(Point point) {
    return identifyCell(point.x, point.y);
  }

  /**
   * Actually paint the board. Delegates to paintCell to actually paint stuff,
   * unless paintBorders specifies that borders should be painted.
   *
   * @param g
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // cache resized images
    Box preBox = identifyCell(0, 0);
    int width = preBox.x2 - preBox.x1, height = preBox.y2 - preBox.y1;
    if (width != prevWidth || height != prevHeight) {
      if (width <= 0 || height <= 0) {
        // don't paint or cache anything if we don't actually have enough room
        return;
      }

      cellSizeChange(width, height);

      prevWidth = width;
      prevHeight = height;
    }

    for (int x = 0; x < boardWidth; x++) {
      for (int y = 0; y < boardHeight; y++) {
        Box box = identifyCell(x, y);
        paintCell(g, x, y, box.x1, box.y1, box.x2, box.y2);
      }
    }

    if (paintBorders) {
      int bottom = identifyCell(0, boardHeight).y1;
      for (int x = 0; x <= boardWidth; x++) {
        Box box = identifyCell(x, 0);
        g.drawLine(box.x1, box.y1, box.x1, bottom);
      }

      int right = identifyCell(0, boardWidth).x1;
      for (int y = 0; y <= boardHeight; y++) {
        Box box = identifyCell(0, y);
        g.drawLine(box.x1, box.y1, right, box.y1);
      }
    }
  }

  /**
   * Create a solid-color image.
   *
   * @return The created image.
   */
  protected BufferedImage solidImage(Color color, int width, int height) {
    BufferedImage image = new BufferedImage(width, height,
      BufferedImage.TYPE_INT_ARGB);
    Graphics graphics = image.createGraphics();
    graphics.setColor(color);
    graphics.fillRect(0, 0, width, height);
    return image;
  }

  /**
   * Represents a bounding-box around a given cell.
   *
   * @author Eli Skeggs
   */
  protected class Box {
    public int x1, y1, x2, y2;

    /**
     * Construct a bounding box around the given coordinates.
     *
     * Assumes <code>x1 &lt; x2 &amp;&amp; y1 &lt; y2</code>.
     */
    protected Box(int x1, int y1, int x2, int y2) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
    }
  }
}
