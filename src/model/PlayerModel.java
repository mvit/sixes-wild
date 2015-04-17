package model;

/**
 * Top-level player container.
 */
public class PlayerModel {
  public Level level = null;
  public Move move = null;

  public VariationCtrl variation = null;
  public PlayerState playerState = PlayerState.NONE;

  /**
   * Create a new player model.
   *
   * @constructor
   */
  public PlayerModel() {}
}
