package io.github.stdonnelly.adventofcode.day12.model;

import java.util.List;
import java.util.Objects;

public record ProblemInput(List<PresentShape> presentShapes, List<Region> regions) {
  public ProblemInput {
    Objects.requireNonNull(presentShapes);
    Objects.requireNonNull(regions);
  }

  @Override
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < presentShapes.size(); i++) {
      stringBuilder.append(i);
      stringBuilder.append(":\n");
      stringBuilder.append(presentShapes.get(i));
      stringBuilder.append("\n\n");
    }

    for (Region region : regions) {
      stringBuilder.append(region);
      stringBuilder.append('\n');
    }
    return stringBuilder.toString();
  }
}
