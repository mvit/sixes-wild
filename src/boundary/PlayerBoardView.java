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
  private static final Color[] colors = new Colors[] {
    Color.GREEN,
    Color.YELLOW,
    Color.ORANGE,
    Color.RED,
    Color.BLUE,
    Color.GRAY
  };

  PlayerApplication app;
  PlayerModel model;
  Board board;

  public PlayerBoardView(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
    board = model.level.currentBoard;

    addMouseListener(new PlayerBoardMouseCtrl(app, model));
  }

  public Point identifyPoint(int mouseX, int mouseY) {
    Dimension size = getSize();
    int width = Math.min(size.width, size.height);
    int xOffset = (size.width - width) / 2;
    int yOffset = (size.height - width) / 2;

    return new Point((mouseX - xOffset) * 9 / width, (mouseY - yOffset) * 9 / width);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Dimension size = getSize();
    int width = Math.min(size.width, size.height);
    int xOffset = (size.width - width) / 2;
    int yOffset = (size.height - width) / 2;

    for (int x = 0; x < Board.width; x++) {
      for (int y = 0; y < Board.height; y++) {
        int xPos = xOffset + width * x / 9;
        int yPos = yOffset + width * y / 9;
        int number = board.grid[x][y].tile.number;

        if (number < colors.length || number >= colors.length) {
          System.err.println("[WARN] Bad number for color lookup: " + number);
          g.setColor(colors[number]);
        } else {
          g.setColor(Color.WHITE);
        }

        g.fillRect(xPos, yPos, width, width);
        g.setColor(Color.BLACK);
        // g.drawString("" + (number + 1), xPos, yPos + width);
        g.drawLine(xPos, yOffset, xPos, yOffset + width);
        g.drawLine(xOffset, yPos, xOffset + width, yPos);
      }
    }
  }
}
