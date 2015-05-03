package controller;

import boundary.PlayerApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import model.Board;
import model.Cell;
import model.CellType;
import model.PlayerModel;
import model.Tile;
import model.Variation;

/**
 * @author Eli Skeggs, and Nick Chaput
 */
public class PlayerScrambleCtrl implements ActionListener {
  PlayerApplication app;
  PlayerModel model;

  public PlayerScrambleCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  public void actionPerformed(ActionEvent event) {
    ArrayList<Cell> cells = new ArrayList<Cell>();
    ArrayList<Tile> tiles = new ArrayList<Tile>();
    for (int x = 0; x < Board.width; x++) {
      for (int y = 0; y < Board.height; y++) {
        Cell cell = model.level.currentBoard.grid[x][y];
        if (cell.type != CellType.INERT
            && cell.type != CellType.BUCKET
            && cell.tile != null
            && (cell.tile.number != 5
            && model.level.rules.variation != Variation.RELEASE)) {
          cells.add(cell);
          tiles.add(cell.tile);
        }
      }
    }
    // TODO: should use seed
    Collections.shuffle(tiles);
    Iterator<Tile> tileIter = tiles.iterator();
    for (Cell cell : cells) {
      cell.tile = tileIter.next();
    }
    model.variation.createCtrl(app, model).scramble();
    app.getView().repaint();
  }
}
