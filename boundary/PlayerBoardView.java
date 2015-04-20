package boundary;

import controller.PlayerBoardMouseCtrl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import model.Board;
import model.PlayerModel;
import model.Point;

/**
 * Actual view for the playable grid in-game.
 *
 * @author Eli Skeggs
 */
public class PlayerBoardView extends JPanel {
  private static final Color[] colors = new Color[] {
    Color.GREEN,
    Color.YELLOW,
    Color.ORANGE,
    Color.RED,
    Color.BLUE,
    Color.GRAY
  };

  private static final Font font = new Font("SansSerif", Font.BOLD, 18);

  PlayerApplication app;
  PlayerModel model;

  public PlayerBoardView(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;

    addMouseListener(new PlayerBoardMouseCtrl(app, model));
  }

  public Point identifyPoint(int mouseX, int mouseY) {
    Dimension size = getSize();
    int width = Math.min(size.width, size.height);
    int xOffset = (size.width - width) / 2;
    int yOffset = (size.height - width) / 2;

    return new Point((mouseX - xOffset) * Board.width / width, (mouseY - yOffset) * Board.height / width);
  }

  protected void drawStringCentered(Graphics g, String string, int x, int y) {
    FontMetrics fontMetrics = g.getFontMetrics();
    x -= fontMetrics.stringWidth(string) / 2;
    // y = fontMetrics.getAscent() + (y - fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    g.drawString(string, x, y);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setFont(font);

    Board board = model.level.currentBoard;
    Dimension size = getSize();

    int width, height;
    if (size.width * Board.height < size.height * Board.width) {
      width = size.width - 1;
      height = size.width * Board.height / Board.width - 1;
    } else {
      width = size.height * Board.width / Board.height - 1;
      height = size.height - 1;
    }

    int xOffset = (size.width - width + 1) / 2 - 1;
    int yOffset = (size.height - height + 1) / 2 - 1;

    for (int x = 0; x < Board.width; x++) {
      for (int y = 0; y < Board.height; y++) {
        int xPos = xOffset + width * x / Board.width;
        int yPos = yOffset + height * y / Board.height;
        int number = board.grid[x][y].tile.number;

        if (number < colors.length || number >= colors.length) {
          g.setColor(colors[number]);
        } else {
          System.err.println("[WARN] Bad number for color lookup: " + number);
          g.setColor(Color.WHITE);
        }

        g.fillRect(xPos, yPos, xOffset + width * (x + 1) / Board.width - xPos,
          yOffset + height * (y + 1) / Board.height - yPos);
        g.setColor(Color.BLACK);
        drawStringCentered(g, "" + (number + 1), (int) (xOffset + width * (y
          + 0.5d) / Board.width), (int) (yOffset + height * (y + 1.5d)
          / Board.height));
        g.drawLine(xOffset, yPos, xOffset + width, yPos);
        g.drawLine(xPos, yOffset, xPos, yOffset + height);
      }
    }

    g.drawLine(xOffset, yOffset + height, xOffset + width, yOffset + height);
    g.drawLine(xOffset + width, yOffset, xOffset + width, yOffset + height);
  }
}
