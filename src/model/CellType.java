import java.util.HashMap;

/**
 * The CellType for the Cell class.
 */
public enum CellType {
  PLAYABLE(0),
  INERT(1),
  BUCKET(2);

  private static final HashMap<Character, CellType> map =
    new HashMap<Character, CellType>();

  public final byte code;

  private CellType(byte code) {
    this.code = code;
    map.put(code, this);
  }

  /**
   * Get the cell type corresponding to the provided code.
   *
   * @param {byte} code
   * @return {CellType}
   */
  public static CellType getCellType(byte code) {
    return map.get(code);
  }
}
