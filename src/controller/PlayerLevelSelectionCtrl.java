package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.PlayerModel;
import boundary.PlayerApplication;

public class PlayerLevelSelectionCtrl implements ActionListener{
	PlayerApplication app;
	PlayerModel model;

	public PlayerLevelSelectionCtrl(PlayerApplication app, PlayerModel model) {
		this.app = app;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

}
