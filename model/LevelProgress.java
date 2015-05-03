package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * The progress the user has made for a specific level.
 *
 * @author Eli Skeggs
 */
public class LevelProgress {
  Date lastPlayed;
  int level;
  public int bestScore;

  /**
   * Create a new LevelProgress for a given level and a given achieved score,
   * assumes this progress occurred at the current time.
   *
   * @param level
   * @param score
   */
  public LevelProgress(int level, int score) {
    this.level = level;
    lastPlayed = new Date();
    bestScore = score;
  }

  /**
   * Creates a LevelProgress from a DataInputStream.
   *
   * @param in
   */
  public LevelProgress(int level, DataInputStream in) throws IOException {
    this.level = level;
    lastPlayed = new Date(in.readLong());
    bestScore = Math.max(0, in.readInt());
  }

  /**
   * Write the LevelProgress to a DataOutputStream.
   *
   * @param out
   */
  public void write(DataOutputStream out) throws IOException {
    out.writeLong(lastPlayed.getTime());
    out.writeInt(bestScore);
  }

  public int getScore() {
    return bestScore;
  }
}
