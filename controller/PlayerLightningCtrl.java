package controller;

import boundary.PlayerApplication;
import model.PlayerModel;

/**
 * Controller for the Lightning variation.
 *
 * @author Bailey Sheridan, Nick Chaput
 */
public class PlayerLightningCtrl implements PlayerVariationCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerLightningCtrl(PlayerApplication app, PlayerModel model) {
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
	
	if(model.move.isValid())
		return true;
	
    return false;
  }

  @Override
  public boolean remove() {
  	// TODO Auto-generated method stub
	specialMove();
	return false;
  }

  @Override
  public boolean scramble() {
	// TODO Auto-generated method stub
	specialMove();
	return false;
  }

  @Override
  public boolean swap() {
	// TODO Auto-generated method stub
	specialMove();
	return false;
  }
  
}
