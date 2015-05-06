package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import utils.WeightedRandom;

/**
 * A container for a Level's Rules.
 *
 * @author Eli Skeggs, Cem Unsal and Bailey Sheridan
 */
public class Rules {
  public static final int maxNumber = 6, maxMultiplier = 3;

  // TODO: load seed from input?
  WeightedRandom random = new WeightedRandom();
  public Variation variation;
  // represents the remaining moves or remaining time, depending on variation
  public int initialCounter;
  public int[] scoreThresholds, numberWeights, multiplierWeights;
  protected int totalNumberWeight, totalMultiplierWeight;

  /**
   * The default constructor which creates some reasonable rules.
   *
   * TODO: should we have constructors like this at all? Not really the job of
   * the rules class to do this...
   */
  public Rules() {
    numberWeights = new int[maxNumber];
    for (int i = 0; i < maxNumber; i++) {
      numberWeights[i] = 1;
    }
    totalNumberWeight = maxNumber;

    multiplierWeights = new int[maxMultiplier];
    for (int i = 0; i < maxMultiplier; i++) {
      // assumes few multipliers
      multiplierWeights[i] = 1;
    }
    totalMultiplierWeight = maxMultiplier;

    variation = Variation.PUZZLE;
    initialCounter = 100;
    scoreThresholds = new int[] {10, 20, 40};
  }

  /**
   * Creates a Rules instance from a DataInputStream.
   *
   * @param in
   * @param version Hints at the appropriate version to load with.
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
    totalNumberWeight = 0;
    numberWeights = new int[maxNumber];
    for (int i = 0; i < maxNumber; i++) {
      int weight = in.readInt();
      totalNumberWeight += weight;
      numberWeights[i] = weight;
    }
    totalMultiplierWeight = 0;
    multiplierWeights = new int[maxMultiplier];
    for (int i = 0; i < maxMultiplier; i++) {
      int weight = in.readInt();
      totalMultiplierWeight += weight;
      multiplierWeights[i] = weight;
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
    totalNumberWeight = rules.totalNumberWeight;

    multiplierWeights = new int[rules.multiplierWeights.length];
    for (int i = 0; i < rules.multiplierWeights.length; i++) {
      multiplierWeights[i] = rules.multiplierWeights[i];
    }
    totalMultiplierWeight = rules.totalMultiplierWeight;

    scoreThresholds = new int[rules.scoreThresholds.length];
    for (int i = 0; i < rules.scoreThresholds.length; i++) {
      scoreThresholds[i] = rules.scoreThresholds[i];
    }
  }

  /**
   * Generate a random number for a tile using the configured rules.
   *
   * @return The generated tile number.
   */
  public int randomNumber() {
    return random.weightedRandom(numberWeights);
  }

  /**
   * Generate a random multiplier for a tile with the given number.
   *
   * Will return a multiplier of 1 for tiles with the maximum number.
   *
   * @param number The number for which to generate a multiplier.
   * @return The generated multiplier.
   */
  public int randomMultiplier(int number) {
    if (number == maxNumber - 1) {
      return 1;
    }
    return random.weightedRandom(multiplierWeights) + 1;
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

  /**
   * Returns true if this Rules object is equivalent to the argument.
   * @param rul
   * @return
   */
  @Override
  public boolean equals(Object obj) {
    if (obj != null && (obj instanceof Rules)) {
      Rules rul = (Rules) obj;
      if (variation != rul.variation || initialCounter != rul.initialCounter ||
          scoreThresholds.length != rul.scoreThresholds.length ||
          numberWeights.length != rul.numberWeights.length ||
          multiplierWeights.length != rul.multiplierWeights.length) {
        return false;
      }
      for (int i = 0; i < scoreThresholds.length; i++) {
        if (scoreThresholds[i] != rul.scoreThresholds[i]) {
          return false;
        }
      }
      for (int i = 0; i < numberWeights.length; i++) {
        if (numberWeights[i] != rul.numberWeights[i]) {
          return false;
        }
      }
      for (int i = 0; i < multiplierWeights.length; i++) {
        if (multiplierWeights[i] != rul.multiplierWeights[i]) {
          return false;
        }
      }
      return true;
    }
    return false;
  }
}
