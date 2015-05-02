package model;

/**
 * Top-level player container.
 *
 * @author Eli Skeggs
 */
public class PlayerModel {
  public Level level = null;
  public Move move = new Move();
  public int counter = 0;
  public int score = 0;
  public int levelnum = 0;

  public Variation variation = null;
  public PlayerState playerState = PlayerState.NONE;

  public PlayerProgress progress = null;

  /**
   * Create a new player model.
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
    score = 0;
    variation = level.rules.variation;
  }

  // TODO: be specific, what are we resetting?
  public void reset() {
    playerState = PlayerState.NONE;
    move = null;
  }
}
