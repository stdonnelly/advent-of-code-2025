package io.github.stdonnelly.adventofcode.day11;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day11.loader.NetworkDeviceDtoLoader;
import io.github.stdonnelly.adventofcode.day11.model.Network;
import io.github.stdonnelly.adventofcode.day11.model.NetworkDeviceDto;
import java.io.IOException;
import java.util.List;

/** Day 11 solver */
public class App {
  private static final String IN_FILE_NAME = "input.txt";

  public static void main(String[] args) {
    final InputLoader<NetworkDeviceDto> inputLoader = new NetworkDeviceDtoLoader(IN_FILE_NAME);

    try {
      final List<NetworkDeviceDto> input = inputLoader.load();

      final var part1Answer = part1(input);
      System.out.println("Part 1: " + part1Answer);

      final var part2Answer = part2(input);
      System.out.println("Part 2: " + part2Answer);
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  static long part1(final List<NetworkDeviceDto> input) {
    final Network network = Network.of(input);
    return network.countPaths("you", "out");
  }

  static long part2(final List<NetworkDeviceDto> input) {
    return -1;
  }
}
