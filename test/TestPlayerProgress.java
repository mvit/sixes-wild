package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;

import model.LevelProgress;
import model.PlayerProgress;

import org.junit.Test;

/**
 * @author Eli Skeggs
 */
public class TestPlayerProgress {
  @Test
  public void test() throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PlayerProgress progress = new PlayerProgress();

    assertEquals(0, progress.attemptedLevels());
    progress.setAchievedScore(1, 132, true);
    assertEquals(0, progress.attemptedLevels());
    progress.setAchievedScore(0, 132, true);
    assertEquals(1, progress.attemptedLevels());

    for (LevelProgress item : progress) {
      assertEquals(0, item.level);
      assertEquals(132, item.bestScore);
      assertEquals(true, item.passedLevel);
    }

    progress.setAchievedScore(1, 253, false);
    assertEquals(2, progress.attemptedLevels());

    Iterator<LevelProgress> iter = progress.iterator();
    LevelProgress item = iter.next();
    assertEquals(0, item.level);
    assertEquals(132, item.bestScore);
    assertEquals(true, item.passedLevel);

    item = iter.next();
    assertEquals(1, item.level);
    assertEquals(253, item.bestScore);
    assertEquals(false, item.passedLevel);
    assertFalse(iter.hasNext());

    progress.setAchievedScore(0, 50, true);
    assertEquals(2, progress.attemptedLevels());

    iter = progress.iterator();
    item = iter.next();
    assertEquals(0, item.level);
    assertEquals(132, item.bestScore);
    assertEquals(true, item.passedLevel);

    item = iter.next();
    assertEquals(1, item.level);
    assertEquals(253, item.bestScore);
    assertEquals(false, item.passedLevel);
    assertFalse(iter.hasNext());

    progress.setAchievedScore(0, 150, true);
    assertEquals(2, progress.attemptedLevels());

    iter = progress.iterator();
    item = iter.next();
    assertEquals(0, item.level);
    assertEquals(150, item.bestScore);
    assertEquals(true, item.passedLevel);

    item = iter.next();
    assertEquals(1, item.level);
    assertEquals(253, item.bestScore);
    assertEquals(false, item.passedLevel);
    assertFalse(iter.hasNext());

    progress.write(new DataOutputStream(out));

    byte[] data = out.toByteArray();
    PlayerProgress copy = new PlayerProgress(new DataInputStream(new ByteArrayInputStream(data)));

    iter = copy.iterator();
    item = iter.next();
    assertEquals(0, item.level);
    assertEquals(150, item.bestScore);
    assertEquals(true, item.passedLevel);

    item = iter.next();
    assertEquals(1, item.level);
    assertEquals(253, item.bestScore);
    assertEquals(false, item.passedLevel);
    assertFalse(iter.hasNext());
  }
}
