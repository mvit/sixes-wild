package controller;

import boundary.BoardView;
import boundary.BuilderApplication;
import boundary.BuilderLevelEditorView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.BuilderModel;
import model.Cell;
import model.Point;
import model.Tile;

public class BuilderBoardMouseCtrl extends MouseAdapter {
  BuilderApplication app;
  BuilderModel model;

  boolean dragging = false;

  public BuilderBoardMouseCtrl(BuilderApplication app, BuilderModel model) {
    this.app = app;
    this.model = model;
  }

  Point identifyPoint(MouseEvent event) {
    BuilderLevelEditorView view = (BuilderLevelEditorView) app.getView();
    return view.boardView.identifyPoint(event.getX(), event.getY());
  }

  protected void applyCell(MouseEvent event) {
    BuilderLevelEditorView view = (BuilderLevelEditorView) app.getView();
    BoardView boardView = view.boardView;

    Point point;
    if (model.currentType != null &&
        (point = boardView.identifyPoint(event.getX(), event.getY())) != null) {
      Cell cell = model.level.initialBoard.grid[point.x][point.y];
      if (model.currentUseNumber) {
        if (cell.tile == null) {
          cell.tile = new Tile(model.currentNumber, model.currentMultiplier);
        } else {
          cell.tile.number = model.currentNumber;
          cell.tile.multiplier = model.currentMultiplier;
        }
      } else {
        cell.type = model.currentType;
        cell.tile = null;
      }
      boardView.repaint();
    }
  }

  @Override
  public void mousePressed(MouseEvent event) {
    if (event.getButton() == MouseEvent.BUTTON1) {
      dragging = true;
      applyCell(event);
    }
  }

  @Override
  public void mouseReleased(MouseEvent event) {
    if (event.getButton() == MouseEvent.BUTTON1) {
      applyCell(event);
      dragging = false;
    }
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    if (dragging) {
      applyCell(event);
    }
  }
}
