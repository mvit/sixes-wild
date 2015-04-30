package controller;

import boundary.PlayerApplication;
import model.PlayerModel;

/**
 * @author Eli Skeggs, Nick Chaput
 */
public class PlayerReleaseCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerReleaseCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public boolean specialMove() {
    return false;
  }

  public boolean finishMove() {
	if(model.counter<= 0)
		return false;
	
	model.counter--;
	
	if(model.move.isValid()) {
		//TODO: Bucket checking 
		return true;
	}
    return false;
  }
}
