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

/**
 * Load specified resources and cache the for future use.
 *
 * Emits progress events to the registered progress listeners, to notify of
 * progress in loading the specified resources.
 *
 * @author Eli Skeggs
 */
public class ResourceLoader {
  protected Set<ProgressListener> listeners = new HashSet<ProgressListener>();
  protected List<String> resources = new ArrayList<String>();
  protected Map<String, BufferedImage> loadedResources =
    new HashMap<String, BufferedImage>();

  protected boolean loading = false;

  /**
   * Emit a progress event (a double) to all registered listeners.
   *
   * @param {double} progress
   */
  protected void emitProgress(double progress) {
    for (ProgressListener listener : listeners) {
      listener.handleProgress(progress);
    }
  }

  /**
   * Load the given resource.
   *
   * @param {String} resource The filename of the resource to load from within
   *   the resource/img directory.
   */
  protected void loadResource(String resource) throws IOException {
    loadedResources.put(resource, ImageIO.read(new File("resource/img/" + resource)));
  }

  /**
   * Actually load the resources that have been added.
   */
  public void loadResources() throws IOException {
    int count = 0, total = resources.size();
    loading = true;
    for (String resource : resources) {
      emitProgress(count / total);
      loadResource(resource);
    }
    emitProgress(1.0d);
  }

  /**
   * Add a resource to be loaded.
   *
   * @param {String} resource The filename of the resource to load from within
   *   the resource/img directory.
   */
  public void addResource(String resource) {
    if (loading) {
      throw new RuntimeException(
        "Cannot load additional resources after loading process has started");
    }
    resources.add(resource);
  }

  /**
   * Get the BufferedImage for the given resource.
   *
   * @param {String} resource The filename of the resource to load from within
   *   the resource/img directory.
   * @return {BufferedImage}
   */
  public BufferedImage getResource(String resource) {
    return loadedResources.get(resource);
  }

  /**
   * Register a progress listener.
   *
   * @param {ProgressListener} listener
   */
  public void onProgress(ProgressListener listener) {
    listeners.add(listener);
  }

  /**
   * Deregister a progress listener.
   *
   * @param {ProgressListener} listener
   */
  public void offProgress(ProgressListener listener) {
    listeners.remove(listener);
  }
}
