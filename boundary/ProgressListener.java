package boundary;

public interface ProgressListener {
  // progress is a double value between 0 and 1
  public void handleProgress(double progress);
}
