package io.github.stdonnelly.adventofcode.day12.service;

import io.github.stdonnelly.adventofcode.day12.model.PresentShape;
import io.github.stdonnelly.adventofcode.day12.model.Region;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/// An initial filter that checks if the presents could all fit, assuming perfect packing density
///
/// This will give an overly optimistic result, since the packing density is likely not perfect.
public class PresentSizeFilterOptimistic implements Predicate<Region> {
  private final List<PresentShape> shapes;

  /// Constructor
  ///
  /// @param shapes The list of shapes to consider
  public PresentSizeFilterOptimistic(List<PresentShape> shapes) {
    this.shapes = shapes;
  }

  /// Check if all presents could fit with perfect packing density
  ///
  /// @return `true` if the total space taken up by the presents is less than or equal to the area
  /// of the region; `false` otherwise.
  @Override
  public boolean test(final Region region) {
    final int arrayLength = shapes.size();
    if (region.countPerShape().length != arrayLength) {
      throw new IndexOutOfBoundsException(
          "The number of known shapes must match the length of Region.countPerShape");
    }

    final int totalShapeSize =
        IntStream.range(0, arrayLength)
            .map(i -> region.countPerShape()[i] * shapes.get(i).size())
            .sum();

    return totalShapeSize <= region.area();
  }
}
