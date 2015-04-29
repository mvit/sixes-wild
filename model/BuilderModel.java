package model;

import java.util.ArrayList;

/**
 * Top-level player container.
 *
 * @author Cem Unsal, and Eli Skeggs
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

  /**
   * Clear the temporary state.
   */
  public void clearTempState() {
    currentType = null;
    currentUseNumber = false;
    currentNumber = 0;
    currentMultiplier = 1;
  }

  public void clearHistory() {
    history.removeAll();
    history.trimToSize();
  }

  /**
   * Adds a snapshot of the current state to the history.
   */
  public void takeSnapshot() {
    if (redoIndex < history.size() - 1) {
      history.subList(redoIndex + 1, history.size()).clear();
      history.trimToSize();
    }

    if (history.size() > 0) {
      redoIndex++;
    }

    history.add(new Change(new Level(level)));
  }
}
