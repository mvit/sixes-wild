package utils;

import java.util.Random;

/**
 * A class for generating a weighted index from an array of integer weights.
 */
public class WeightedRandom {
  protected Random random;

  /**
   * Create a WeightedRandom instance without a seed.
   */
  public WeightedRandom() {
    random = new Random();
  }

  /**
   * Create a WeightedRandom instance with a seed.
   */
  public WeightedRandom(long seed) {
    random = new Random(seed);
  }

  /**
   * The primary function which selects an index in the weights array based on
   * the weights in the array.
   *
   * @param weights The weights to select from.
   * @return The index into the weights array that has been selected.
   */
  public int weightedRandom(double[] weights) {
    double sum = 0.0d, compare = random.nextDouble();
    for (int i = 0; i < weights.length; i++) {
      sum += weights[i];
      if (compare < sum) {
        return i;
      }
    }

    return weights.length;
  }
}
