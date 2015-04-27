package utils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Scale image utility, scales the given buffered image to the given width and
 * height.
 *
 * @author Eli Skeggs
 */
public class ScaleImage {
  public static BufferedImage scaleImage(BufferedImage source,
      int width, int height) {
    if (width == source.getWidth() && height == source.getHeight()) {
      return source;
    }

    BufferedImage scaled = new BufferedImage(width, height,
      BufferedImage.TYPE_INT_ARGB);

    AffineTransform transform = new AffineTransform();
    transform.scale((double) width / source.getWidth(),
      (double) height / source.getHeight());

    AffineTransformOp transformOp = new AffineTransformOp(transform,
      AffineTransformOp.TYPE_BILINEAR);

    scaled = transformOp.filter(source, scaled);

    return scaled;
  }
}
