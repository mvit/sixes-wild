package model;

// TODO: we shouldn't need to import boundary and controller in the model
// package
import boundary.PlayerApplication;
import controller.PlayerEliminationCtrl;
import controller.PlayerLightningCtrl;
import controller.PlayerPuzzleCtrl;
import controller.PlayerReleaseCtrl;
import controller.PlayerVariationCtrl;
import java.util.HashMap;
import java.util.Map;

/**
 * The variation enumeration.
 */
public enum Variation {
  ELIMINATION('E') {
    @Override
    public PlayerVariationCtrl createCtrl(PlayerApplication app, PlayerModel model) {
      return new PlayerEliminationCtrl(app, model);
    }
  },
  LIGHTNING('L') {
    @Override
    public PlayerVariationCtrl createCtrl(PlayerApplication app, PlayerModel model) {
      return new PlayerLightningCtrl(app, model);
    }
  },
  PUZZLE('P') {
    @Override
    public PlayerVariationCtrl createCtrl(PlayerApplication app, PlayerModel model) {
      return new PlayerPuzzleCtrl(app, model);
    }
  },
  RELEASE('R') {
    @Override
    public PlayerVariationCtrl createCtrl(PlayerApplication app, PlayerModel model) {
      return new PlayerReleaseCtrl(app, model);
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

  abstract public PlayerVariationCtrl createCtrl(PlayerApplication app, PlayerModel model);
}
