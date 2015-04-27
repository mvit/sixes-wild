package model;

import java.util.ArrayList;

/**
 * Top-level player container.
 */
public class BuilderModel {
  public Level level = null;
  public ArrayList<Change> history = new ArrayList<Change>();
  public int redoIndex = 0;

  // the current modifications to be applied when a cell is clicked
  public CellType currentType = null;
  public boolean currentUseNumber = false;
  public int currentNumber = 0, currentMultiplier = 1;

  /**
   * Create a new builder model.
   *
   * @constructor
   */
  public BuilderModel() {}
}
