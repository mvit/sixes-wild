package utils;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Eli Skeggs
 */
public interface WriteStream {
  public void write(DataOutputStream out) throws IOException;
}
