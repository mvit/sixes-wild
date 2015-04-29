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
		 app.getView().validate();
		  for (int y = Board.height - 1; y >= 0; y--) {
			  for (int x = Board.width - 1; x >= 0; x--) {
				  if (model.level.currentBoard.grid[y][x].type != CellType.INERT 
						  && model.level.currentBoard.grid[y][x].type != CellType.BUCKET
						  && model.level.currentBoard.grid[y][x].tile == null) {
					  model.level.currentBoard.grid[y][x].tile = model.level.currentBoard.takeTileAbove(new Point(x,y));
				  }
				  if (model.level.currentBoard.grid[y][x].type == CellType.BUCKET
						  && model.level.currentBoard.getTileAbove(new Point(x,y)).number == 5
						  && model.level.currentBoard.grid[y][x].tile == null) {
					  model.level.currentBoard.grid[y][x].tile = model.level.currentBoard.takeTileAbove(new Point(x,y));
				  }
			  }
		  }
	 }
	
}
