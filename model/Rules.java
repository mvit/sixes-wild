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
  public int[] scoreThresholds;
  public double[] numberWeights, multiplierWeights;

  /**
   * The default constructor which creates some reasonable rules.
   *
   * TODO: should we have constructors like this at all? Not really the job of
   * the rules class to do this...
   */
  public Rules() {
    int count = maxNumber - 1;
    double fraction = 1.0d / maxNumber;
    numberWeights = new double[count];
    for (int i = 0; i < count; i++) {
      numberWeights[i] = fraction;
    }

    count = maxMultiplier - 1;
    fraction = 1.0d / maxMultiplier;
    multiplierWeights = new double[count];
    for (int i = 0; i < count; i++) {
      multiplierWeights[i] = fraction;
    }

    variation = Variation.PUZZLE;
    initialCounter = 100;
    scoreThresholds = new int[] {10, 20, 40};
  }

  /**
   * Reads and converts integer probabilities into double probabilities.
   *
   * @param in The data input stream to read from.
   * @param count The number of integer probabilities to read.
   * @return The array of double probabilities.
   */
  protected double[] readConvertProbabilities(DataInputStream in, int count)
      throws IOException {
    int total = 0;
    int[] inWeights = new int[count];
    double[] weights = new double[count - 1];
    for (int i = 0; i < count; i++) {
      int weight = in.readInt();
      total += weight;
      inWeights[i] = weight;
    }
    int doubleCount = count - 1;
    double doubleTotal = (double) total;
    for (int i = 0; i < doubleCount; i++) {
      weights[i] = inWeights[i] / doubleTotal;
    }
    return weights;
  }

  /**
   * Reads and converts integer probabilities into double probabilities.
   *
   * @param in The data input stream to read from.
   * @param count The number of double probabilities to read.
   * @return The array of double probabilities.
   */
  protected double[] readDoubleProbabilities(DataInputStream in, int count)
      throws IOException {
    double[] weights = new double[count];
    double total = 0.0d;
    for (int i = 0; i < count; i++) {
      double weight = in.readDouble();
      total += weight;
      if (total > 1.0d) {
        throw new RuntimeException("stored rules sum of weights exceeds 1.0");
      }
      weights[i] = weight;
    }
    return weights;
  }

  /**
   * Creates a Rules instance from a DataInputStream.
   *
   * @param in
   * @param version Hints at the appropriate version to load with.
   */
  public Rules(DataInputStream in, int version) throws IOException {
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
    if (version == 0) {
      numberWeights = readConvertProbabilities(in, maxNumber);
      multiplierWeights = readConvertProbabilities(in, maxMultiplier);
    } else {
      numberWeights = readDoubleProbabilities(in, maxNumber - 1);
      multiplierWeights = readDoubleProbabilities(in, maxMultiplier - 1);
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

    numberWeights = new double[rules.numberWeights.length];
    for (int i = 0; i < rules.numberWeights.length; i++) {
      numberWeights[i] = rules.numberWeights[i];
    }

    multiplierWeights = new double[rules.multiplierWeights.length];
    for (int i = 0; i < rules.multiplierWeights.length; i++) {
      multiplierWeights[i] = rules.multiplierWeights[i];
    }

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
   * Get the number at the specified index, estimating the final fraction if
   * applicable.
   *
   * @param number The number to get.
   * @return The number value.
   */
  public double getNumberWeight(int number) {
    if (number < numberWeights.length) {
      return numberWeights[number];
    }
    double result = 1.0d;
    for (int i = 0; i < numberWeights.length; i++) {
      result -= numberWeights[i];
    }
    return result;
  }

  /**
   * Get the multiplier at the specified index, estimating the final fraction if
   * applicable.
   *
   * @param multiplier The multiplier to get.
   * @return The multiplier value.
   */
  public double getMultiplierWeight(int multiplier) {
    if (multiplier < multiplierWeights.length) {
      return multiplierWeights[multiplier];
    }
    double result = 1.0d;
    for (int i = 0; i < multiplierWeights.length; i++) {
      result -= multiplierWeights[i];
    }
    return result;
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
    int numberCount = maxNumber - 1;
    for (int i = 0; i < numberCount; i++) {
      out.writeDouble(numberWeights[i]);
    }
    int multiplierCount = maxMultiplier - 1;
    for (int i = 0; i < multiplierCount; i++) {
      out.writeDouble(multiplierWeights[i]);
    }
  }
  
  /**
   * Returns true if this Rules object is equivalent to the argument.
   * @param rul
   * @return
   */
  public boolean equals(Rules rul) {
	  return ((variation.equals(rul.variation)) && (initialCounter == rul.initialCounter) 
			  && (scoreThresholds.equals(rul.scoreThresholds)) && (numberWeights.equals(rul.numberWeights))
			  && (multiplierWeights.equals(rul.multiplierWeights)));
  }
}
