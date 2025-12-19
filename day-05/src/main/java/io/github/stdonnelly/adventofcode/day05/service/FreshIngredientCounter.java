package io.github.stdonnelly.adventofcode.day05.service;

import io.github.stdonnelly.adventofcode.common.model.InclusiveRange;
import io.github.stdonnelly.adventofcode.common.service.InclusiveRangeMergingGatherer;
import java.util.Collection;
import java.util.List;

/// A service class that counts the number of fresh ingredients contained in a list of ingredient
/// IDs
///
/// An ingredient is fresh if it is contained in any of the `freshIngredientRanges`
public class FreshIngredientCounter {
  private final List<InclusiveRange> freshIngredientRanges;

  /// Construct a new instance
  ///
  /// @param freshIngredientRanges A [Collection] of (inclusive) ranges of fresh ingredients.
  /// This constructor copies the collection unless it is an immutable [Set], so this class will not
  /// modify this collection or accept any modifications after this class is constructed.
  public FreshIngredientCounter(final Collection<InclusiveRange> freshIngredientRanges) {
    this.freshIngredientRanges =
        freshIngredientRanges.stream()
            // For #countAllFresh(), sort and gather the ranges so there are no overlaps
            .sorted()
            .gather(new InclusiveRangeMergingGatherer())
            .toList();
  }

  /// Counts *all* IDs that are included in at least one of the ranges in `freshIngredientRanges`
  public long countAllFresh() {
    return freshIngredientRanges.stream()
        // Get the count of items in each range.
        // This is the difference +1 because the range is inclusive
        .mapToLong(
            freshIngredientRange -> freshIngredientRange.end() - freshIngredientRange.start() + 1)
        .sum();
  }

  /// Give the count of all provided ingredients that are considered fresh by
  /// [isFresh][#isFresh(long)]
  ///
  /// @param allIngredients The ingredients to check for freshness
  public long freshCount(Collection<Long> allIngredients) {
    return allIngredients.stream().filter(this::isFresh).count();
  }

  /// Check if the given ingredient is contained in any of the fresh ingredient ranges
  ///
  /// @param ingredient The ingredient ID to check for freshness
  public boolean isFresh(final long ingredient) {
    return freshIngredientRanges.parallelStream().anyMatch(range -> range.contains(ingredient));
  }
}
