package utils;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author Eli Skeggs
 */
public interface ReadStream {
  public Object read(DataInputStream in) throws IOException;
}
