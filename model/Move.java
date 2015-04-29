package model;

import java.util.HashSet;
import java.util.Set;

/**
 * The Move class, which stores the currently selected set of points on the
 * PlayerApplication's board.
 *
 * @author Bailey Sheridan, Nick Chaput, and Eli Skeggs
 */
public class Move {
  // TODO: structure to optimize for the eventual removal and replacement of
  // tiles (i.e. organize the points by row)
  public Set<Point> points = new HashSet<Point>();
  public int moveSum = 0;

  public Move() {}

  /**
   * Returns whether the move is a valid move.
   *
   * A move is valid if it contains at least two tiles that sum to 6.
   */
  public boolean isValid() {
    return points.size() > 1 && moveSum == 6;
  }

  /**
   * Expand the move to include another point.
   *
   * Precondition: board contains a tile at the given x, y point.
   */
  public void expand(Board board, Point point) {
	boolean isUnique = true;
	for (Point p : points) {
  	    if (point.x == p.x && point.y == p.y) {
  	    	isUnique = false;
  	    }
  	}
	if (isUnique) {
		if (points.add(point)) {
			moveSum += (board.grid[point.x][point.y].tile.number + 1);
    	}
	}
  }
}
