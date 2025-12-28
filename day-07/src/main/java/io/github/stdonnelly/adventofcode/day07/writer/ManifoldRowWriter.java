package io.github.stdonnelly.adventofcode.day07.writer;

import io.github.stdonnelly.adventofcode.day07.model.ManifoldRow;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/// Write the manifold rows to the output file
public class ManifoldRowWriter {
  private final Path outPath;

  public ManifoldRowWriter(Path outPath) {
    this.outPath = outPath;
  }

  /// Write the manifold row list to the file specified in `outPath`
  public void write(List<ManifoldRow> manifoldRowList) throws IOException {
    try (BufferedWriter writer = Files.newBufferedWriter(outPath)) {
      for (ManifoldRow manifoldRow : manifoldRowList) {
        writer.write(manifoldRow.toString() + "\n");
      }
    }
  }
}
