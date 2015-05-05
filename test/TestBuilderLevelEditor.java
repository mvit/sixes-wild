package test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JPanel;

import model.BuilderModel;

import org.junit.Before;
import org.junit.Test;

import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;
import boundary.BuilderMainMenuView;
import boundary.ResourceLoader;
import boundary.SplashScreen;

/**
 * The builder levelEditor test class.
 *
 * @author Cem Unsal
 */

public class TestBuilderLevelEditor {


    BuilderModel model;
    BuilderApplication app;
    JPanel initialView;
    BuilderLevelEditorView levelEditorView;
    
    @Before
    public void setUpClass() throws Exception {


        ResourceLoader loader;
        SplashScreen splash;
        
        
        // start splash screen
        try {
          splash = new SplashScreen("splash1levelbuilder.png");
        } catch (IOException err) {
          System.err.println(err.getMessage());
          err.printStackTrace();
          return;
        }

        long loadStart = System.currentTimeMillis();
        model = new BuilderModel();

        loader = BuilderApplication.load();

        if (loader == null) {
          return;
        }

        // initialize main app
        app = new BuilderApplication(model, loader);
        initialView = new BuilderMainMenuView(app, model);

        // extend time if necessary
        long loadElapsed = System.currentTimeMillis() - loadStart;
        if (loadElapsed < 2000) {
          try {
            Thread.sleep(2000 - loadElapsed);
          } catch (InterruptedException err) {
            // if we've been interrupted, just close the splash screen
          } finally {
            // close splash screen
            splash.close();
          }
        }

        // start main app
        app.setView(initialView);
      
    	
    }
    
    
	@Test
	public void test() {
		levelEditorView= new BuilderLevelEditorView(app, model);
		
		
		assertTrue (true);
	}

}
