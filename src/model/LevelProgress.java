package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * The progress the user has made for a specific level.
 */
public class LevelProgress {
  Date lastPlayed;
  int level, bestScore;

  /**
   * Create a new LevelProgress for a given level and a given acheived score,
   * assumes this progress occurred at the current time.
   *
   * @constructor
   * @param {int} level
   * @param {int} score
   */
  public LevelProgress(int level, int score) {
    this.level = level;
    lastPlayed = new Date();
    bestScore = score;
  }

  /**
   * Creates a LevelProgress from a DataInputStream.
   *
   * @constructor
   * @param {DataInputStream} in
   */
  public LevelProgress(int level, DataInputStream in) throws IOException {
    this.level = level;
    lastPlayed = new Date(in.readLong());
    // TODO: what if <= 0?
    bestScore = in.readInt();
  }

  /**
   * Write the LevelProgress to a DataOutputStream.
   *
   * @param {DataOutputStream} out
   */
  public void write(DataOutputStream out) throws IOException {
    out.writeLong(lastPlayed.getTime());
    out.writeInt(bestScore);
  }
}
