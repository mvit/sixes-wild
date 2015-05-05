package test;

import static org.junit.Assert.*;
import model.BuilderModel;

import org.junit.Test;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;
import boundary.BuilderMainMenuView;
import boundary.ResourceLoader;


/**
 * BuilderLevelEditorView tests
 *
 * @author Cem Unsal, and Maurizio Vitale
 */

public class TestBuilderLevelEditor {

	BuilderApplication app;
	BuilderModel model;
	ResourceLoader loader;
	BuilderLevelEditorView levelEditorView;
	
	
	private void setUp() {
	    loader = new ResourceLoader();
	    model = new BuilderModel();
	    app = new BuilderApplication(model, loader);
	    levelEditorView = new BuilderLevelEditorView(app, model);
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
