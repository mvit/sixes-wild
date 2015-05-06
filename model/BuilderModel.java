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

  protected boolean holdHistory = false;

  /**
   * Create a new builder model.
   */
  public BuilderModel() {}

  public void discardLevel() {
    // discard the board
    if (level != null) {
      level.discardBoard();
    }
  }

  public void realizeLevel() {
    level.realizeBoard();
  }

  /**
   * Clear the temporary state.
   */
  public void clearTempState() {
    currentType = null;
    currentUseNumber = false;
    currentNumber = 0;
    currentMultiplier = 1;
  }

  public boolean undo() {
    if (redoIndex > 0) {
      holdHistory = true;
      boolean isPreview = level.currentBoard != null;
      level = history.get(--redoIndex).snapshot;
      if (isPreview) {
        realizeLevel();
      }
      return true;
    }
    return false;
  }

  public void finishHistoryChange() {
    holdHistory = false;
  }

  public boolean redo() {
    if (redoIndex < history.size() - 1) {
      holdHistory = true;
      boolean isPreview = level.currentBoard != null;
      level = history.get(++redoIndex).snapshot;
      if (isPreview) {
        realizeLevel();
      }
      return true;
    }
    return false;
  }

  public void clearHistory() {
    history.clear();
    history.trimToSize();
    redoIndex = 0;
    holdHistory = false;
  }

  /**
   * Adds a snapshot of the current state to the history.
   */
  public void takeSnapshot() {
    if (holdHistory || (history.size() > 0 &&
        level.equals(history.get(redoIndex).snapshot))) {
      return;
    }

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
