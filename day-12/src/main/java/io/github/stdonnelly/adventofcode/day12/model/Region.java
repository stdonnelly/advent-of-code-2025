package io.github.stdonnelly.adventofcode.day12.model;

import java.util.List;

/// @param countPerShape The number of presents of each shape that are required
///
/// The index corresponds to the shape, and the value corresponds to the required number of that
/// shape.
public record Region(int width, int length, List<Integer> countPerShape) {}
