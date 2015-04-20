package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * The Level class.
 */
public class Level {
  // TODO: where should these be stored?
  public static String header = "SWLV";
  public static int version = 0;

  public Rules rules;
  public Board initialBoard;

  // TODO: these really feel like they belong elsewhere...
  public Board currentBoard;
  public int currentScore;

  /**
   * Create a Level with reasonable default settings.
   */
  public Level() {
    rules = new Rules();
    initialBoard = new Board(rules);
    currentBoard = null;
    currentScore = 0;
  }

  /**
   * Creates a Board from a DataInputStream.
   *
   * @constructor
   * @param {DataInputStream} in
   */
  public Level(DataInputStream in) throws IOException {
    byte headerBytes[] = new byte[4];
    if (in.read(headerBytes, 0, 4) != 4) {
      throw new RuntimeException("stored level incomplete");
    }
    if (!header.equals(new String(headerBytes, StandardCharsets.US_ASCII))) {
      throw new RuntimeException("provided data stream is not a stored level");
    }

    // not a stellar system, but good enough given that we'll likely only have
    // one or two versions in use
    int version = in.readInt();
    if (version != this.version) {
      throw new RuntimeException("provided data stream has incompatible version");
    }

    rules = new Rules(in);
    initialBoard = new Board(rules, in);
    currentBoard = null;
    currentScore = 0;
  }

  /**
   * Write the Level to a DataOutputStream.
   *
   * @param {DataOutputStream} out
   */
  public void write(DataOutputStream out) throws IOException {
    out.write(header.getBytes(StandardCharsets.US_ASCII));
    out.writeInt(version);
    rules.write(out);
    initialBoard.write(out);
  }

  /**
   * Realize the Board - fill in the unknown Tiles.
   *
   * Call this when we want to play a game as a player.
   */
  public void realizeBoard() {
    currentBoard = new Board(initialBoard);
    currentBoard.realizeTiles();
    currentScore = 0;
  }

  /**
   * Discards the realization of the Board, if applicable.
   *
   * Call this when we're done playing a game as a player.
   */
  public void discardBoard() {
    currentBoard = null;
    currentScore = 0;
  }
}
