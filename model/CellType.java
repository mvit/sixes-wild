package model;

import java.util.HashMap;
import java.util.Map;

/**
 * The CellType for the Cell class.
 *
 * @author Eli Skeggs
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
   * @param code
   * @return The CellType value corresponding to the code byte.
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
