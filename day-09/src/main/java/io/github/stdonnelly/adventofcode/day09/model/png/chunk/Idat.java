package io.github.stdonnelly.adventofcode.day09.model.png.chunk;

import java.nio.ByteBuffer;
import java.util.zip.Deflater;

/// Data chunk
public final class Idat extends PngChunk {
  // #region Fields
  private final ByteBuffer compressedData;

  // #endregion

  // #region Constructors and factory methods
  private Idat(ByteBuffer compressedData) {
    this.compressedData = compressedData;
  }

  /// Create a new instance with the compressed data
  ///
  /// This method expects the data to already be compressed with deflate. If the data is not yet
  /// compressed, use [ofUncompressed(ByteBuffer)][#ofUncompressed(ByteBuffer)]
  ///
  /// @param compressedData The data after compression
  public static Idat ofCompressed(final ByteBuffer compressedData) {
    return new Idat(compressedData);
  }

  /// Create a new instance with the uncompressed data
  ///
  /// This method compresses the input data before adding it to the image
  ///
  /// @param uncompressedData The data before compression
  public static Idat ofUncompressed(final ByteBuffer uncompressedData) {
    return new Idat(compress(uncompressedData));
  }

  // #endregion

  // #region PngChunk overrides
  @Override
  public String getType() {
    return "IDAT";
  }

  @Override
  public ByteBuffer getData() {
    return compressedData.asReadOnlyBuffer();
  }

  // #endregion

  // #region Private helpers
  private static ByteBuffer compress(final ByteBuffer uncompressedData) {
    try (Deflater deflater = new Deflater()) {
      // Start by adding the data and compressing
      // The initial size should be at least 8, because that is the length if there is no input.
      final int initialSize = Math.max(uncompressedData.remaining(), 8);
      deflater.setInput(uncompressedData);
      deflater.finish();

      // Probably unnecessary, but allocate a ByteBuffer that could hold the entire uncompressed
      // data. This ByteBuffer will hold the compressed data.
      ByteBuffer deflatedByteBuffer = ByteBuffer.allocate(initialSize);

      // Loop until the Deflater is finished
      // This loop exists in case the compressed data is larger than the uncompressed data
      while (!deflater.finished()) {
        deflater.deflate(deflatedByteBuffer);

        if (!deflater.finished()) {
          int oldPosition = deflatedByteBuffer.position();
          // The new capacity will be twice the old one
          int capacity = deflatedByteBuffer.capacity() * 2;

          deflatedByteBuffer =
              ByteBuffer.allocate(capacity)
                  .put(0, deflatedByteBuffer, 0, oldPosition)
                  .position(oldPosition);
        }
      }

      return deflatedByteBuffer.limit(deflatedByteBuffer.position()).rewind();
    }
  }
}
