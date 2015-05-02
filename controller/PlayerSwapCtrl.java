package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import boundary.PlayerApplication;
import model.Move;
import model.PlayerModel;
import model.PlayerState;
import model.Point;
import model.Tile;

/**
 * @author Eli Skeggs, Nick Chaput
 */
public class PlayerSwapCtrl implements ActionListener{
  PlayerApplication app;
  PlayerModel model;

  public PlayerSwapCtrl(PlayerApplication app, PlayerModel model) {
	  super();
    this.app = app;
    this.model = model;
  }
  
  public void swapInitiated(Point point) {
	  System.out.println("SwapCtrl sInit" + model.playerState.toString());
	  if (model.playerState != PlayerState.SWAP) {
		  System.out.println("You borked it somehow (Triggered swap when player state != swap)");
		  model.move = new Move();
		  model.counter--;
	  }
	  else if (model.move.points.size() == 0) {
		  model.move.expand(model.level.currentBoard, point);
	  }
	  else if (model.move.points.size() == 1) {
		  if (model.move.isAdjacent(point)) {
			  model.move.expand(model.level.currentBoard, point);
			  swap();
			  model.move = new Move();
			  model.playerState = PlayerState.NONE;
			  model.counter--;
		  }
		  else {
			  //What should happen here? anything?
			  //TODO: Decide^
		  }
	  }
	  else {
		  System.out.println("You borked it somehow (Too many tiles in swap)");
		  model.move = new Move();
		  model.playerState = PlayerState.NONE;
		  model.counter--;
	  }
	  app.getView().repaint();
  }

  private void swap() {
	// TODO Auto-generated method stub
	Point[] tempPoints = new Point[2];
	tempPoints = model.move.points.toArray(new Point[0]);
	if (tempPoints.length != 2) {
		System.err.println("Nick is dumb bbbbbbbbbbbbb");
		return;
	}
	Tile tempTile = model.level.currentBoard.grid[tempPoints[0].x][tempPoints[0].y].tile;
	model.level.currentBoard.grid[tempPoints[0].x][tempPoints[0].y].tile = model.level.currentBoard.grid[tempPoints[1].x][tempPoints[1].y].tile;
	model.level.currentBoard.grid[tempPoints[1].x][tempPoints[1].y].tile = tempTile;
  }

  @Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	  	model.playerState = PlayerState.SWAP;
	  	

	    System.out.println("SwapCtrl aPerf " + model.playerState.toString());
	}
}
