package boundary;

/**
 * A progress listener, which receives progress events consisting of the current
 * progress from 0.0d to 1.0d.
 *
 * @author Eli Skeggs
 */
public interface ProgressListener {
  // progress is a double value between 0 and 1
  public void handleProgress(double progress);
}
