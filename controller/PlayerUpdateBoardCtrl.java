package controller;

import model.Board;
import model.CellType;
import model.PlayerModel;
import model.Point;
import boundary.PlayerApplication;

/**
 * 
 * @author Nick Chaput
 *
 */

public class PlayerUpdateBoardCtrl {

	PlayerApplication app;
	PlayerModel model;
	
	public PlayerUpdateBoardCtrl(PlayerApplication app, PlayerModel model) {
		this.app = app;
		this.model = model;
	}
	
	 public void processBoard() {
		  for (int x = Board.width - 1; x >= 0; x--) {
			 /* try {
			       Thread.sleep(15);
					// app.getView().paintImmediately(0, 0, 500, 500);
			    } catch (InterruptedException ie) {
			    }*/
			  boolean hasEmpty = false;
			  for (int y = Board.height - 1; y >= 0; y--) {
				  if (model.level.currentBoard.grid[y][x].type != CellType.INERT 
						  && model.level.currentBoard.grid[y][x].type != CellType.BUCKET
						  && model.level.currentBoard.grid[y][x].tile == null) {
					  model.level.currentBoard.grid[y][x].tile = model.level.currentBoard.takeTileAbove(new Point(x,y));
					  hasEmpty = true;
					    /*try {
						       Thread.sleep(30);
								 app.getView().paintImmediately(50, 50, 500, 500);
						    } catch (InterruptedException ie) {
						    }*/
				  }
				  if (model.level.currentBoard.grid[y][x].type == CellType.BUCKET
						  && model.level.currentBoard.getTileAbove(new Point(x,y)).number == 5
						  && model.level.currentBoard.grid[y][x].tile == null) {
					  model.level.currentBoard.grid[y][x].tile = model.level.currentBoard.takeTileAbove(new Point(x,y));
					  hasEmpty = true;
					    /*try {
						       Thread.sleep(30);
								 app.getView().paintImmediately(50, 50, 500, 500);
						    } catch (InterruptedException ie) {
						    }*/
				  }
			  }
			  if (hasEmpty) {
				  try {
				       Thread.sleep(60);
						 app.getView().paintImmediately(0, 0, 500, 500);
				    } catch (InterruptedException ie) {
				    }
			  }
		  }
	 }
	
}
