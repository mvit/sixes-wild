package test;

import static org.junit.Assert.*;
import model.Board;
import model.Move;
import model.Point;
import model.Rules;

import org.junit.Test;

/**
 * @author Eli Skeggs
 */
public class TestMove {
  @Test
  public void test() {
    Rules rules = new Rules();
    rules.numberWeights = new int[] {1, 0, 0, 0, 0, 0};
    rules.multiplierWeights = new int[] {1, 0, 0};
    Board board = new Board(rules);
    board.realizeTiles();

    Move move = new Move();

    move.expand(board, new Point(0, 0));
    assertEquals(1, move.points.size());
    assertFalse(move.isValid());

    // not adjacent
    move.expand(board, new Point(1, 1));
    assertEquals(1, move.points.size());
    assertFalse(move.isValid());

    move.expand(board, new Point(1, 0));
    assertEquals(2, move.points.size());
    assertFalse(move.isValid());

    move.expand(board, new Point(1, 1));
    assertEquals(3, move.points.size());
    assertFalse(move.isValid());

    move.expand(board, new Point(2, 1));
    move.expand(board, new Point(3, 1));
    move.expand(board, new Point(4, 1));
    assertEquals(6, move.points.size());
    assertTrue(move.isValid());
  }
}
