package io.github.stdonnelly.adventofcode.day09.model.png.chunk;

import java.nio.ByteBuffer;

/// Trailer chunk
///
/// This is a singleton because there is only one possible representation of IEND.
@SuppressWarnings("java:S6548")
public final class Iend extends PngChunk {
  private static final ByteBuffer DATA = ByteBuffer.allocate(0).asReadOnlyBuffer();
  private static Iend instance;

  public static Iend getInstance() {
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
  public ByteBuffer getData() {
    return DATA;
  }
}
