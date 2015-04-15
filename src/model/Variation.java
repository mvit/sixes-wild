import java.util.HashMap;

/**
 * The variation enumeration.
 */
public enum Variation {
  ELIMINATION('E'),
  LIGHTNING('L'),
  PUZZLE('P'),
  RELEASE('R');

  private static final HashMap<Character, Variation> map =
    new HashMap<Character, Variation>();

  public final char code;

  private Variation(char code) {
    this.code = code;
    map.put(code, this);
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
}
