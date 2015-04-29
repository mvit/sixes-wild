package boundary;

import java.awt.Dimension;
import java.awt.Graphics;
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

  /**
   * Create a board view with the given grid dimensions. The paintBorders flag
   * specifies whether to paint borders between cells.
   *
   * @constructor
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
   * Abstract paint cell method, paints the cell with the implementation-
   * specific painting code.
   *
   * x and y are the logical cell coordinates, x1, y1, x2, and y2 are the screen
   * coordinates of the rectangle to draw in
   *
   * @param g
   * @param x
   * @param y
   * @param x1
   * @param y1
   * @param x2
   * @param y2
   */
  protected abstract void paintCell(Graphics g, int x, int y,
    int x1, int y1, int x2, int y2);

  /**
   * Identify the given mouse coordinates as a grid Point.
   *
   * @param mouseX
   * @param mouseY
   * @return {Point}
   */
  public Point identifyPoint(int mouseX, int mouseY) {
    Dimension size = getSize();

    int width, height;
    if (size.width * boardHeight < size.height * boardWidth) {
      width = size.width - 1;
      height = size.width * boardHeight / boardWidth - 1;
    } else {
      width = size.height * boardWidth / boardHeight - 1;
      height = size.height - 1;
    }

    int xOffset = (size.width - width + 1) / 2 - 1;
    int yOffset = (size.height - height + 1) / 2 - 1;

    // TODO: return null if out of bounds!!!
    return new Point((mouseX - xOffset) * boardWidth / width,
      (mouseY - yOffset) * boardHeight / height);
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

    Dimension size = getSize();

    int width, height;
    if (size.width * boardHeight < size.height * boardWidth) {
      width = size.width - 1;
      height = size.width * boardHeight / boardWidth - 1;
    } else {
      width = size.height * boardWidth / boardHeight - 1;
      height = size.height - 1;
    }

    double scaledWidth = (double) width / boardWidth,
      scaledHeight = (double) height / boardHeight;

    int xOffset = (size.width - width + 1) / 2 - 1;
    int yOffset = (size.height - height + 1) / 2 - 1;

    int prevX = xOffset, prevY = yOffset;

    for (int x = 0; x < boardWidth; x++) {
      int nextX;

      for (int y = 0; y < boardHeight; y++) {
        int nextY = (int) (yOffset + scaledHeight * (y + 1));
        nextX = (int) (xOffset + scaledWidth * (x + 1));
        paintCell(g, x, y, prevX, prevY, nextX, nextY);
        prevY = nextY;
      }

      prevX = (int) (xOffset + scaledWidth * (x + 1));
      prevY = yOffset;
    }

    if (paintBorders) {
      prevY = yOffset + height;
      for (int x = 0; x <= boardWidth; x++) {
        prevX = (int) (xOffset + scaledWidth * x);
        g.drawLine(prevX, yOffset, prevX, prevY);
      }

      prevX = xOffset + width;
      for (int y = 0; y <= boardHeight; y++) {
        prevY = (int) (yOffset + scaledHeight * y);
        g.drawLine(xOffset, prevY, prevX, prevY);
      }
    }
  }
}
