package model;

/**
 * Stores the current state of the player's game. Represents the action they are
 * currently undertaking, if any.
 *
 * @author Eli Skeggs, Nick Chaput
 */
public enum PlayerState {
  NONE("NONE"),
  REMOVE("REMOVE"),
  // if we want an animation or something, this could be the current move to
  // disable user interaction
  SCRAMBLE("SCRAMBLE"),
  SELECT("SELECT"),
  SWAP("SWAP");

  public final String str;

  private PlayerState(String str) {
    this.str = str;
  }
  
  public String toString() {
    return str;
  }
}
