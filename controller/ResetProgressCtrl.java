package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import boundary.PlayerApplication;
import model.PlayerModel;
import model.PlayerProgress;

public class ResetProgressCtrl implements ActionListener{

	PlayerModel model;
	PlayerApplication app;
	
	public ResetProgressCtrl(PlayerApplication app, PlayerModel model) {
		this.model = model;
		this.app = app;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Files.deleteIfExists(new File("resource/progress/progress").toPath());
		} catch (IOException f) {
			// TODO Auto-generated catch block
			f.printStackTrace();
		}
		model.progress = new PlayerProgress();
		
	}

}
