package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import model.BuilderModel;
import model.Variation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.BuilderNewLevelCtrl;
import controller.BuilderSetVariationCtrl;
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
		
		app.setView(new BuilderMainMenuView(app, model));
		
		//Test if view has been changed
		ActionEvent event1 = new ActionEvent(((BuilderMainMenuView)(app.initialView)).btnNewLevel, 0, "yes");
		new BuilderNewLevelCtrl(app, model).actionPerformed(event1);
		assertTrue(app.getView() instanceof BuilderLevelEditorView);
		
		//Test if the variation has changed to RELEASE
		ActionEvent event2 = new ActionEvent(((BuilderLevelEditorView)(app.getView())).button, 0, "yes");
		new BuilderSetVariationCtrl(app, model, Variation.RELEASE).actionPerformed(event2);
		assertEquals(Variation.RELEASE, model.level.rules.variation);
		
	}

}
