package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The Board class.
 *
 * @author Eli Skeggs, Nick Chaput and Bailey Sheridan
 */
public class Board {
  public static final int width = 9, height = 9;
  public Cell[][] grid = new Cell[width][height];
  public Rules rules;

  /**
   * Create a new blank Board with default rules
   *
   * Defaults to all Tiles being playable and defaulting to the probability
   * distribution in the Rules.
   */
  public Board() {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        grid[x][y] = new Cell();
      }
    }

    rules = new Rules();
  }

  /**
   * Create a new blank Board given a set of rules.
   *
   * Defaults to all Tiles being playable and defaulting to the probability
   * distribution in the Rules.
   */
  public Board(Rules rules) {
    this.rules = rules;

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        grid[x][y] = new Cell();
      }
    }
  }

  /**
   * Create a deep copy of the provided Board.
   *
   * @param src
   */
  public Board(Board src) {
    this.rules = null;

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        grid[x][y] = new Cell(src.grid[x][y]);
      }
    }
  }

  /**
   * Creates a Board from a DataInputStream.
   *
   * @param in
   */
  public Board(Rules rules, DataInputStream in) throws IOException {
    this.rules = rules;

    if (in.readInt() != width || in.readInt() != height) {
      throw new RuntimeException("stored board has incompatible dimensions");
    }

    boolean hasTiles = in.readByte() != 0;

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        grid[x][y] = new Cell(in, hasTiles);
      }
    }
  }

  /**
   * Write the Board to a DataOutputStream.
   *
   * @param out
   */
  public void write(DataOutputStream out) throws IOException  {
    out.writeInt(width);
    out.writeInt(height);
    // TODO: set based on whether we store the tiles based on the variation
    out.writeByte(1);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        grid[x][y].write(out, true);
      }
    }
  }

  /**
   * Realizes the content of the board, specifically for use when initally
   * loading the Level in the PlayerApplication, such that all playable Cells
   * have Tiles.
   */
  public void realizeTiles() {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Cell cell = grid[x][y];
        if (cell.type == CellType.PLAYABLE && cell.tile == null) {
          cell.tile = new Tile(rules);
        }
      }
    }
  }

  /**
   *
   * @param p
   * @return the closest non-empty tile above the given point
   */
  public Tile getTileAbove(int x, int y) {
    if (y != 0) {
      for (int row = y - 1; row >= 0; row--) {
        if (grid[x][row].tile != null) {
          return grid[x][row].tile;
        }
      }
    }
    return new Tile(rules);
  }

  /**
   *
   * @param p
   * @return the closest non-empty tile above the given point and sets that tile to empty
   */
  public Tile takeTileAbove(int x, int y) {
    if (y != 0) {
      for (int row = y - 1; row >= 0; row--) {
        if (grid[x][row].tile != null) {
          Tile newTile = grid[x][row].tile;
          grid[x][row].tile = null;
          return newTile;
        }
      }
    }
    return new Tile(rules);
  }

  /**
   * Updates the board to make all empty tiles fill from tiles above
   */
  public void processBoard() {
    for (int x = Board.width - 1; x >= 0; x--) {
      for (int y = Board.height - 1; y >= 0; y--) {
        Cell cell = grid[x][y];
        if (cell.type != CellType.INERT && cell.type != CellType.BUCKET
            && cell.tile == null) {
          cell.tile = takeTileAbove(x, y);
        }
        if (cell.type == CellType.BUCKET && getTileAbove(x, y).number == 5
            && cell.tile == null) {
          cell.tile = takeTileAbove(x, y);
        }
      }
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && (obj instanceof Board)) {
      Board bor = (Board) obj;
      if (grid.length != bor.grid.length) {
        return false;
      }
      for (int i = 0; i < grid.length; i++) {
        int count = grid[i].length;
        if (count != bor.grid[i].length) {
          return false;
        }
        for (int j = 0; j < count; j++) {
          if (((grid[i][j] == null) != (bor.grid[i][j] == null)) ||
              !grid[i][j].equals(bor.grid[i][j])) {
            return false;
          }
        }
      }
      return true;
    }
    return false;
  }
}
