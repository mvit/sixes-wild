package boundary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import model.Board;

/**
 * Actual view for the playable grid in-game.
 *
 * @author Eli Skeggs
 */
public class PlayerBoardView extends JPanel {
  Board board;
  private static final Map<Integer, Color> colors = new HashMap<Integer, Color>();

  static {
    colors.put(0, Color.GREEN);
    colors.put(1, Color.YELLOW);
    colors.put(2, Color.ORANGE);
    colors.put(3, Color.RED);
    colors.put(4, Color.BLUE);
    colors.put(5, Color.GRAY);
  }

  public PlayerBoardView(Board board) {
    this.board = board;
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.black);
    Dimension size = getSize();
    int width = Math.min(size.width, size.height);
    int xOffset = (size.width - width) / 2;
    int yOffset = (size.height - width) / 2;
    for (int x = 0; x < Board.width; x++) {
      for (int y = 0; y < Board.height; y++) {
        int xPos = xOffset + width * x / 9;
        int yPos = yOffset + width * y / 9;
        int number = board.grid[x][y].tile.number;
        g.setColor(colors.get(number));
        g.fillRect(xPos, yPos, width, width);
        g.setColor(Color.BLACK);
        // g.drawString("" + (number + 1), xPos, yPos + width);
        g.drawLine(xPos, yOffset, xPos, yOffset + width);
        g.drawLine(xOffset, yPos, xOffset + width, yPos);
      }
    }
  }
}
