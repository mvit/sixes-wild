package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import model.Point;
import org.junit.Test;

/**
 * @author Eli Skeggs
 */
public class TestPoint {
  @Test
  public void test() {
    HashSet<Point> set = new HashSet<Point>();
    set.add(new Point(0, 0));
    set.add(new Point(1, 0));
    assertEquals(2, set.size());

    set.add(new Point(0, 0));
    assertEquals(2, set.size());

    set.add(new Point(0, 1));
    assertEquals(3, set.size());

    set.add(new Point(2, 2));
    assertEquals(4, set.size());

    HashSet<Object> set2 = new HashSet<Object>();
    set2.add(new Point(0, 0));
    set2.add(new Integer(7));
    assertEquals(2, set2.size());

    assertEquals(new Point(0, 0), new Point(0, 0));
    assertNotEquals(new Point(0, 0), new Point(0, 1));
    assertNotEquals(new Point(0, 0), new Point(1, 0));
    assertNotEquals(new Point(0, 0), new Point(1, 1));
    assertNotEquals(new Point(0, 0), new Integer(7));
  }
}
