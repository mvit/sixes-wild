package model;

public enum PlayerState {
  NONE,
  REMOVE,
  // if we want an animation or something, this could be the current move to
  // disable user interaction
  SCRAMBLE,
  SELECT,
  SWAP
}
