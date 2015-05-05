package utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Abstracts many of the utilities for loading and writing streams, especially
 * focusing on handling failure gracefully.
 *
 * @author Eli Skeggs
 */
public class StreamFileUtils {
  public static boolean ensureParent(String file) {
    return ensureParent(new File(file));
  }

  public static boolean ensureParent(File file) {
    File parent = file.getParentFile();
    if (!parent.mkdirs() && !parent.isDirectory()) {
      System.err.println("Unable to create the directory structure");
      return false;
    }
    return true;
  }

  public static DataInputStream inStream(String file) {
    return inStream(new File(file));
  }

  public static DataInputStream inStream(File file) {
    DataInputStream in = null;

    try {
      in = new DataInputStream(new FileInputStream(file));
    } catch (IOException err) {
      System.err.println(err.getMessage());
      err.printStackTrace();

      close(in);
      in = null;
    }

    return in;
  }

  public static Object readStream(String file, ReadStream readable) {
    return readStream(new File(file), readable);
  }

  public static Object readStream(File file, ReadStream readable) {
    DataInputStream in = inStream(file);

    if (in == null) {
      System.err.println("Unable to open input stream");
      return null;
    }

    try {
      return readable.read(in);
    } catch (IOException err) {
      System.err.println("Unable to read input stream");
      System.err.println(err.getMessage());
      err.printStackTrace();
    } catch (RuntimeException err) {
      System.err.println("Error reading from input stream");
      System.err.println(err.getMessage());
      err.printStackTrace();
    } finally {
      close(in);
      in = null;
    }

    return null;
  }

  public static DataOutputStream outStream(String file) {
    return outStream(new File(file));
  }

  public static DataOutputStream outStream(File file) {
    DataOutputStream out = null;

    try {
      out = new DataOutputStream(new FileOutputStream(file));
    } catch (IOException err) {
      System.err.println(err.getMessage());
      err.printStackTrace();

      close(out);
      out = null;
    }

    return out;
  }

  public static boolean writeStream(String file, WriteStream writable) {
    return writeStream(new File(file), writable);
  }

  public static boolean writeStream(File file, WriteStream writable) {
    DataOutputStream out = outStream(file);

    if (out == null) {
      System.err.println("Unable to open output stream");
      return false;
    }

    try {
      writable.write(out);
    } catch (IOException err) {
      System.err.println("Unable to write output stream");
      System.err.println(err.getMessage());
      err.printStackTrace();

      return false;
    } catch (RuntimeException err) {
      System.err.println("Error writing to output stream");
      System.err.println(err.getMessage());
      err.printStackTrace();

      return false;
    } finally {
      close(out);
      out = null;
    }

    return true;
  }

  public static void close(InputStream in) {
    try {
      if (in != null) {
        in.close();
      }
    } catch (IOException err) {
      System.err.println(err.getMessage());
      err.printStackTrace();
    }
  }

  public static void close(OutputStream out) {
    try {
      if (out != null) {
        out.close();
      }
    } catch (IOException err) {
      System.err.println(err.getMessage());
      err.printStackTrace();
    }
  }

  public static boolean backup(String inputFile, String backupFile) {
    return backup(new File(inputFile), new File(backupFile));
  }

  public static boolean backup(File inputFile, String backupFile) {
    return backup(inputFile, new File(backupFile));
  }

  public static boolean backup(String inputFile, File backupFile) {
    return backup(new File(inputFile), backupFile);
  }

  public static boolean backup(File inputFile, File backupFile) {
    Path input = inputFile.toPath(), backup = backupFile.toPath();

    try {
      Files.move(input, backup, StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception err) {
      System.err.println(err.getMessage());
      err.printStackTrace();
      return false;
    }

    return true;
  }
}
