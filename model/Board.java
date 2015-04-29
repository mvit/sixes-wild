package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The Board class.
 *
 * @author Eli Skeggs, Nick Chaput
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
  public Tile getTileAbove(Point p) {
	  if (p.x == 0) {
		  return new Tile(rules);
	  }
	  for (int x = p.x-1; x>=0; x--) {
		  if (grid[p.y][x].tile != null) {
			  return grid[p.y][x].tile;
		  }
	  }
	  return new Tile(rules);
  }
  
  /**
   *
   * @param p
   * @return the closest non-empty tile above the given point and sets that tile to empty
   */
  public Tile takeTileAbove(Point p) {
	  if (p.x == 0) {
		  return new Tile(rules);
	  }
	  for (int x = p.x-1; x>=0; x--) {
		  if (grid[p.y][x].tile != null) {
			  Tile newTile = grid[p.y][x].tile;
			  grid[p.y][x].tile = null;
			  return newTile;
		  }
	  }
	  return new Tile(rules);
  }

  /**
   * Updates the board to make all empty tiles fill from tiles above
   */
  public void processBoard() {
	  for (int y = Board.height - 1; y >= 0; y--) {
		  for (int x = Board.width - 1; x >= 0; x--) {
			  if (grid[y][x].type != CellType.INERT 
					  && grid[y][x].type != CellType.BUCKET
					  && grid[y][x].tile == null) {
				  grid[y][x].tile = takeTileAbove(new Point(x,y));
			  }
			  if (grid[y][x].type == CellType.BUCKET
					  && getTileAbove(new Point(x,y)).number == 5
					  && grid[y][x].tile == null) {
				  grid[y][x].tile = takeTileAbove(new Point(x,y));
			  }
		  }
	  }
  }
}
