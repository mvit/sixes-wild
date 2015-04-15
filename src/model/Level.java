import java.nio.charset.StandardCharsets;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * The Level class.
 */
public class Level {
  // TODO: where should these be stored?
  public static String header = "SWLV";
  public static int version = 0;

  Rules rules;
  Board initialBoard;

  /**
   * Create a Level with reasonable defaults.
   */
  public Level() {
    initialBoard = new Board();
    rules = new Rules();
  }

  /**
   * Creates a Board from a DataInputStream.
   *
   * @constructor
   * @param {DataInputStream} in
   */
  public Level(DataInputStream in) {
    byte headerBytes[] = new byte[4];
    if (in.read(headerBytes, 0, 4) != 4) {
      throw new RuntimeError("stored level incomplete");
    }
    if (!header.equals(new String(headerBytes, StandardCharsets.US_ASCII))) {
      throw new RuntimeError("provided data stream is not a stored level");
    }

    // not a stellar system, but good enough given that we'll likely only have
    // one or two versions in use
    int version = in.readInt();
    if (version != this.version) {
      throw new RuntimeError("provided data stream has incompatible version");
    }

    rules = new Rules(in);
    initialBoard = new Board(in);
  }

  /**
   * Write the Level to a DataOutputStream.
   *
   * @param {DataOutputStream} out
   */
  public void write(DataOutputStream out) {
    out.write(header.getBytes(StandardCharsets.US_ASCII));
    out.writeInt(version);
    rules.write(out);
    initialBoard.write(out);
  }
}
