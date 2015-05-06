package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.PlayerApplication;
import boundary.PlayerLevelView;
import model.Move;
import model.PlayerModel;
import model.PlayerState;
import model.Point;
import model.Variation;

/**
 * @author Nick Chaput
 */
public class PlayerRemoveCtrl implements ActionListener{
  PlayerApplication app;
  PlayerModel model;

  public PlayerRemoveCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public void startRemove(Point point) {
    // TODO: generic implementation
    PlayerVariationCtrl pVar = model.variation.createCtrl(app, model);
    if (model.variation != Variation.RELEASE 
    		|| model.level.currentBoard.grid[point.x][point.y].tile.number != 5) {
      // GO INTO PLAYERMODEL AND REPLACE PLAYERVARIATIONCTRL WITH VARIATION
      // TODO: This is suboptimal, decide whether to change PlayerVariationCtrl.
      // specialMove() to individual methods for each special move
      // TODO: resolve the move
      model.move.expand(model.level.currentBoard, point);
    }
    //pVar.remove();
    remove();
    model.playerState = PlayerState.NONE;
    model.move = new Move();
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
	model.playerState = PlayerState.REMOVE;
  }
  
  public void remove() {
	  if (model.playerState != PlayerState.REMOVE 
	  		|| model.move.points.size() != 1){
		  System.out.println("Remove called when not valid");
		  model.move = new Move();
	  }
	  else {
		  for (Point p : model.move.points) {
			  model.level.currentBoard.grid[p.x][p.y].tile = null;
		  }
	      PlayerUpdateBoardCtrl updateBoardCtrl = new PlayerUpdateBoardCtrl(app, model);
	      updateBoardCtrl.processBoardSmooth();
	  }
	  if(model.counter>0)
		  model.counter--;
	  app.getView().repaint();
	  ((PlayerLevelView)app.getView()).update();
  }
}
