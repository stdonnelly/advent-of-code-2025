package io.github.stdonnelly.adventofcode.day09.model.png;

/// Represents a triplet of 8-bit RGB values
public class RGBColor8Bit {
  private final byte[] data;

  public RGBColor8Bit(byte red, byte green, byte blue) {
    this.data = new byte[] {red, green, blue};
  }

  // #region Getters and setters
  public byte getRed() {
    return data[0];
  }

  public void setRed(byte red) {
    this.data[0] = red;
  }

  public byte getGreen() {
    return data[1];
  }

  public void setGreen(byte green) {
    this.data[1] = green;
  }

  public byte getBlue() {
    return data[2];
  }

  public void setBlue(byte blue) {
    this.data[2] = blue;
  }

  public byte[] getData() {
    return data;
  }

  // #endregion
}
