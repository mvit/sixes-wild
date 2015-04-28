package model;

/**
 * Stores the current state of the player's game. Represents the action they are
 * currently undertaking, if any.
 *
 * @author Eli Skeggs
 */
public enum PlayerState {
  NONE,
  REMOVE,
  // if we want an animation or something, this could be the current move to
  // disable user interaction
  SCRAMBLE,
  SELECT,
  SWAP
}
