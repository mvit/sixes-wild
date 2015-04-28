package controller;

import java.util.Set;

import boundary.PlayerApplication;
import model.PlayerModel;
import model.Point;

/**
 * Controller for the Elimination variation.
 * @author Bailey Sheridan
 *
 */
public class PlayerEliminationCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerEliminationCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public boolean specialMove() {
    return false;
  }

  public boolean finishMove() {
	if(model.counter<=0) { return false; }
	model.counter--;
	
	// set the cells to marked
	Set<Point> pts = model.move.points;
	for(Point p : pts)
		model.level.currentBoard.grid[p.x][p.y].marked = true;
	
	// if all required cells are marked
	// endLevel();
    return true;
  }
}
