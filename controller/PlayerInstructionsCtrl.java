package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PlayerModel;
import boundary.PlayerApplication;
import boundary.PlayerInstructionsView;

/**
 * Loads Instructions page.
 * @author Bailey Sheridan
 *
 */
public class PlayerInstructionsCtrl implements ActionListener{

	PlayerApplication app;
	PlayerModel model;
	
	public PlayerInstructionsCtrl(PlayerApplication app, PlayerModel model){
		this.app = app;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		app.setView(new PlayerInstructionsView(app, model));
	}

}
