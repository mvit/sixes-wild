package model;

import java.util.Map;
import java.util.HashMap;

/**
 * The variation enumeration.
 */
public enum Variation {
  ELIMINATION('E'),
  LIGHTNING('L'),
  PUZZLE('P'),
  RELEASE('R');

  private static final Map<Character, Variation> map = initializeMapping();

  public final char code;

  private Variation(char code) {
    this.code = code;
  }

  /**
   * Get the variation corresponding to the provided character code.
   *
   * @param {char} code
   * @return {Variation}
   */
  public static Variation getVariation(char code) {
    return map.get(code);
  }

  /**
   * Initialize the mapping based on the enum values.
   */
  private static Map<Character, Variation> initializeMapping() {
    Map<Character, Variation> map = new HashMap<Character, Variation>();
    for (Variation variation : Variation.values()) {
      map.put(variation.code, variation);
    }
    return map;
  }
}
