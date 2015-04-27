package model;

import controller.PlayerVariationCtrl;

/**
 * Top-level player container.
 */
public class PlayerModel {
  public Level level = null;
  public Move move = null;
  public int counter = 0;

  public Variation variation = null;
  public PlayerState playerState = PlayerState.NONE;

  /**
   * Create a new player model.
   *
   * @constructor
   */
  public PlayerModel() {}
  
  public void reset() {
	  playerState = PlayerState.NONE;
	  move = null;
  }
}
