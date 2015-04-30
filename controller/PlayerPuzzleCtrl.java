package controller;

import java.awt.EventQueue;

import boundary.PlayerApplication;
import model.PlayerModel;

/**
 * @author Eli Skeggs, Nick Chaput, Bailey Sheridan
 */
public class PlayerPuzzleCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerPuzzleCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public boolean specialMove() {
    return false;
  }

  public boolean finishMove() {
    if(model.counter<=0)
    	return false;

    model.counter--;
    
    if(model.move.isValid()) {
	    if(model.counter==0) {
	    	EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
		            PlayerEndLevelCtrl end = new PlayerEndLevelCtrl(app, model);
					end.endLevel();
				}
	    	});
	        return true;
	    }
    }
    return false;
  }
}
