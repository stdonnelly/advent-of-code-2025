package io.github.stdonnelly.adventofcode.day09.mapper;

import io.github.stdonnelly.adventofcode.day09.model.Floor;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.RenderedImage;

/// Mapper from [Floor] to [RenderedImage]
public class FloorToRenderedImageMapper {
  // #region Constants and fields
  private static final IndexColorModel INDEX_COLOR_MODEL =
      new IndexColorModel(
          2,
          4,
          /*                WHITE,       BLACK,         RED,       GREEN */
          new byte[] {(byte) 0xff, (byte) 0x00, (byte) 0xff, (byte) 0x00},
          new byte[] {(byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0xff},
          new byte[] {(byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00});

  // #endregion

  // #region Public methods
  /// The actual map function
  public RenderedImage map(Floor floor) {
    // Get width and height
    final int height = floor.tiles().length;
    // If the input is empty, just return a blank image (if this is a valid buffered image)
    if (height == 0) {
      return getBaseImage(0, 0);
    }
    final int width = floor.tiles()[0].length;

    // Get the image
    final BufferedImage image = getBaseImage(width, height);

    // Populate the image

    // Return
    return image;
  }

  // #endregion

  // #region Private and protected helpers
  /// Get the base BufferedImage
  public BufferedImage getBaseImage(int width, int height) {
    return new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY, INDEX_COLOR_MODEL);
  }

  // #endregion
}
