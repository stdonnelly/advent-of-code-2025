package io.github.stdonnelly.adventofcode.day09.model.png;

import java.nio.ByteBuffer;

/// Similar to [ByteBuffer], but allows for elements that are smaller than 1 byte
///
/// This is needed because the PNG specification uses packed bytes when the bit depth is less than
/// 8. This program uses a bit depth of 2 with an indexed color image type
public class PackedBitBuffer {
  // #region Fields
  private final ByteBuffer byteBuffer;
  private final int bitDepth;
  private int bitOffset;

  // #endregion

  // #region Constructors

  /// Constructor
  ///
  /// @param byteBuffer The underlying [ByteBuffer]
  /// @param bitDepth The number of bits in an element, can be 1, 2, or 4. Non-powers of 2 are
  /// unsupported due to difficulty with offset calculation. 8-bits and above are unsupported
  /// because a [ByteBuffer] should be used instead in that case.
  public PackedBitBuffer(final ByteBuffer byteBuffer, final int bitDepth) {
    // Validation
    if (bitDepth != 1 && bitDepth != 2 && bitDepth != 4) {
      throw new IllegalArgumentException(
          "Unable to allocate a PackedBitBuffer with a bit depth of " + bitDepth);
    }

    this.byteBuffer = byteBuffer;
    this.bitDepth = bitDepth;
  }

  /// Create a new instance with a given capacity
  ///
  /// @param capacity The capacity of this PackedBitBuffer in bits. The actually capacity will be
  /// rounded up to the nearest multiple of 8.
  /// @param bitDepth The number of bits in an element, can be 1, 2, or 4. Non-powers of 2 are
  /// unsupported due to difficulty with offset calculation. 8-bits and above are unsupported
  /// because a [ByteBuffer] should be used instead in that case.
  public static PackedBitBuffer withCapacity(final int capacity, final int bitDepth) {
    // Validation
    if (capacity < 0) {
      throw new IllegalArgumentException(
          "Cannot allocate a PackedBitBuffer with a negative capacity");
    }

    // Calculate the capacity needed in bytes, which is ceiling of the capacity, rounded up
    final int capacityInBytes = capacity / 8 + (capacity % 8 != 0 ? 1 : 0);

    return new PackedBitBuffer(ByteBuffer.allocate(capacityInBytes), bitDepth);
  }

  // #endregion

  // #region Public methods

  /// Relative put method
  ///
  /// Writes the least significant `bitDepth` bits into this buffer at the current position, and
  /// then increments the position.
  public void put(byte b) {
    // Get the new bit offset
    bitOffset = (bitOffset + bitDepth) % 8;

    // Apply the bitmask
    b &= (1 << bitDepth) - 1;
    // Shift the byte to be packed into the next position
    if (bitOffset != 0) {
      b <<= 8 - bitOffset;
    }

    // Put the bits
    int position = byteBuffer.position();
    byteBuffer.put(position, (byte) (byteBuffer.get(position) | b));

    // Increment the position if needed
    if (bitOffset == 0) {
      byteBuffer.position(byteBuffer.position() + 1);
    }
  }

  /// Put an entire byte
  ///
  /// If the buffer is not aligned, the appended byte will span multiple bytes
  ///
  /// @see ByteBuffer#put(byte)
  public void putByte(byte b) {
    // If aligned to the byte, just call byteBuffer.put(byte)
    if (bitOffset == 0) {
      byteBuffer.put(b);
    } else {
      // If misaligned, split and put into separate bytes
      final byte left = (byte) (b >>> bitOffset);
      final byte right = (byte) (b << (8 - bitOffset));

      // Get by position to avoid incrementing the position
      byte newLeft = byteBuffer.get(byteBuffer.position());
      newLeft |= left;
      // Put without the position argument to increment the position
      byteBuffer.put(newLeft);

      // Put with position to prevent incrementing the position
      byteBuffer.put(byteBuffer.position(), right);
    }
  }

  /// Align the position to the next byte if the position is mid-byte
  ///
  /// If the position is already aligned to the byte, this does nothing
  public void alignToByte() {
    if (bitOffset == 0) {
      return;
    }

    byteBuffer.position(byteBuffer.position() + 1);
    bitOffset = 0;
  }

  // #endregion

  // #region Getters and setters

  public ByteBuffer getByteBuffer() {
    return byteBuffer;
  }

  public int getBitDepth() {
    return bitDepth;
  }

  public int getCapacity() {
    return byteBuffer.capacity() * 8;
  }

  public byte[] getBytes() {
    return byteBuffer.array();
  }

  /// Get the position in bits
  public int getPosition() {
    return (byteBuffer.position() * 8) + bitOffset;
  }

  /// Set the position in bits
  ///
  /// `position` must be less than the capacity and must be a multiple of the `bitDepth`
  public void setPosition(int position) {
    if (position % bitDepth != 0) {
      throw new IllegalArgumentException(
          "Cannot set the position to be misaligned with the bit depth");
    }

    byteBuffer.position(position / 8);
    bitOffset = position % 8;
  }

  // #endregion

  // #region Object method overrides

  @Override
  public boolean equals(Object obj) {
    return obj instanceof PackedBitBuffer o
        && bitDepth == o.bitDepth
        && byteBuffer.equals(o.byteBuffer);
  }

  @Override
  public int hashCode() {
    return byteBuffer.hashCode() ^ bitDepth;
  }

  @Override
  public String toString() {
    return String.format("PackedBitBuffer[bitDepth=%d, byteBuffer=%s]", bitDepth, byteBuffer);
  }

  // #endregion
}
