package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import model.Level;
import model.Tile;

import org.junit.Test;

/**
 * @author Eli Skeggs
 */
public class TestLevel {
  @Test
  public void test() throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Level level = new Level();
    level.initialBoard.grid[0][0].tile = new Tile(5);
    level.write(new DataOutputStream(out));

    byte[] data = out.toByteArray();
    Level copy = new Level(new DataInputStream(new ByteArrayInputStream(data)));

    assertEquals(level, copy);
    assertEquals(5, copy.initialBoard.grid[0][0].tile.number);
    assertEquals(null, copy.initialBoard.grid[1][0].tile);

    Level copyTwo = new Level(level);
    assertEquals(level, copyTwo);
    assertEquals(copy, copyTwo);
  }
}
