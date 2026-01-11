package io.github.stdonnelly.adventofcode.day09.model;

/// Represents a colored tile on the floor
public enum FloorTile {
  WHITE('.', (byte) 255, (byte) 255, (byte) 255),
  BLACK('.', (byte) 0, (byte) 0, (byte) 0),
  RED('#', (byte) 255, (byte) 0, (byte) 0),
  GREEN('X', (byte) 0, (byte) 255, (byte) 0);

  private final char charRepresentation;
  private final byte red;
  private final byte green;
  private final byte blue;

  private FloorTile(char charRepresentation, byte red, byte green, byte blue) {
    this.charRepresentation = charRepresentation;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public char getCharRepresentation() {
    return charRepresentation;
  }

  public byte getRed() {
    return red;
  }

  public byte getGreen() {
    return green;
  }

  public byte getBlue() {
    return blue;
  }

  @Override
  public String toString() {
    return switch (this) {
      case WHITE, BLACK -> ".";
      case RED -> "#";
      case GREEN -> "X";
    };
  }
}
