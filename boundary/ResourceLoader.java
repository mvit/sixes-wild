package boundary;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

public class ResourceLoader {
  protected Set<ProgressListener> listeners = new HashSet<ProgressListener>();
  protected List<String> resources = new ArrayList<String>();
  protected Map<String, BufferedImage> loadedResources =
    new HashMap<String, BufferedImage>();

  protected boolean loading = false;

  protected void emitProgress(double progress) {
    for (ProgressListener listener : listeners) {
      listener.handleProgress(progress);
    }
  }

  protected void loadResource(String resource) throws IOException {
    loadedResources.put(resource, ImageIO.read(new File("resource/img/" + resource)));
  }

  public void loadResources() throws IOException {
    int count = 0, total = resources.size();
    loading = true;
    for (String resource : resources) {
      emitProgress(count / total);
      loadResource(resource);
    }
    emitProgress(1.0d);
  }

  public void addResource(String resource) {
    if (loading) {
      throw new RuntimeException(
        "Cannot load additional resources after loading process has started");
    }
    resources.add(resource);
  }

  public BufferedImage getResource(String resource) {
    return loadedResources.get(resource);
  }

  public void onProgress(ProgressListener listener) {
    listeners.add(listener);
  }

  public void offProgress(ProgressListener listener) {
    listeners.remove(listener);
  }
}
