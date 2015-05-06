package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import model.BuilderModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.BuilderNewLevelCtrl;
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
	

    @Before
	public void setUp() {
	    loader = new ResourceLoader();
	    model = new BuilderModel();
	    app = new BuilderApplication(model, loader);
	}

    @After
	public void tearDown() {
		app.dispose();
		app = null;
	}
	
	@Test
	public void test() {

		//Test that MainMenuView has been initial
		app.setView(new BuilderMainMenuView(app, model));
		assertTrue(app.getView() instanceof BuilderMainMenuView);
		ActionEvent event1 = new ActionEvent(((BuilderMainMenuView)(app.initialView)).btnNewLevel, 0, "yes");
		new BuilderNewLevelCtrl(app, model).actionPerformed(event1);
	}

}
