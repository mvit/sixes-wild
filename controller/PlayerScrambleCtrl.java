package controller;

import boundary.PlayerApplication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.PlayerModel;

public class PlayerScrambleCtrl implements ActionListener {
  PlayerApplication app;
  PlayerModel model;

  public PlayerScrambleCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public void actionPerformed(ActionEvent event) {
	//TODO: generic implementation
	  model.variation.specialMove();
  }
}
