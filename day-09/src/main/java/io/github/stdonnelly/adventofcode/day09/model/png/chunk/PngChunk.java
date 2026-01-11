package io.github.stdonnelly.adventofcode.day09.model.png.chunk;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

/// A chunk in the PNG specification
public abstract class PngChunk {
  /// Get the length of the chunk
  ///
  /// As per the PNG specification, version 3, section 5.3;
  /// <q cite="https://www.w3.org/TR/png-3/#5Chunk-layout">The length counts only the data field,
  /// not itself, the chunk type, or the CRC.</q>
  public int getLength() {
    return getData().remaining();
  }

  /// Get the four-character type string
  public abstract String getType();

  /// Get the data
  ///
  /// @implSpec For empty chunks, this should return an empty array.
  /// @implSpec Changes to the returned object, such as changing the mark or position, should not
  /// change the backing data or the object that is returned if this method is called again.
  public abstract ByteBuffer getData();

  /// Get the CRC32 of the chunk type and data fields, but not length
  public int getCrc() {
    CRC32 crc32 = new CRC32();

    // Add the type name and data
    crc32.update(getType().getBytes(StandardCharsets.US_ASCII));
    crc32.update(getData());

    return (int) crc32.getValue();
  }

  /// Get the full chunk, including the length, header, data, and CRC
  public ByteBuffer getFullChunk() {
    // Ensure that the type has a length of 4
    if (getType().length() != 4) {
      throw new IllegalStateException("Chunk type name length must be 4");
    }

    // Allocate enough room for the length, type, data, and CRC. According to the Java
    // documentation, the byte order should already be big endian, which is what PNG expects.
    ByteBuffer byteBuffer = ByteBuffer.allocate(4 + 4 + getLength() + 4);

    // Add the data
    byteBuffer.putInt(getLength());
    byteBuffer.put(getType().getBytes(StandardCharsets.US_ASCII));
    byteBuffer.put(getData());
    byteBuffer.putInt(getCrc());

    // Assert that the byteBuffer is full
    if (byteBuffer.position() != byteBuffer.capacity()) {
      throw new IllegalArgumentException("Full chunk byte buffer should be filled");
    }

    return byteBuffer.rewind();
  }

  @Override
  public boolean equals(Object obj) {
    // Equal if the other object is a chunk, has the same type, and has the same data.
    // CRC is implied to be the same if data is the same, so no need to check.
    return obj instanceof PngChunk o
        && this.getType().equals(o.getType())
        && this.getLength() == o.getLength()
        && this.getData().equals(o.getData());
  }

  @Override
  public int hashCode() {
    return getCrc();
  }
}
