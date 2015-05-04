package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import boundary.PlayerApplication;
import model.Board;
import model.Cell;
import model.Move;
import model.PlayerModel;
import model.PlayerState;
import model.Point;
import model.Tile;
import model.Variation;

/**
 * @author Eli Skeggs, Nick Chaput
 */
public class PlayerSwapCtrl implements ActionListener {
  PlayerApplication app;
  PlayerModel model;

  public PlayerSwapCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public void startSwap(Point point) {
    if (model.playerState != PlayerState.SWAP) {
      System.out.println("You borked it somehow (Triggered swap when player state != swap)");
      model.move = new Move();
      model.counter--;
    } else if (model.variation == Variation.RELEASE
    			&& model.level.currentBoard.grid[point.x][point.y].tile.number == 5) {
        model.move = new Move();
        model.counter--;
    } else if (model.move.points.size() == 0) {
      model.move.expand(model.level.currentBoard, point);
    } else if (model.move.points.size() == 1) {
      if (model.move.isAdjacent(point)) {
        model.move.expand(model.level.currentBoard, point);
        swap();
        model.move = new Move();
        model.playerState = PlayerState.NONE;
        model.counter--;
      } else {
        // TODO: what should happen here? anything?
      }
    } else {
      System.out.println("You borked it somehow (Too many tiles in swap)");
      model.move = new Move();
      model.playerState = PlayerState.NONE;
      model.counter--;
    }
    app.getView().repaint();
  }

  protected void swap() {
    int numCells = model.move.points.size();
    if (numCells != 2) {
      System.err.println("Wrong number of cells to swap: " + numCells);
      return;
    }

    // get the two cells from the Move's points
    Board board = model.level.currentBoard;
    Iterator<Point> iterator = model.move.points.iterator();
    Point a = iterator.next(), b = iterator.next();
    Cell cellA = board.grid[a.x][a.y], cellB = board.grid[b.x][b.y];

    // swap tiles
    Tile swap = cellA.tile;
    cellA.tile = cellB.tile;
    cellB.tile = swap;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    model.playerState = PlayerState.SWAP;
  }
}
