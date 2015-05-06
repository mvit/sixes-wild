package controller;

import java.awt.EventQueue;

import boundary.PlayerApplication;
import model.Board;
import model.CellType;
import model.PlayerModel;

/**
 * @author Eli Skeggs, Nick Chaput and Bailey Sheridan
 */
public class PlayerReleaseCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerReleaseCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public boolean specialMove() {
    model.counter--;
    return false;
  }

  public boolean finishMove() {
	    if (model.counter <= 0) {
	      return false;
	    }

	    model.counter--;

	    if (model.counter == 0) {
	    //  EventQueue.invokeLater(new PlayerEndLevelCtrl(app, model, allBucketsFull()));
	    }
	    
	    if(model.move.isValid()) {
		    if(allBucketsFull()) {
		//	      EventQueue.invokeLater(new PlayerEndLevelCtrl(app, model, allBucketsFull()));
			    }
		    return true;
	    }
	    
	    return false;
	  }

  private boolean allBucketsFull() {
	for (int x = 0; x < Board.width; x++) {
		for (int y = 0; y < Board.height; y++) {
			  if (model.level.currentBoard.grid[x][y].type == CellType.BUCKET) {
				  if (model.level.currentBoard.grid[x][y].tile != null) {
					  if (model.level.currentBoard.grid[x][y].tile.number != 5) {
						  System.out.println("Something messed up and a non-Six is in your bucket: " + x + " " + y);
					  }
				  }
				  else {
					  return false;
				  }
		 	  }
		}
	}
	return true;
}

@Override
  public boolean remove() {
    // TODO: implement
    specialMove();
    return false;
  }

  @Override
  public boolean scramble() {
    // TODO: implement
    specialMove();
    return false;
  }

  @Override
  public boolean swap() {
    // TODO: implement
    specialMove();
    return false;
  }

  public void redundantFinish() {
	    if (model.counter == 0) {
	      EventQueue.invokeLater(new PlayerEndLevelCtrl(app, model, allBucketsFull()));
	    }
		if(allBucketsFull()) {
		   EventQueue.invokeLater(new PlayerEndLevelCtrl(app, model, true));
		}
  }
  
}
