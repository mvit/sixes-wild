package model;

import java.util.Map;
import java.util.HashMap;

/**
 * The CellType for the Cell class.
 */
public enum CellType {
  PLAYABLE((byte) 0),
  INERT((byte) 1),
  BUCKET((byte) 2);

  private static final Map<Byte, CellType> map = initializeMapping();

  public final byte code;

  private CellType(byte code) {
    this.code = code;
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

  /**
   * Initialize the mapping based on the enum values.
   */
  private static Map<Byte, CellType> initializeMapping() {
    Map<Byte, CellType> map = new HashMap<Byte, CellType>();
    for (CellType type : CellType.values()) {
      map.put(type.code, type);
    }
    return map;
  }
}
