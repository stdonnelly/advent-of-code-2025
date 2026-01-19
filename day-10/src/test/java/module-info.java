// This is needed because junit can't find MachineProvider.java without it.
module io.github.stdonnelly.adventofcode.day10 {
  exports io.github.stdonnelly.adventofcode.day10;
  exports io.github.stdonnelly.adventofcode.day10.service;
  exports io.github.stdonnelly.adventofcode.day10.testutil;

  opens io.github.stdonnelly.adventofcode.day10;
  opens io.github.stdonnelly.adventofcode.day10.service;
  opens io.github.stdonnelly.adventofcode.day10.testutil;

  requires io.github.stdonnelly.adventofcode.common;
  // Ignore VSCode error, this module is accessible.
  requires org.junit.jupiter;
}
