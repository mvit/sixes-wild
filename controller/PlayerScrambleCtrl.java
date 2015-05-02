package controller;

import boundary.PlayerApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import model.Board;
import model.CellType;
import model.PlayerModel;
import model.Tile;
import model.Variation;

/**
 * @author Eli Skeggs, and Nick Chaput
 */
public class PlayerScrambleCtrl implements ActionListener {
  PlayerApplication app;
  PlayerModel model;

  public PlayerScrambleCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public void actionPerformed(ActionEvent event) {
    // TODO: generic implementation
    // model.variation.specialMove();
	 // System.out.println("Start Scramble");
	  ArrayList<Tile> tiles = new ArrayList<Tile>();
	  for (int x = 0; x < Board.width; x++) {
		  for (int y = 0; y < Board.height; y++) {
			  if (model.level.currentBoard.grid[x][y].type != CellType.INERT
					  && model.level.currentBoard.grid[x][y].type != CellType.BUCKET
					  && model.level.currentBoard.grid[x][y].tile != null
					  && (model.level.currentBoard.grid[x][y].tile.number != 5
							  && model.level.rules.variation != Variation.RELEASE)) {
				  tiles.add(model.level.currentBoard.grid[x][y].tile);
				  model.level.currentBoard.grid[x][y].tile = null;
			  }
		  }
	  }
	  Collections.shuffle(tiles);
	  for (int x = 0; x < Board.width; x++) {
		  for (int y = 0; y < Board.height; y++) {
			  if (model.level.currentBoard.grid[x][y].type != CellType.INERT
					  && model.level.currentBoard.grid[x][y].type != CellType.BUCKET
					  && model.level.currentBoard.grid[x][y].tile == null) {
				  if (tiles.get(0) != null) {
					  model.level.currentBoard.grid[x][y].tile = tiles.get(0);
					  tiles.remove(0);
				  }
				  else {
					  System.out.println("Nick's brain has failed and you should laugh at him.");
					  return;
				  }
			  }
		  }
	  }
	  model.variation.createCtrl(app, model).specialMove();
	  app.getView().repaint();
  }
}
