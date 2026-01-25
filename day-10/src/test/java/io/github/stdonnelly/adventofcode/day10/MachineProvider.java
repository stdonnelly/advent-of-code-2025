package io.github.stdonnelly.adventofcode.day10;

import io.github.stdonnelly.adventofcode.day10.model.MachineDescription;
import io.github.stdonnelly.adventofcode.day10.model.Matrix;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.ParameterDeclarations;

@SuppressWarnings("exports")
public class MachineProvider implements ArgumentsProvider {
  private final List<Object[]> arguments;

  public MachineProvider() {
    arguments =
        List.of(
            new Object[] {
              MachineDescription.parse("[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}"),
              10,
              new Matrix(
                  new Matrix.Row(0, 0, 0, 0, 1, 1, 3),
                  new Matrix.Row(0, 1, 0, 0, 0, 1, 5),
                  new Matrix.Row(0, 0, 1, 1, 1, 0, 4),
                  new Matrix.Row(1, 1, 0, 1, 0, 0, 7))
            },
            new Object[] {
              MachineDescription.parse(
                  "[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}"),
              12,
              new Matrix(
                  new Matrix.Row(1, 0, 1, 1, 0, 7),
                  new Matrix.Row(0, 0, 0, 1, 1, 5),
                  new Matrix.Row(1, 1, 0, 1, 1, 12),
                  new Matrix.Row(1, 1, 0, 0, 1, 7),
                  new Matrix.Row(1, 0, 1, 0, 1, 2))
            },
            new Object[] {
              MachineDescription.parse(
                  "[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}"),
              11,
              new Matrix(
                  new Matrix.Row(1, 1, 1, 0, 10),
                  new Matrix.Row(1, 0, 1, 1, 11),
                  new Matrix.Row(1, 0, 1, 1, 11),
                  new Matrix.Row(1, 1, 0, 0, 5),
                  new Matrix.Row(1, 1, 1, 0, 10),
                  new Matrix.Row(0, 0, 1, 0, 5))
            });
  }

  @Override
  public Stream<? extends Arguments> provideArguments(
      ParameterDeclarations parameters, ExtensionContext context) throws Exception {
    return arguments.stream().map(Arguments::of);
  }
}
