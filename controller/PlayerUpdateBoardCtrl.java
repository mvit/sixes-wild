package controller;

import model.Board;
import model.CellType;
import model.PlayerModel;
import model.Point;
import model.Tile;
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
					    try {
						       Thread.sleep(10);
								 app.getView().paintImmediately(0, 0, 500, 500);
						    } catch (InterruptedException ie) {
						    }
				  }
				  if (model.level.currentBoard.grid[y][x].type == CellType.BUCKET
						  && model.level.currentBoard.getTileAbove(new Point(x,y)).number == 5
						  && model.level.currentBoard.grid[y][x].tile == null) {
					  model.level.currentBoard.grid[y][x].tile = model.level.currentBoard.takeTileAbove(new Point(x,y));
					  hasEmpty = true;
					    try {
						       Thread.sleep(10);
								 app.getView().paintImmediately(0, 0, 500, 500);
						    } catch (InterruptedException ie) {
						    }
				  }
			  }
			  if (hasEmpty) {
				  try {
				       Thread.sleep(70);
						// app.getView().paintImmediately(0, 0, 500, 500);
				    } catch (InterruptedException ie) {
				    }
			  }
		  }
	 }
	 
	 
	 public void processBoardSmooth() {
		 for (int numPass = 0; numPass < Board.height; numPass++) {
			 boolean passChange = false;
			 for (int y = Board.height - 1; y >= 0; y--) {
				 boolean rowChange = false;
				 for (int x = Board.width - 1; x >= 0; x--) {
					 if (y == 0) {
						 if (model.level.currentBoard.grid[x][y].tile == null
								 && model.level.currentBoard.grid[x][y].type != CellType.INERT
								 && model.level.currentBoard.grid[x][y].type != CellType.BUCKET) {
							 model.level.currentBoard.grid[x][y].tile = new Tile(model.level.rules);
							 passChange = true;
							 rowChange = true;
							 try {
							       Thread.sleep(10);
									 app.getView().paintImmediately(0, 0, 500, 500);
							    } catch (InterruptedException ie) {
							    }
						 }
							 
					 }
					 else {
						 if (model.level.currentBoard.grid[x][y-1].tile != null
								 && model.level.currentBoard.grid[x][y-1].type != CellType.BUCKET
								 && nextOpenCell(x, y-1,model.level.currentBoard.grid[x][y-1].tile.number) != -1) {
							 int tempY = nextOpenCell(x, y-1,model.level.currentBoard.grid[x][y-1].tile.number);
							 Tile tempTile = model.level.currentBoard.grid[x][y-1].tile;
							 model.level.currentBoard.grid[x][y-1].tile = null;
							 model.level.currentBoard.grid[x][tempY].tile = tempTile;
							 passChange = true;
							 rowChange = true;
							 try {
							       Thread.sleep(10);
									 app.getView().paintImmediately(0, 0, 500, 500);
							    } catch (InterruptedException ie) {
							    }
						 }
						 if (model.level.currentBoard.grid[x][y].tile == null
								 && model.level.currentBoard.grid[x][y].type != CellType.INERT
								 && model.level.currentBoard.grid[x][y].type != CellType.BUCKET
								 && !hasPlayableAbove(x,y)) {
							 model.level.currentBoard.grid[x][y].tile = new Tile(model.level.rules);
							 passChange = true;
							 rowChange = true;
							 try {
							       Thread.sleep(10);
									 app.getView().paintImmediately(0, 0, 500, 500);
							    } catch (InterruptedException ie) {
							    }
						 }
					 }
				 }
				 if (rowChange) {
					 try {
					       Thread.sleep(10);
							// app.getView().paintImmediately(0, 0, 500, 500);
					    } catch (InterruptedException ie) {
					    }
				 }
			 }
			 if (passChange) {
				 try {
				       Thread.sleep(0);
						// app.getView().paintImmediately(0, 0, 500, 500);
				    } catch (InterruptedException ie) {
				    }
			 }
		 }
			 
	 }
	 
	 /**
	  * 
	  * @param x
	  * @param y
	  * @param tileValue
	  * @return y coordinate of open cell given a certain tile value. return -1 if no available cells
	  */
	 public int nextOpenCell(int x, int y, int tileValue) {
		 for (int i = y+1; i < Board.height; i++) {
			 if (model.level.currentBoard.grid[x][i].tile == null
					 && model.level.currentBoard.grid[x][i].type != CellType.INERT) {
				 if (model.level.currentBoard.grid[x][i].type != CellType.BUCKET) {
					 return i;
				 }
				 else if (model.level.currentBoard.grid[x][y].tile.number == 5) {
					 return i;
				 }
			 }
			 if (model.level.currentBoard.grid[x][i].tile != null) {
				 return -1;
			 }
		 }
		 return -1;
	 }
	
	 
	 public boolean hasPlayableAbove(int x, int y) {
		 for (int i = y-1; i >= 0; i--) {
			 if (model.level.currentBoard.grid[x][i].type == CellType.BUCKET) {
				 return false;
			 }
			 else if (model.level.currentBoard.grid[x][i].type != CellType.INERT) {
				 return true;
			 }
			 else if (model.level.currentBoard.grid[x][i].tile != null) {
				 return true;
			 }
		 }
		 
		 return false;
	 }
}
