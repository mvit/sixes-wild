package controller;

import javax.swing.JOptionPane;

import model.PlayerModel;
import boundary.PlayerApplication;
import boundary.PlayerEndLevelView;

public class PlayerEndLevelCtrl {

	PlayerApplication app;
	PlayerModel model;
	
	public PlayerEndLevelCtrl(PlayerApplication app, PlayerModel model) {
		this.app = app;
		this.model = model;
	}
	
	public void endLevel() {
		JOptionPane.showMessageDialog(new PlayerEndLevelView(app, model), "Level Complete!");
	}
}
