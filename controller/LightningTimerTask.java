package controller;

import java.awt.EventQueue;
import java.util.TimerTask;

import model.PlayerModel;
import boundary.PlayerApplication;
import boundary.PlayerLevelView;

/**
 * TimerTask for the LightningTimer for the Lightning variation.
 *
 * @author Bailey Sheridan
 */
public class LightningTimerTask extends TimerTask {
  PlayerApplication app;
  PlayerModel model;

  public LightningTimerTask(PlayerApplication app, PlayerModel model){
    this.model = model;
  }

  /**
   * Runs until the counter hits zero.
   */
  @Override
  public void run() {
    if (model.counter > 0) {
      model.counter--;
      ((PlayerLevelView) app.getView()).update();
    }

    if (model.counter == 0) {
    	cancel();
    	EventQueue.invokeLater(new Runnable() {
    		@Override
    		public void run() {
                PlayerEndLevelCtrl end = new PlayerEndLevelCtrl(app, model);
    			end.endLevel();
    		}
    	});
    }
  }
}
