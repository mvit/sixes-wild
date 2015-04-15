import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * The Cell class.
 */
public class Cell {
  CellType type;
  Tile tile; // null if the cell is empty

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
   * @param {CellType} type
   * @param {Tile} tile
   */
  public Cell(CellType type, Tile tile) {
    this.type = type;
    this.tile = tile;
  }

  /**
   * Creates a Tile from a DataInputStream.
   *
   * @constructor
   * @param {DataInputStream} in
   * @param {boolean} hasTile Whether the containing Board stored the Tiles for
   *   each Cell.
   */
  public Cell(DataInputStream in, boolean hasTile) {
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
   * @param {DataOutputStream} out
   * @param {boolean} storeTile Whether to store the Tile, and whether it
   *   exists, along with the Cell.
   */
  public void write(DataOutputStream out, boolean storeTile) {
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
