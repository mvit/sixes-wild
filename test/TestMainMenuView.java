package test;

import static org.junit.Assert.*;

import java.awt.event.MouseEvent;

import model.PlayerModel;

import org.junit.Test;

import boundary.PlayerApplication;
import boundary.PlayerMainMenuView;
import boundary.ResourceLoader;

public class TestMainMenuView {

	PlayerApplication app;
	PlayerModel model;
	ResourceLoader loader;
	
	
	private void setUp() {
	    loader = new ResourceLoader();
	    model = new PlayerModel();
	    app = new PlayerApplication(model, loader);
	}
	
	private void tearDown() {
		app.dispose();
		app = null;
	}
	
	@Test
	public void test() {
		setUp();
		app.setView(new PlayerMainMenuView(app, model));
		assertTrue(app.getView() instanceof PlayerMainMenuView);
		MouseEvent e = new MouseEvent(app.getView(), 0, 0, 0, 0, 0, 0, false);
		tearDown();	
		//app.getView().
	}

}
