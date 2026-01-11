package io.github.stdonnelly.adventofcode.day09.model.png.chunk;

import io.github.stdonnelly.adventofcode.day09.model.png.RGBColor8Bit;
import java.nio.ByteBuffer;
import java.util.List;

/// Pallette chunk
public final class Plte extends PngChunk {
  private final ByteBuffer data;

  // #region Constructors and factory methods
  /// Constructs a new instance with a given number of entries
  ///
  /// Per the PNG spec, `1 <= size <= 256`. This does not need to correspond to the image bit depth.
  ///
  /// @param size The number of entries
  public Plte(final int size) {
    if (size < 1 || size > 256) {
      throw new IndexOutOfBoundsException(size);
    }

    // An entry is always 3 bytes
    data = ByteBuffer.allocate(size * 3);
  }

  /// Constructs a new instance with the given entries
  ///
  /// Per the PNG spec, `1 <= colors.size() <= 256`. This does not need to correspond to the image
  /// bit depth.
  ///
  /// @param colors The color entries
  public Plte(final List<RGBColor8Bit> colors) {
    this(colors.size());

    // Fill the buffer
    colors.stream().map(RGBColor8Bit::getData).forEachOrdered(data::put);
    data.rewind();
  }

  /// Constructs a new instance with every entry needed to completely fill a given bit depth
  ///
  /// @see #Plte(int)
  public static Plte ofBitDepth(byte bitDepth) {
    return new Plte(1 << bitDepth);
  }

  // #endregion

  // #region PngChunk overrides
  @Override
  public String getType() {
    return "PLTE";
  }

  @Override
  public ByteBuffer getData() {
    return data.asReadOnlyBuffer();
  }

  // #endregion
}
