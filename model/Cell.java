package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The Cell class.
 *
 * @author Eli Skeggs
 */
public class Cell {
  public CellType type;
  public Tile tile; // null if the cell is empty
  public boolean marked; // for elimination variation

  /**
   * Create a new generic Cell, which is playable and empty.
   *
   * @constructor
   */
  public Cell() {
    this(CellType.PLAYABLE, null);
  }

  /**
   * Create a new Cell from a CellType and a Tile. Primarily for testing
   * purposes.
   *
   * @constructor
   * @param type
   * @param tile
   */
  public Cell(CellType type, Tile tile) {
    this.type = type;
    this.tile = tile;
    this.marked = false;
  }

  /**
   * Create a copy of the provided Cell.
   *
   * @constructor
   * @param src
   */
  public Cell(Cell src) {
    this.type = src.type;
    if (src.tile == null) {
      this.tile = src.tile;
    } else {
      this.tile = new Tile(src.tile);
    }
  }

  /**
   * Creates a Tile from a DataInputStream.
   *
   * @constructor
   * @param in
   * @param hasTile Whether the containing Board stored the Tiles for
   *   each Cell.
   */
  public Cell(DataInputStream in, boolean hasTile) throws IOException {
    type = CellType.getCellType(in.readByte());
    if (type == null) {
      throw new RuntimeException("stored cell has unknown cell type");
    }
    if (!hasTile || in.readByte() == 0) {
      tile = null;
    } else {
      tile = new Tile(in);
    }
  }

  /**
   * Write the Cell to a DataOutputStream.
   *
   * TODO: have a separate class that handles exactly how to store the Tile,
   * which varies based on the Level's variation?
   *
   * @param out
   * @param storeTile Whether to store the Tile, and whether it
   *   exists, along with the Cell.
   */
  public void write(DataOutputStream out, boolean storeTile) throws IOException {
    out.writeByte(type.code);
    if (storeTile) {
      if (tile == null) {
        out.writeByte(0);
      } else {
        out.writeByte(1);
        tile.write(out);
      }
    }
  }
}
