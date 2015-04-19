package model;

import java.util.ArrayList;

/**
 * Top-level player container.
 */
public class BuilderModel {
  public Level level = null;
  public ArrayList<Change> history = new ArrayList<Change>();
  public int redoIndex = 0;

  /**
   * Create a new builder model.
   *
   * @constructor
   */
  public BuilderModel() {}
}
