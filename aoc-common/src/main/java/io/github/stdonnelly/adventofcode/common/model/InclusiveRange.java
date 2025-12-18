package io.github.stdonnelly.adventofcode.common.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// An inclusive range of long integers
///
/// @param start The start of the range (inclusive)
/// @param end The end of the range (inclusive)
public record InclusiveRange(long start, long end) implements Comparable<InclusiveRange> {
  // A regex to match the range
  private static final Pattern PARSER_PATTERN = Pattern.compile("^(\\d+)-(\\d+)$");

  /// Parse an input object
  ///
  /// @param input The input string to parse
  /// @return The input after parsing
  /// @throws IllegalArgumentException If the input is not parsable
  public static InclusiveRange parse(String input) throws IllegalArgumentException {
    final Matcher matcher = PARSER_PATTERN.matcher(input);

    // Return an exception if the parsing fails.
    // This should cover any parsing error.
    // The rest of the statements in this method can throw runtime errors,
    // but should only do so if the regex was made incorrectly.
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Unable to parse input '" + input + "'");
    }

    final long start = Long.parseLong(matcher.group(1));
    final long end = Long.parseLong(matcher.group(2));

    return new InclusiveRange(start, end);
  }

  /// Check if the number is included in this range
  ///
  /// @param other The number to check
  /// @return `true` if the number is in this range (inclusive). `false` otherwise.
  public boolean contains(long other) {
    return this.start <= other && other <= this.end;
  }

  @Override
  public String toString() {
    return String.format("%d-%d", start, end);
  }

  /// Compare by `start`, then by `end`
  ///
  /// @since 0.5.1
  @Override
  public int compareTo(InclusiveRange other) {
    Objects.requireNonNull("Cannot compare InclusiveRange[" + this.toString() + "] to null");

    // Try comparing by start
    if (this.start() < other.start()) {
      return -1;
    } else if (this.start() > other.start()) {
      return 1;
    } else {
      // If the start is equal, try comparing by end
      if (this.end() < other.end()) {
        return -1;
      } else if (this.end() > other.end()) {
        return 1;
      } else {
        // If the end is equal, this is other
        return 0;
      }
    }
  }
}
