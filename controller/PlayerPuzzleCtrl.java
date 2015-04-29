package controller;

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
	  return true;
  }
}
