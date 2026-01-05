package io.github.stdonnelly.adventofcode.day09.model.png;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/// Tests for [PackedBitBuffer]
///
/// This was extremely necessary, as there were several bugs in the `put(byte)` and `putByte(byte)`
/// methods, including:
/// - Somehow I neglected to OR the existing byte in `put(byte)` with the new one, and was just
///   erasing previous bits.
/// - The original `put(byte)` bitmask operation was `b %= bitOffset`. This doesn't work because:
///   - It should be `bitDepth`, not `bitOffset`.
///   - Just applying modulo here doesn't work either.
/// - The original `put(byte)` bit shift operation was zeroing bytes when the bitOffset becomes 0.
/// - The shift operations in the misaligned `putByte(byte)` method were backwards, it was using
///   a shift of `8-bitOffset` for the left byte, and `bitOffset` for the right side.
class PackedBitBufferTest {
  /// Unsupported bitDepth throw an error
  @ParameterizedTest
  @ValueSource(ints = {-1, 0, 3, 8})
  void testConstructor_unsupportedBitDepth(final int bitDepth) {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          new PackedBitBuffer(1, bitDepth);
        });
  }

  /// Negative capacity throws an error
  @Test
  void testConstructor_negativeCapacity() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          new PackedBitBuffer(-1, 1);
        });
  }

  /// Correct bitDepths do work
  @ParameterizedTest
  @ValueSource(ints = {1, 2, 4})
  void testConstructor_bitDepth(final int bitDepth) {
    assertEquals(bitDepth, new PackedBitBuffer(0, bitDepth).getBitDepth());
  }

  /// Capacity rounds up to the nearest multiple of 8
  @ParameterizedTest
  @CsvSource(
      textBlock =
          """
          0,0
          1,8
          4,8
          8,8
          9,16
          """)
  void testConstructor_capacity(final int inputCapacity, final int expectedCapacity) {
    final PackedBitBuffer packedBitBuffer = new PackedBitBuffer(inputCapacity, 1);
    assertEquals(expectedCapacity, packedBitBuffer.getCapacity());
    assertEquals(expectedCapacity / 8, packedBitBuffer.getByteBuffer().capacity());
  }

  /// Elements are appended correctly
  ///
  /// All testcases use a 2-byte buffer
  ///
  /// @param bitDepth Passed to constructor
  /// @param inputElements The elements to input into the bit buffer
  /// @param expectedBytes The expected bytes in the buffer after the operations
  @ParameterizedTest
  @MethodSource("testPutArgumentProvider")
  void testPut(final int bitDepth, final byte[] inputElements, final byte[] expectedBytes) {
    PackedBitBuffer packedBitBuffer = new PackedBitBuffer(16, bitDepth);

    for (byte element : inputElements) {
      packedBitBuffer.put(element);
    }

    assertArrayEquals(expectedBytes, packedBitBuffer.getBytes());
  }

  /// @see #testPut(int,byte,byte,byte\[\])
  static Stream<Arguments> testPutArgumentProvider() {
    return Stream.of(
        // Do nothing
        arguments(1, new byte[0], new byte[] {0, 0}),
        // Insert one bit
        arguments(1, new byte[] {1}, new byte[] {(byte) 0b1000_0000, 0}),
        // Insert 3 bits
        arguments(1, new byte[] {1, 0, 1}, new byte[] {(byte) 0b1010_0000, 0}),
        // Insert 3 nybbles
        arguments(
            4, new byte[] {0b1101, 0b1011, 0b0111}, new byte[] {(byte) 0b1101_1011, 0b0111_0000}),
        // Insert 2 2-bit arguments
        // This also checks that the bitmask works
        arguments(2, new byte[] {0b10, 0b101}, new byte[] {(byte) 0b1001_0000, 0}),
        // Insert all 2-bit arguments
        arguments(
            2,
            new byte[] {0b10, 0b01, 0b10, 0b01, 0b01, 0b10, 0b01, 0b10},
            new byte[] {(byte) 0b1001_1001, 0b0110_0110}));
  }

  /// Test putting an aligned byte
  @Test
  void testPutByte_aligned() {
    PackedBitBuffer packedBitBuffer = new PackedBitBuffer(8, 1);
    packedBitBuffer.putByte((byte) 0b0101_1010);
    assertEquals(0b0101_1010, packedBitBuffer.getBytes()[0]);
  }

  /// Test putting a misaligned byte
  @Test
  void testPutByte_misaligned() {
    PackedBitBuffer packedBitBuffer = new PackedBitBuffer(16, 1);
    packedBitBuffer.put((byte) 1);
    packedBitBuffer.putByte((byte) 0b1101_1011);
    assertEquals((byte) 0b1110_1101, packedBitBuffer.getBytes()[0]);
    assertEquals((byte) 0b1000_0000, packedBitBuffer.getBytes()[1]);
  }
}
