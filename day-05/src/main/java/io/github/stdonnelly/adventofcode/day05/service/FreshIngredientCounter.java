package io.github.stdonnelly.adventofcode.day05.service;

import io.github.stdonnelly.adventofcode.common.model.InclusiveRange;
import java.util.Collection;
import java.util.Set;

/// A service class that counts the number of fresh ingredients contained in a list of ingredient
/// IDs
///
/// An ingredient is fresh if it is contained in any of the `freshIngredientRanges`
public class FreshIngredientCounter {
  private final Set<InclusiveRange> freshIngredientRanges;

  /// Construct a new instance
  ///
  /// @param freshIngredientRanges A [Collection] of (inclusive) ranges of fresh ingredients.
  /// This constructor copies the collection unless it is an immutable [Set], so this class will not
  /// modify this collection or accept any modifications after this class is constructed.
  public FreshIngredientCounter(final Collection<InclusiveRange> freshIngredientRanges) {
    this.freshIngredientRanges = Set.copyOf(freshIngredientRanges);
  }

  /// Give the count of all of the ingredients that are considered fresh by
  // [isFresh][#isFresh(long)]
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
