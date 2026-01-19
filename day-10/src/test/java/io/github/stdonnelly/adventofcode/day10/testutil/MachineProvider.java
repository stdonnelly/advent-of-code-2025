package io.github.stdonnelly.adventofcode.day10.testutil;

import io.github.stdonnelly.adventofcode.day10.model.MachineDescription;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.ParameterDeclarations;

public class MachineProvider implements ArgumentsProvider {
  private final List<Object[]> arguments;

  public MachineProvider() {
    arguments =
        List.of(
            new Object[] {
              MachineDescription.parse("[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}"), 10
            },
            new Object[] {
              MachineDescription.parse(
                  "[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}"),
              12
            },
            new Object[] {
              MachineDescription.parse(
                  "[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}"),
              11
            });
  }

  @Override
  public Stream<? extends Arguments> provideArguments(
      ParameterDeclarations parameters, ExtensionContext context) throws Exception {
    return arguments.stream().map(Arguments::of);
  }
}
