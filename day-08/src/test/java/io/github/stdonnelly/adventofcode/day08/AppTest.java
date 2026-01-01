package io.github.stdonnelly.adventofcode.day08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day08.loader.Point3dLoader;
import io.github.stdonnelly.adventofcode.day08.model.Point3d;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class AppTest {
  private static final String EXAMPLE_FILE_NAME = "example_input.txt";
  private static final int TEST_CONNECTION_COUNT = 10;

  /**
   * Test part 1 with the example input
   *
   * @throws IOException if the input loading fails
   */
  @Test
  void part1Test() throws IOException {
    final long EXPECTED = 40L;
    final InputLoader<Point3d> inputLoader = new Point3dLoader(EXAMPLE_FILE_NAME);
    final List<Point3d> input = inputLoader.load();
    assertEquals(EXPECTED, App.part1(input, TEST_CONNECTION_COUNT));
  }

  /**
   * Test part 2 with the example input
   *
   * @throws IOException if the input loading fails
   */
  @Test
  void part2Test() throws IOException {
    final long EXPECTED = 25272L;
    final InputLoader<Point3d> inputLoader = new Point3dLoader(EXAMPLE_FILE_NAME);
    final List<Point3d> input = inputLoader.load();
    assertEquals(EXPECTED, App.part2(input));
  }
}
