package io.github.stdonnelly.adventofcode.day09.model.png.chunk;

/// Data chunk
public final class Idat extends PngChunk {

  @Override
  public String getType() {
    return "IDAT";
  }

  @Override
  public byte[] getData() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getData'");
  }
}
