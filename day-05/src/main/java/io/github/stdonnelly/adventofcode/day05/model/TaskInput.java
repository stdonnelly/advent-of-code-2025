package io.github.stdonnelly.adventofcode.day05.model;

import io.github.stdonnelly.adventofcode.common.model.InclusiveRange;
import java.util.List;

/// The entire task input, rather than a single row.
///
/// @param freshIngredientRanges A List of fresh ingredient ID ranges
/// @param availableIngredients A List of available ingredient IDs
public record TaskInput(
    List<InclusiveRange> freshIngredientRanges, List<Long> availableIngredients) {}
