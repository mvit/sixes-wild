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
  public boolean passedLevel;

  /**
   * Create a new LevelProgress for a given level and a given achieved score,
   * assumes this progress occurred at the current time.
   *
   * @param level
   * @param score
   */
  public LevelProgress(int level, int score, boolean passedLevel) {
    this.level = level;
    lastPlayed = new Date();
    bestScore = score;
    this.passedLevel = passedLevel;
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
    passedLevel = in.readByte() != 0;
  }

  /**
   * Write the LevelProgress to a DataOutputStream.
   *
   * @param out
   */
  public void write(DataOutputStream out) throws IOException {
    out.writeLong(lastPlayed.getTime());
    out.writeInt(bestScore);
    out.writeByte(passedLevel ? 1 : 0);
  }

  public int getScore() {
    return bestScore;
  }
}
