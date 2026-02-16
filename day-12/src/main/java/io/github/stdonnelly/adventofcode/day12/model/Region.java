package io.github.stdonnelly.adventofcode.day12.model;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// @param countPerShape The number of presents of each shape that are required
///
/// The index corresponds to the shape, and the value corresponds to the required number of that
/// shape.
public record Region(int width, int length, List<Integer> countPerShape) {
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
    final String countsString = matcher.group(3);
    final List<Integer> counts =
        Arrays.stream(countsString.split(" ")).map(Integer::valueOf).toList();

    return new Region(width, length, counts);
  }
}
