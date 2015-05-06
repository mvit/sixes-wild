package test;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import model.PlayerModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.PlayerInstructionsCtrl;
import controller.PlayerLoadLevelSelectCtrl;
import boundary.PlayerApplication;
import boundary.PlayerInstructionsView;
import boundary.PlayerLevelSelectView;
import boundary.PlayerMainMenuView;
import boundary.ResourceLoader;

public class TestPlayerMainMenuView {

	PlayerApplication app;
	PlayerModel model;
	ResourceLoader loader;
	
	@Before
	public void setUp() {
	    loader = new ResourceLoader();
	    model = new PlayerModel();
	    app = new PlayerApplication(model, loader);
	}
	
	@After
	public void tearDown() {
		app.dispose();
		app = null;
	}

	@Test
	public void test() {
		//Set the context window
		app.setView(new PlayerMainMenuView(app, model));
		assertTrue(app.getView() instanceof PlayerMainMenuView);
		//Test the btnLevelSelect and its controllers
		ActionEvent event1 = new ActionEvent(((PlayerMainMenuView) app.getView()).btnLevelSelect, 0, "yes" );
		new PlayerLoadLevelSelectCtrl(app, model).actionPerformed(event1);
		assertTrue(app.getView() instanceof PlayerLevelSelectView);
		//Set the context window
		app.setView(new PlayerMainMenuView(app, model));
		assertTrue(app.getView() instanceof PlayerMainMenuView);
		//Test the btnInstructions and its controllers
		ActionEvent event2 = new ActionEvent(((PlayerMainMenuView) app.getView()).btnInstructions, 0, "yes" );
		new PlayerInstructionsCtrl(app, model).actionPerformed(event2);
		assertTrue(app.getView() instanceof PlayerInstructionsView);
	}
}
