package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * The progress the user has made in unlocking levels and the achieved scores.
 *
 * Levels are zero-indexed.
 *
 * @author Eli Skeggs
 */
public class PlayerProgress implements Iterable<LevelProgress> {
  // TODO: where should these be stored?
  public static String header = "SWUP";
  public static int version = 0;

  ArrayList<LevelProgress> levels = new ArrayList<LevelProgress>();

  /**
   * Creates an empty PlayerProgress.
   */
  public PlayerProgress() {}

  /**
   * Creates a PlayerProgress from a DataInputStream.
   *
   * TODO: create log-style progress? Implicitly adds recent event capabilities
   * if desired.
   *
   * @param in
   */
  public PlayerProgress(DataInputStream in) throws IOException {
    // if we don't have an input stream, do nothing!
    if (in == null) {
      return;
    }

    byte headerBytes[] = new byte[4];
    if (in.read(headerBytes, 0, 4) != 4) {
      throw new RuntimeException("stored user progress incomplete");
    }
    if (!header.equals(new String(headerBytes, StandardCharsets.US_ASCII))) {
      throw new RuntimeException(
        "provided data stream is not stored user progress");
    }

    int completedLevels = in.readInt();
    if (completedLevels < 0) {
      throw new RuntimeException(
        "stored user progress contains invalid completed level count");
    }

    if (completedLevels > 0) {
      levels.ensureCapacity(completedLevels);
      for (int i = 0; i < completedLevels; i++) {
        levels.add(new LevelProgress(i, in));
      }
    }
  }

  /**
   * Write the PlayerProgress to a DataOutputStream.
   *
   * @param out
   */
  public void write(DataOutputStream out) throws IOException {
    out.write(header.getBytes(StandardCharsets.US_ASCII));
    out.writeInt(version);
    out.writeInt(levels.size());
    for (LevelProgress progress : levels) {
      progress.write(out);
    }
  }

  /**
   * Get the number of completed levels.
   *
   * @return The number of completed levels.
   */
  public int completedLevels() {
    return levels.size();
  }

  /**
   * Get the number of unlocked levels.
   *
   * @return The number of unlocked levels.
   */
  public int unlockedLevels() {
    return levels.size() + 1;
  }

  /**
   * Get the LevelProgress instance corresponding to the given level number.
   *
   * @param level The level to get progress for.
   * @return The LevelProgress instance.
   */
  public LevelProgress getLevelProgress(int level) {
    return level < levels.size() ? levels.get(level) : null;
  }

  /**
   * Set the achieved score for the given level and score at the current time.
   *
   * TODO: patch specific file bytes?
   *
   * No-op if the specified level has not been unlocked.
   *
   * @param level
   * @param score
   */
  public void setAchievedScore(int level, int score) {
    int completedLevels = levels.size();
    if (level == completedLevels + 1) {
      levels.add(new LevelProgress(level, score));
    } else if (level <= completedLevels) {
      LevelProgress progress = levels.get(level);
      if (progress.bestScore < score) {
        progress.bestScore = score;
      }
      progress.lastPlayed = new Date();
    } else {
      // HAHA NOPE
    }
  }

  /**
   * Gets the iterator for the PlayerProgress.
   *
   * @return An iterator for iterating over the LevelProgress objects in the
   *   in the user's progress.
   */
  public Iterator<LevelProgress> iterator() {
    return new ProgressIterator(levels.iterator());
  }

  /**
   * Encapsulates an iterator which forbids the removal of elements.
   */
  protected static class ProgressIterator implements Iterator<LevelProgress> {
    protected Iterator<LevelProgress> iter;

    protected ProgressIterator(Iterator<LevelProgress> iter) {
      this.iter = iter;
    }

    public boolean hasNext() {
      return iter.hasNext();
    }

    public LevelProgress next() {
      return iter.next();
    }

    public void remove() throws UnsupportedOperationException {
      throw new UnsupportedOperationException();
    }
  }
}
