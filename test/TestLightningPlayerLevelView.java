package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import model.PlayerModel;
import model.PlayerState;
import model.Tile;
import model.Variation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.PlayerBoardMouseCtrl;
import controller.PlayerLevelSelectCtrl;
import controller.PlayerLoadLevelCtrl;
import controller.PlayerRemoveCtrl;
import controller.PlayerRestartLevelCtrl;
import controller.PlayerScrambleCtrl;
import controller.PlayerSwapCtrl;
import boundary.PlayerApplication;
import boundary.PlayerBoardView;
import boundary.PlayerLevelSelectView;
import boundary.PlayerLevelView;
import boundary.ResourceLoader;
/**
 * 
 * @author Eli Skeggs and Maurizio Vitale
 *
 */
public class TestLightningPlayerLevelView {
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
    ActionEvent event2 = new ActionEvent(((PlayerLevelSelectView) app.getView()).btnPlayLevel, 0, "yes");
    new PlayerLoadLevelCtrl(app, model,"2").actionPerformed(event2);
    assertTrue(app.getView() instanceof PlayerLevelView);

    //Variation tests
    assertEquals(Variation.LIGHTNING, model.variation);
    assertEquals(0, model.score);
    //int moves = model.counter;
    Tile[] tiles = new Tile[6];

    //Make the first 6 tiles in the first row 1's with x1 multipliers
    for(int i = 0; i < 6; i++) {
      tiles[i] = new Tile(0);
      model.level.currentBoard.grid[i][0].tile = tiles[i];
      model.level.currentBoard.grid[i][0].tile.multiplier = 1;
    }

    //Make a mouse event that drags across the first 6 tiles in the first row
    PlayerBoardView boardView = ((PlayerLevelView) app.getView()).boardView;
    PlayerBoardView.Box start = boardView.identifyCell(0, 0), end = boardView.identifyCell(5, 0);
    PlayerBoardMouseCtrl mouseCtrl = new PlayerBoardMouseCtrl(app, model);

    MouseEvent mouseEvent = new MouseEvent(boardView, MouseEvent.MOUSE_PRESSED, 0, 0, start.x1 + 1, start.y1 + 1, 1, false, MouseEvent.BUTTON1);
    mouseCtrl.mousePressed(mouseEvent);
    //drag until the end
    for (int x = start.x1 + 1; x <= end.x1 + 1; x++) {
      mouseEvent = new MouseEvent(boardView, MouseEvent.MOUSE_DRAGGED, 0, 0, x, start.y1 + 1, 1, false);
      mouseCtrl.mouseDragged(mouseEvent);
    }

    mouseEvent = new MouseEvent(boardView, MouseEvent.MOUSE_RELEASED, 0, 0, end.x1 + 1, end.y1 + 1, 1, false, MouseEvent.BUTTON1);
    mouseCtrl.mouseReleased(mouseEvent);

    assertEquals(60, model.score);
    //assertEquals(moves - 1, model.counter);

    //Test Remove on the first (0,0) tile
    //moves = model.counter;
    //Select the remove move
    ActionEvent event3 = new ActionEvent(((PlayerLevelView) app.getView()).btnRemove, 0, "yes");
    new PlayerRemoveCtrl(app, model).actionPerformed(event3);

    assertEquals(PlayerState.REMOVE, model.playerState);

    mouseEvent = new MouseEvent(boardView, MouseEvent.MOUSE_PRESSED, 0, 0, start.x1 + 1, start.y1 + 1, 1, false, MouseEvent.BUTTON1);
    mouseCtrl.mousePressed(mouseEvent);

    mouseEvent = new MouseEvent(boardView, MouseEvent.MOUSE_RELEASED, 0, 0, start.x1 + 1, start.y1 + 1, 1, false, MouseEvent.BUTTON1);
    mouseCtrl.mouseReleased(mouseEvent);

   // assertEquals(moves - 1, model.counter);
    
    //Test Scramble on Board
   // moves = model.counter;
    //Select the remove move
    ActionEvent event4 = new ActionEvent(((PlayerLevelView) app.getView()).btnScramble, 0, "yes");
    new PlayerScrambleCtrl(app, model).actionPerformed(event4);

    assertEquals(PlayerState.NONE, model.playerState);
    
    //assertEquals(moves - 1, model.counter);
    
    //Test Swap on the first (0,0) tile and second (1,0) tiles
    //moves = model.counter;
    start = boardView.identifyCell(0, 0);
    end = boardView.identifyCell(1, 0);
    model.level.currentBoard.grid[0][0].tile = new Tile(3);
    model.level.currentBoard.grid[1][0].tile = new Tile(2);
    Tile tile1 = model.level.currentBoard.grid[0][0].tile;
    Tile tile2 = model.level.currentBoard.grid[1][0].tile;
    //Select the remove move
    ActionEvent event5 = new ActionEvent(((PlayerLevelView) app.getView()).btnSwap, 0, "yes");
    new PlayerSwapCtrl(app, model).actionPerformed(event5);

    assertEquals(PlayerState.SWAP, model.playerState);

    mouseEvent = new MouseEvent(boardView, MouseEvent.MOUSE_RELEASED, 0, 0, start.x1 + 1, start.y1 + 1, 1, false, MouseEvent.BUTTON1);
    mouseCtrl.mouseReleased(mouseEvent);

    mouseEvent = new MouseEvent(boardView, MouseEvent.MOUSE_RELEASED, 0, 0, end.x1 + 1, end.y1 + 1, 1, false, MouseEvent.BUTTON1);
    mouseCtrl.mouseReleased(mouseEvent);

   // assertEquals(moves - 1, model.counter);

    assertTrue(model.level.currentBoard.grid[0][0].tile.equals(tile2));
    assertTrue(model.level.currentBoard.grid[1][0].tile.equals(tile1));
    
    //Test Restart Controls
    //moves = model.counter;
    ActionEvent event6 = new ActionEvent(((PlayerLevelView) app.getView()).btnRestart, 0, "yes");
    new PlayerRestartLevelCtrl(app, model).actionPerformed(event6);
    //assertNotEquals(moves,model.counter);
    assertEquals(0,model.score);
    
  }
}
