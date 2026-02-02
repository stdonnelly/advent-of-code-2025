package io.github.stdonnelly.adventofcode.day11.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/// Represents a network device in a way that can easily be parsed from the input data
///
/// This is separated from [NetworkDevice] because the construction of that object requires some
/// complex logic
public record NetworkDeviceDto(String name, List<String> outputDevices) {
  /// Parse an input object
  ///
  /// @param input The input string to parse
  /// @return The input after parsing
  /// @throws IllegalArgumentException If the input is not parsable
  public static NetworkDeviceDto parse(String input) throws IllegalArgumentException {
    final String[] nameAndOutputs = input.split(": ");
    if (nameAndOutputs.length != 2) {
      throw new IllegalArgumentException(
          "Unable to parse input. There must be exactly one colon in each line");
    }

    return new NetworkDeviceDto(nameAndOutputs[0], List.of(nameAndOutputs[1].split(" ")));
  }

  @Override
  public final String toString() {
    return Stream.concat(Stream.of(name + ":"), outputDevices.stream())
        .collect(Collectors.joining(" "));
  }
}
