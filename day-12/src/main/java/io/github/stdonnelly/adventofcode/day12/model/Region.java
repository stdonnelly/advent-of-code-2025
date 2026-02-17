package io.github.stdonnelly.adventofcode.day12.model;

import java.text.ParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/// @param countPerShape The number of presents of each shape that are required
///
/// The index corresponds to the shape, and the value corresponds to the required number of that
/// shape.
public record Region(int width, int length, int[] countPerShape) {
  private static final Pattern PARSE_PATTERN = Pattern.compile("^(\\d+)x(\\d+): ([\\d ]+)$");

  /// Parse a string into a new instance
  ///
  /// @param input The string to parse
  /// @return The [Region] represented by `input`
  /// @throws ParseException If the input cannot be parsed
  public static Region parse(String input) throws ParseException {
    Matcher matcher = PARSE_PATTERN.matcher(input);

    if (!matcher.matches()) {
      throw new ParseException("Input does not match regex /" + PARSE_PATTERN + "/", 0);
    }

    final int width = Integer.parseInt(matcher.group(1));
    final int length = Integer.parseInt(matcher.group(2));

    final String[] countsStrings = matcher.group(3).split(" ");
    int[] counts = new int[countsStrings.length];
    for (int i = 0; i < countsStrings.length; i++) {
      counts[i] = Integer.parseInt(countsStrings[i]);
    }

    return new Region(width, length, counts);
  }

  @Override
  public final boolean equals(Object other) {
    return other instanceof Region(int otherWidth, int otherLength, int[] otherCounts)
        && this.width == otherWidth
        && this.length == otherLength
        && Arrays.equals(this.countPerShape, otherCounts);
  }

  @Override
  public final int hashCode() {
    int hashCode = Arrays.hashCode(countPerShape);

    // Essentially extend the hashed array by applying the width and height the same way other
    // elements are added to a hash code.
    hashCode = hashCode * 31 + width;
    hashCode = hashCode * 31 + length;

    return hashCode;
  }

  @Override
  public final String toString() {
    final String countsString =
        Arrays.stream(countPerShape).mapToObj(Integer::toString).collect(Collectors.joining(" "));
    return String.format("%dx%d %s", width, length, countsString);
  }
}
