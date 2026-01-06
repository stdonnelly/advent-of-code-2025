package io.github.stdonnelly.adventofcode.day09.model.png;

public enum ColorType {
  GREYSCALE((byte) 0, new byte[] {1, 2, 4, 8, 16}),
  TRUECOLOR((byte) 2, new byte[] {8, 16}),
  INDEXED_COLOR((byte) 3, new byte[] {1, 2, 4, 8}),
  GREYSCALE_WITH_ALPHA((byte) 4, new byte[] {8, 16}),
  TRUECOLOR_WITH_ALPHA((byte) 6, new byte[] {8, 16});

  private final byte colorTypeId;
  private final byte[] allowedBitDepths;

  private ColorType(byte colorTypeId, byte[] allowedBitDepths) {
    this.colorTypeId = colorTypeId;
    this.allowedBitDepths = allowedBitDepths;
  }

  /// The number to be put into the png header
  public byte getColorTypeId() {
    return colorTypeId;
  }

  /// The list of valid bit depths for the color type
  public byte[] getAllowedBitDepths() {
    return allowedBitDepths;
  }
}
