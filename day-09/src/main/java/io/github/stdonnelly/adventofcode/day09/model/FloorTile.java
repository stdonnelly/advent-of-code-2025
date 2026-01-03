package io.github.stdonnelly.adventofcode.day09.model;

/// Represents a colored tile on the floor
public enum FloorTile {
  WHITE,
  BLACK,
  RED,
  GREEN;

  @Override
  public String toString() {
    return switch (this) {
      case WHITE, BLACK -> ".";
      case RED -> "#";
      case GREEN -> "X";
    };
  }
}
