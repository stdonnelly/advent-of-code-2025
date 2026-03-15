package io.github.stdonnelly.adventofcode.day12.service;

import io.github.stdonnelly.adventofcode.day12.model.Region;
import java.util.Arrays;
import java.util.function.Predicate;

/// An initial filter that checks if the presents could all fit.
///
/// This assumes that each present is a 3x3 square. This will give an overly pessimistic result,
/// since the packing density is likely better than that.
public class PresentSizeFilterPessimistic implements Predicate<Region> {
  /// Check if all presents could fit with the worst packing density
  ///
  /// @return `true` if the total space taken up by 3x3 squares is less than or equal to the area
  /// of the region; `false` otherwise.
  @Override
  public boolean test(final Region region) {
    final int shapeCount = Arrays.stream(region.countPerShape()).sum();

    // Divide width and length by 3 and truncate to count the number of possible squares
    final int width = region.width() / 3;
    final int length = region.length() / 3;

    return shapeCount <= (width * length);
  }
}
