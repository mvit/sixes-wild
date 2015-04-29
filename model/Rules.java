package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import utils.WeightedRandom;

/**
 * A container for a Level's Rules.
 *
 * @author Eli Skeggs, and Cem Unsal
 */
public class Rules {
  public static final int maxNumber = 6, maxMultiplier = 3;

  // TODO: load seed from input?
  WeightedRandom random = new WeightedRandom();
  public Variation variation;
  // represents the remaining moves or remaining time, depending on variation
  public int initialCounter;
  public int[] numberWeights, multiplierWeights, scoreThresholds;

  /**
   * The default constructor which creates some reasonable rules.
   *
   * TODO: should we have constructors like this at all? Not really the job of
   * the rules class to do this...
   */
  public Rules() {
    numberWeights = new int[maxNumber];
    for (int i = 0; i < maxNumber; i++) {
      numberWeights[i] = 50;
    }

    multiplierWeights = new int[maxMultiplier];
    for (int i = 0; i < maxMultiplier; i++) {
      // assumes few multipliers
      multiplierWeights[i] = (int) Math.pow(2, i);
    }

    variation = Variation.PUZZLE;
    initialCounter = 100;
    scoreThresholds = new int[] {10, 20, 40};
  }

  /**
   * Creates a Rules instance from a DataInputStream.
   *
   * @param in
   */
  public Rules(DataInputStream in) throws IOException {
    variation = Variation.getVariation((char) in.readByte());
    if (variation == null) {
      throw new RuntimeException("stored rules use unknown variation type");
    }
    initialCounter = in.readInt();
    if (initialCounter <= 0) {
      throw new RuntimeException(
        "stored rules has useless initial counter value");
    }
    int thresholdCount = in.readInt();
    if (thresholdCount < 1) {
      throw new RuntimeException(
        "stored rules contains invalid threshold count");
    }
    if (in.readInt() != maxNumber) {
      throw new RuntimeException(
        "stored rules contain the wrong number of number weights");
    }
    if (in.readInt() != maxMultiplier) {
      throw new RuntimeException(
        "stored rules contain the wrong number of multiplier weights");
    }
    scoreThresholds = new int[thresholdCount];
    for (int i = 0; i < scoreThresholds.length; i++) {
      scoreThresholds[i] = in.readInt();
    }
    numberWeights = new int[maxNumber];
    for (int i = 0; i < maxNumber; i++) {
      numberWeights[i] = in.readInt();
    }
    multiplierWeights = new int[maxMultiplier];
    for (int i = 0; i < maxMultiplier; i++) {
      multiplierWeights[i] = in.readInt();
    }
  }

  /**
   * Clones a Rules instance from the given Rules.
   *
   * @param rules
   */
  public Rules(Rules rules) {
    variation = rules.variation;
    initialCounter = rules.initialCounter;

    numberWeights = new int[rules.numberWeights.length];
    for (int i = 0; i < rules.numberWeights.length; i++) {
      numberWeights[i] = rules.numberWeights[i];
    }

    multiplierWeights = new int[rules.multiplierWeights.length];
    for (int i = 0; i < rules.multiplierWeights.length; i++) {
      multiplierWeights[i] = rules.multiplierWeights[i];
    }

    scoreThresholds = new int[rules.scoreThresholds.length];
    for (int i = 0; i < rules.scoreThresholds.length; i++) {
      scoreThresholds[i] = rules.scoreThresholds[i];
    }
  }

  /**
   * Write the Rules to a DataOutputStream.
   */
  public void write(DataOutputStream out) throws IOException {
    out.writeByte((byte) variation.code);
    out.writeInt(initialCounter);
    out.writeInt(scoreThresholds.length);
    out.writeInt(maxNumber);
    out.writeInt(maxMultiplier);
    for (int i = 0; i < scoreThresholds.length; i++) {
      out.writeInt(scoreThresholds[i]);
    }
    for (int i = 0; i < maxNumber; i++) {
      out.writeInt(numberWeights[i]);
    }
    for (int i = 0; i < maxMultiplier; i++) {
      out.writeInt(multiplierWeights[i]);
    }
  }
}
