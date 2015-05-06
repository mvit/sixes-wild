package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.WeightedRandom;

/**
 * @author Eli Skeggs
 *
 */
public class TestWeightedRandom {
  /**
   * Test method for {@link utils.WeightedRandom#weightedRandom(int[])}.
   */
  @Test
  public void testWeightedRandom() {
    WeightedRandom random = new WeightedRandom();
    assertTrue(random.weightedRandom(new int[] {}) < 0);
    assertEquals(0, random.weightedRandom(new int[] {1}));
    assertEquals(1, random.weightedRandom(new int[] {0, 1}));
    assertEquals(0, random.weightedRandom(new int[] {100, 0}));

    random = new WeightedRandom(0L);
    assertEquals(0, random.weightedRandom(new int[] {100, 1}));
  }

  @Test(expected=RuntimeException.class)
  public void testWeightedRandomMissingWeights() {
    WeightedRandom random = new WeightedRandom();
    random.weightedRandom(new int[] {0});
  }
}
