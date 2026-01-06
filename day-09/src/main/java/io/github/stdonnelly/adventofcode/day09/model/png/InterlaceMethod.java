package io.github.stdonnelly.adventofcode.day09.model.png;

public enum InterlaceMethod {
  NONE((byte) 0),
  ADAM7((byte) 1);

  private final byte id;

  private InterlaceMethod(byte id) {
    this.id = id;
  }

  public byte getId() {
    return id;
  }
}
