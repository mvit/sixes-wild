package model;

// TODO: don't import boundary/controller from model package :(
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
 *
 * @author Eli Skeggs
 */
public enum Variation {
  ELIMINATION('E', "Elimination") {
    @Override
    public PlayerVariationCtrl createCtrl(PlayerApplication app, PlayerModel model) {
      return new PlayerEliminationCtrl(app, model);
    }
  },
  LIGHTNING('L', "Lightning") {
    @Override
    public PlayerVariationCtrl createCtrl(PlayerApplication app, PlayerModel model) {
      return new PlayerLightningCtrl(app, model);
    }
  },
  PUZZLE('P', "Puzzle") {
    @Override
    public PlayerVariationCtrl createCtrl(PlayerApplication app, PlayerModel model) {
      return new PlayerPuzzleCtrl(app, model);
    }
  },
  RELEASE('R', "Release") {
    @Override
    public PlayerVariationCtrl createCtrl(PlayerApplication app, PlayerModel model) {
      return new PlayerReleaseCtrl(app, model);
    }
  };

  private static final Map<Character, Variation> map = initializeMapping();

  public final char code;
  public final String name;

  private Variation(char code, String name) {
    this.code = code;
    this.name = name;
  }

  /**
   * Get the variation corresponding to the provided character code.
   *
   * @param code
   * @return The variation corresponding to the given code character.
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
