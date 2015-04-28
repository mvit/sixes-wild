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

  /**
   * Create a player model from a builder model to enable previews.
   */
  public PlayerModel(BuilderModel model) {
    level = model.level;
  }

  public void discardLevel() {
    // discard the board
    if (level != null) {
      level.discardBoard();
    }
  }

  public void realizeLevel() {
    level.realizeBoard();
    counter = level.rules.initialCounter;
    variation = level.rules.variation;
  }

  // TODO: be specific, what are we resetting?
  public void reset() {
	  playerState = PlayerState.NONE;
	  move = null;
  }
}
