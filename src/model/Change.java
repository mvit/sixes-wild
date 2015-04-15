package model;

/**
 * Make a new change containing a level snapshot.
 */
public class Change {
  public Level snapshot;

  public Change(Level baseLevel) {
    // make a snapshot
    snapshot = new Level(baseLevel);
  }
}
