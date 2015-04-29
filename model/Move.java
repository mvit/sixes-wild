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
   * Expand the move to include another unique point.
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

  /**
   * Check whether the given point is adjacent to any of the points in the move.
   *
   * @param point The point to check for adjacency.
   * @return Whether the given point is adjacent to at least one point in the
   *   current move (does not check that the new point is not already contained
   *   in the move).
   */
  public boolean isAdjacent(Point point) {
	  boolean isAdjacent = false;
	  for (Point p : points) {
		  isAdjacent = ((Math.abs(point.x-p.x) == 1 && point.y-p.y == 0)
				  		|| (point.x-p.x == 0 && Math.abs(point.y-p.y) == 1));
	  }
	  return isAdjacent;
  }
}
