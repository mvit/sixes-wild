package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import utils.ReadStream;
import utils.WriteStream;


/**
 * The Level class.
 *
 * @author Eli Skeggs
 */
public class Level implements WriteStream {
  // TODO: where should these be stored?
  public static String header = "SWLV";
  public static int version = 1;

  public Rules rules;
  public Board initialBoard;

  // TODO: these really feel like they belong elsewhere...
  public Board currentBoard;
  public int currentScore;

  /**
   * Checks the header of the given InputStream to ensure it is a level.
   *
   * @param in The stream to check.
   * @return Whether the given file could be a valid level.
   */
  protected static boolean checkHeader(InputStream in) throws IOException {
    byte headerBytes[] = new byte[4];
    if (in.read(headerBytes) != headerBytes.length) {
      return false;
    }

    if (!header.equals(new String(headerBytes, StandardCharsets.US_ASCII))) {
      return false;
    }

    return true;
  }

  /**
   * Checks the header of the given file to ensure it is a level.
   *
   * @param file
   * @return Whether the given file could be a valid level.
   */
  public static boolean checkHeader(File file) {
    try {
      return Level.checkHeader(new FileInputStream(file));
    } catch (IOException err) {
      return false;
    }
  }

  /**
   * Gets an abstract Level constructor which reads from a DataInputStream.
   *
   * @return The readable object.
   */
  public static ReadStream getReadable() {
    return new ReadStream() {
      @Override
      public Object read(DataInputStream in) throws IOException {
        return new Level(in);
      }
    };
  }

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
   * Clones a Level from the given Level.
   *
   * @param src
   */
  public Level(Level src) {
    rules = new Rules(src.rules);
    initialBoard = new Board(src.initialBoard);
    currentBoard = null;
    currentScore = 0;
  }

  /**
   * Creates a Level from a DataInputStream.
   *
   * @param in
   */
  public Level(DataInputStream in) throws IOException {
    if (!Level.checkHeader(in)) {
      throw new RuntimeException("level file is not a stored level");
    }

    // not a stellar system, but good enough given that we'll likely only have
    // one or two versions in use
    int version = in.readInt();
    if (version != Level.version && version != 0) {
      throw new RuntimeException("level file has incompatible version");
    }

    rules = new Rules(in, version);
    initialBoard = new Board(rules, in);
    currentBoard = null;
    currentScore = 0;
  }

  /**
   * Write the Level to a DataOutputStream.
   *
   * @param out
   */
  @Override
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
