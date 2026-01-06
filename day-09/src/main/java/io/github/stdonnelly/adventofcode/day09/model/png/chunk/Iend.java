package io.github.stdonnelly.adventofcode.day09.model.png.chunk;

/// Trailer chunk
///
/// This is a singleton because there is only one possible representation of IEND.
public final class Iend extends PngChunk {
  private static final byte[] DATA = new byte[0];
  private Iend instance;

  public Iend getInstance() {
    if (instance == null) {
      instance = new Iend();
    }
    return instance;
  }

  private Iend() {}

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public String getType() {
    return "IEND";
  }

  @Override
  public byte[] getData() {
    return DATA;
  }
}
