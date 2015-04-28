package model;

/**
 * An x, y point.
 *
 * @author Eli Skeggs
 */
public class Point {
  public int x, y;

  /**
   * Create a Point.
   *
   * @constructor
   * @param {int} x
   * @param {int} y
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int hashCode() {
    return (new int[] {x, y}).hashCode();
  }

  public boolean equals(Point other) {
    return x == other.x && y == other.y;
  }
}
