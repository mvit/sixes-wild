package controller;

import boundary.PlayerApplication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.PlayerModel;
/**
 * 
 * @author Nicholas Chaput and Maurizio Vitale
 *
 */
public class PlayerLevelSelectionCtrl implements ActionListener{
  PlayerApplication app;
  PlayerModel model;

  public PlayerLevelSelectionCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    // TODO: implement me!
  }
}
