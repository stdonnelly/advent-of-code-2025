package io.github.stdonnelly.adventofcode.day12.model;

import java.util.List;
import java.util.Objects;

public record ProblemInput(List<PresentShape> presentShapes, List<Region> regions) {
  public ProblemInput {
    Objects.requireNonNull(presentShapes);
    Objects.requireNonNull(regions);
  }
}
