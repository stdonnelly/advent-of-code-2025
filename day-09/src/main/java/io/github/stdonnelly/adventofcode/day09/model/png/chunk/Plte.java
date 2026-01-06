package io.github.stdonnelly.adventofcode.day09.model.png.chunk;

/// Pallette chunk
public final class Plte extends PngChunk {

  @Override
  public String getType() {
    return "PLTE";
  }

  @Override
  public byte[] getData() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getData'");
  }
}
