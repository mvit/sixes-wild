package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * The Board class.
 */
public class Board {
  public static final int width = 9, height = 9;
  Cell[][] grid = new Cell[width][height];
  Rules rules;

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
   * @constructor
   * @param {Board} src
   */
  public Board(Board src) {
    // TODO: rules need to be copied for the snapshot usage in the Builder
    this.rules = src.rules;

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        grid[x][y] = new Cell(src.grid[x][y]);
      }
    }
  }

  /**
   * Creates a Board from a DataInputStream.
   *
   * @constructor
   * @param {DataInputStream} in
   */
  public Board(Rules rules, DataInputStream in) {
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
   * @param {DataOutputStream} out
   */
  public void write(DataOutputStream out) {
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
}
