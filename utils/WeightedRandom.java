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
  public int weightedRandom(int[] weights) {
    if (weights.length <= 0) {
      return -1;
    }

    int sum = 0, onlyValid = 0;
    for (int i = 0; i < weights.length; i++) {
      sum += weights[i];
      if (weights[i] > 0) {
        onlyValid = i;
      }
    }

    if (sum == 0) {
      throw new RuntimeException("no available weights to choose from");
    }

    // shortcut if there's only one valid weight
    if (sum == 1) {
      return onlyValid;
    }

    int compare = 0, value = random.nextInt(sum);
    for (int i = 0; i < weights.length; i++) {
      compare += weights[i];

      if (value < compare) {
        return i;
      }
    }

    throw new RuntimeException("logical error, weightedRandom was unable to " +
      "generate a random value from the provided weights");
  }
}
