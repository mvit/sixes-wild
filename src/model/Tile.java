import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * The Tile class.
 */
public class Tile {
  private int number, multiplier;

  /**
   * Creates a random Tile from the given Rules' probability distribution.
   */
  public Tile(Rules rules) {
    this.number = rules.random.weightedRandom(rules.numberWeights) + 1;
    this.multiplier = rules.random.weightedRandom(rules.multiplierWeights) + 1;
  }

  /**
   * Creates a Tile given only a number.
   *
   * @constructor
   */
  public Tile(int number) {
    this.number = number;
    this.multiplier = 1;
  }

  /**
   * Creates a Tile given a number and multiplier.
   *
   * @constructor
   */
  public Tile(int number, int multiplier) {
    this.number = number;
    this.multiplier = multiplier;
  }

  /**
   * Creates a Tile from a DataInputStream.
   *
   * @constructor
   * @param {DataInputStream} in
   */
  public Tile(DataInputStream in) {
    number = in.readInt();
    if (number < 0 || number >= Rules.maxNumber) {
      throw new RuntimeException("stored tile contains an incorrect number");
    }
    multiplier = in.readInt();
    if (multiplier < 0 || multiplier >= Rules.maxNumber) {
      throw new RuntimeException(
        "stored tile contains an incorrect multiplier");
    }
  }

  /**
   * Write the Tile to a DataOutputStream.
   *
   * @param {DataOutputStream} out
   */
  public void write(DataOutputStream out) {
    out.writeInt(number);
    out.writeInt(multiplier);
  }
}
