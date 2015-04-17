package model;

/**
 * An x, y point.
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

  public boolean equals(Point other) {
    return x == other.x && y == other.y;
  }
}
