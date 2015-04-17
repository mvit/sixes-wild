package model;

import controller.PlayerVariationCtrl;

/**
 * Top-level player container.
 */
public class PlayerModel {
  public Level level = null;
  public Move move = null;

  public PlayerVariationCtrl variation = null;
  public PlayerState playerState = PlayerState.NONE;

  /**
   * Create a new player model.
   *
   * @constructor
   */
  public PlayerModel() {}
}
