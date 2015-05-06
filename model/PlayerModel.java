package model;

import controller.LightningTimer;

/**
 * Top-level player container.
 *
 * @author Eli Skeggs
 */
public class PlayerModel {
  public Level level = null;
  public Move move = new Move();
  public int counter = 0;
  public LightningTimer timer;
  public int score = 0;
  public int levelnum = 0;

  public Variation variation = null;
  public PlayerState playerState = PlayerState.NONE;

  public PlayerProgress progress = null;
  public boolean disableProgress = false;

  /**
   * Create a new player model.
   */
  public PlayerModel() {}

  public void discardLevel() {
    // discard the board
    if (level != null) {
      if(level.rules.variation == Variation.LIGHTNING)
    	  timer.task.cancel();
      level.discardBoard();
    }
  }

  public void realizeLevel() {
    level.realizeBoard();
    counter = level.rules.initialCounter;
    score = 0;
    variation = level.rules.variation;
  }

  // TODO: be specific, what are we resetting?
  public void reset() {
    playerState = PlayerState.NONE;
    move = new Move();
  }
}
