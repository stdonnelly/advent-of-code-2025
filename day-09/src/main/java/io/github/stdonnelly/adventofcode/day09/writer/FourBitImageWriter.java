package io.github.stdonnelly.adventofcode.day09.writer;

import java.nio.file.Path;
import java.nio.file.Paths;

import io.github.stdonnelly.adventofcode.day09.model.Floor;

/// Writes a 4-bit image based on [Floor]
public class FourBitImageWriter {
  private final Path outPath;

  /// @param outPath The path of the file to output
  public FourBitImageWriter(Path outPath) {
    this.outPath = outPath;
  }

  /// @param outPathString The path or filename of the output file
  public FourBitImageWriter(String outPathString) {
    this(Paths.get(outPathString));
  }

  public void write(Floor floor) {
    throw new UnsupportedOperationException("TODO");
  }

  public void writeCropped(Floor floor) {
    write(floor.crop());
  }
}
