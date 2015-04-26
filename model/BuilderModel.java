package model;

import java.util.ArrayList;

/**
 * Top-level player container.
 */
public class BuilderModel {
  public Level level = null;
  public ArrayList<Change> history = new ArrayList<Change>();
  public int redoIndex = 0;

  // 'p'= playable, 'i'= inert, 6 = six, 's' = slot
  // Do not change visibility, I need it - CEM
  public char tileType = 'p';
  
  /**
   * Create a new builder model.
   *
   * @constructor
   */
  public BuilderModel() {}
}
