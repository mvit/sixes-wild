package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import model.PlayerModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.PlayerLevelSelectCtrl;
import controller.PlayerLoadLevelCtrl;
import boundary.PlayerApplication;
import boundary.PlayerLevelSelectView;
import boundary.PlayerLevelView;
import boundary.ResourceLoader;

public class TestPlayerLevelSelectView {
	PlayerApplication app;
	PlayerModel model;
	ResourceLoader loader;

	@Before
	public void setUp() {
	    loader = new ResourceLoader();
	    model = new PlayerModel();
	    app = new PlayerApplication(model, loader, 0);
	}

	@After
	public void tearDown() {
		app.dispose();
		app = null;
	}

	@Test
	public void test() {
		//Set the context window
		app.setView(new PlayerLevelSelectView(app,model));
		assertTrue(app.getView() instanceof PlayerLevelSelectView);
		//Load a level
		ActionEvent event1 = new ActionEvent(((PlayerLevelSelectView) app.getView()).selectButtons[0], 0, "yes");
		new PlayerLevelSelectCtrl(app, model,"1").actionPerformed(event1);

		assertTrue(((PlayerLevelSelectView) app.getView()).lblLevel.getText().equals("Level 1"));
		assertTrue(((PlayerLevelSelectView) app.getView()).lblType.getText().equals("Type: Puzzle"));

		ActionEvent event2 = new ActionEvent(((PlayerLevelSelectView) app.getView()).btnPlayLevel, 0, "yes");
		new PlayerLoadLevelCtrl(app, model,"1").actionPerformed(event2);
		assertTrue(app.getView() instanceof PlayerLevelView);

		//assertTrue((PlayerLevelSelectView) app.getView());

	}

}
