package test;

import static org.junit.Assert.*;

import model.BuilderModel;

import org.junit.Test;

import boundary.BuilderApplication;
import boundary.BuilderMainMenuView;
import boundary.ResourceLoader;

public class TestBuilderLevelEditor {

	BuilderApplication app;
	BuilderModel model;
	ResourceLoader loader;
	
	
	private void setUp() {
	    loader = new ResourceLoader();
	    model = new BuilderModel();
	    app = new BuilderApplication(model, loader);
	}
	
	private void tearDown() {
		app.dispose();
		app = null;
	}
	
	@Test
	public void test() {
		setUp();
		app.setView(new BuilderMainMenuView(app, model));
		assertTrue(app.getView() instanceof BuilderMainMenuView);
		tearDown();	
	}

}
