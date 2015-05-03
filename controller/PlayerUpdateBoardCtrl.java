package controller;

import model.Board;
import model.Cell;
import model.CellType;
import model.PlayerModel;
import model.Tile;
import boundary.PlayerApplication;

/**
 * TODO: change calls to paintImmediately() to use actual measurements instead
 * of just a large number (currently 1500).
 *
 * @author Nick Chaput
 */

public class PlayerUpdateBoardCtrl {
  PlayerApplication app;
  PlayerModel model;

  public PlayerUpdateBoardCtrl(PlayerApplication app, PlayerModel model) {
    this.app = app;
    this.model = model;
  }

  /**
   * TODO(skeggse): restructure with queued events.
   */
  public void processBoard() {
    Board board = model.level.currentBoard;
    for (int y = Board.height - 1; y >= 0; y--) {
      /*try {
        Thread.sleep(15);
        // app.getView().paintImmediately(0, 0, 1500, 1500);
      } catch (InterruptedException err) {
      }*/
      boolean hasEmpty = false;
      for (int x = Board.width - 1; x >= 0; x--) {
        Cell cell = board.grid[x][y];
        if (cell.type != CellType.INERT && cell.type != CellType.BUCKET
            && cell.tile == null) {
          cell.tile = board.takeTileAbove(x, y);
          hasEmpty = true;
          try {
            Thread.sleep(10);
            app.getView().paintImmediately(0, 0, 1500, 1500);
          } catch (InterruptedException err) {
          }
        }
        if (cell.type == CellType.BUCKET
            && board.getTileAbove(x, y).number == 5
            && board.grid[x][y].tile == null) {
          board.grid[x][y].tile = board.takeTileAbove(x, y);
          hasEmpty = true;
          try {
            Thread.sleep(10);
            app.getView().paintImmediately(0, 0, 1500, 1500);
          } catch (InterruptedException err) {
          }
        }
      }
      if (hasEmpty) {
        try {
          Thread.sleep(70);
          // app.getView().paintImmediately(0, 0, 1500, 1500);
        } catch (InterruptedException err) {
        }
      }
    }
  }

  public void processBoardSmooth() {
    Board board = model.level.currentBoard;
    for (int numPass = 0; numPass < Board.height; numPass++) {
      boolean passChange = false;
      for (int y = Board.height - 1; y >= 0; y--) {
        boolean rowChange = false;
        for (int x = Board.width - 1; x >= 0; x--) {
          Cell cell = board.grid[x][y];
          if (y == 0) {
            if (cell.tile == null && cell.type != CellType.INERT
                && cell.type != CellType.BUCKET) {
              cell.tile = new Tile(model.level.rules);
              passChange = true;
              rowChange = true;
              try {
                Thread.sleep(10);
                app.getView().paintImmediately(0, 0, 1500, 1500);
              } catch (InterruptedException err) {
              }
            }
          } else {
            Cell prevCell = board.grid[x][y - 1];
            if (prevCell.tile != null && prevCell.type != CellType.BUCKET
                && nextOpenCell(x, y - 1, prevCell.tile.number) != -1) {
              int tempY = nextOpenCell(x, y - 1, prevCell.tile.number);
              Tile tempTile = prevCell.tile;
              prevCell.tile = null;
              board.grid[x][tempY].tile = tempTile;
              passChange = true;
              rowChange = true;
              try {
                Thread.sleep(10);
                app.getView().paintImmediately(0, 0, 1500, 1500);
              } catch (InterruptedException err) {
              }
            }
            if (cell.tile == null
                && cell.type != CellType.INERT
                && cell.type != CellType.BUCKET
                && !hasPlayableAbove(x, y)) {
              cell.tile = new Tile(model.level.rules);
              passChange = true;
              rowChange = true;
              try {
                Thread.sleep(10);
                app.getView().paintImmediately(0, 0, 1500, 1500);
              } catch (InterruptedException err) {
              }
            }
          }
        }
        if (rowChange) {
          try {
            Thread.sleep(10);
            // app.getView().paintImmediately(0, 0, 1500, 1500);
          } catch (InterruptedException err) {
          }
        }
      }
      if (passChange) {
        try {
          Thread.sleep(0);
          // app.getView().paintImmediately(0, 0, 1500, 1500);
        } catch (InterruptedException err) {
        }
      }
    }
  }

  /**
   *
   * @param x
   * @param y
   * @param tileValue
   * @return y coordinate of open cell given a certain tile value, -1 if no
   *   available cells
   */
  public int nextOpenCell(int x, int y, int tileValue) {
    for (int i = y+1; i < Board.height; i++) {
      if (model.level.currentBoard.grid[x][i].tile == null
          && model.level.currentBoard.grid[x][i].type != CellType.INERT) {
        if (model.level.currentBoard.grid[x][i].type != CellType.BUCKET) {
          return i;
        } else if (model.level.currentBoard.grid[x][y].tile.number == 5) {
          return i;
        }
      }
      if (model.level.currentBoard.grid[x][i].tile != null) {
        return -1;
      }
    }
    return -1;
  }

  public boolean hasPlayableAbove(int x, int y) {
    while (--y >= 0) {
      if (model.level.currentBoard.grid[x][y].type == CellType.BUCKET) {
        return false;
      }
      if (model.level.currentBoard.grid[x][y].type != CellType.INERT) {
        return true;
      }
      if (model.level.currentBoard.grid[x][y].tile != null) {
        return true;
      }
    }

    return false;
  }
}
