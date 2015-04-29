package model;

/**
 * Make a new change containing a board snapshot.
 *
 * @author Eli Skeggs
 */
public class Change {
  public Level snapshot;

  public Change(Level level) {
    snapshot = level;
  }
}
