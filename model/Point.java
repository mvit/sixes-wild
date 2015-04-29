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
   * @param x
   * @param y
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Hash code for the point, based on the netbeans recommendation.
   *
   * @return The hash code for this point.
   */
  @Override
  public int hashCode() {
    return 71 * (497 + x) + y;
  }

  /**
   * Check if two points are equal.
   *
   * @param other The point to compare against.
   * @return Whether the points are equal.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Point) {
      Point point = (Point) other;
      return x == point.x && y == point.y;
    }
    return false;
  }
}
