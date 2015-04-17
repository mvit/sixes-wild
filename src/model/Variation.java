package model;

import java.util.HashMap;
import java.util.Map;

/**
 * The variation enumeration.
 */
public enum Variation {
  ELIMINATION('E') {
    @Override
    public VariationCtrl createCtrl(PlayerApplication app, PlayerModel model) {
      return new EliminationCtrl(app, model);
    }
  },
  LIGHTNING('L') {
    @Override
    public VariationCtrl createCtrl(PlayerApplication app, PlayerModel model) {
      return new LightningCtrl(app, model);
    }
  },
  PUZZLE('P') {
    @Override
    public VariationCtrl createCtrl(PlayerApplication app, PlayerModel model) {
      return new PuzzleCtrl(app, model);
    }
  },
  RELEASE('R') {
    @Override
    public VariationCtrl createCtrl(PlayerApplication app, PlayerModel model) {
      return new ReleaseCtrl(app, model);
    }
  };

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

  abstract public VariationCtrl createCtrl(PlayerApplication app, PlayerModel model);
}
