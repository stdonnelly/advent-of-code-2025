package io.github.stdonnelly.adventofcode.day10.loader;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day10.model.MachineDescription;

public class MachineDescriptionLoader extends InputLoader<MachineDescription> {
  public MachineDescriptionLoader(String inFileName) {
    super(inFileName);
  }

  @Override
  public MachineDescription parseOne(String input) {
    return MachineDescription.parse(input);
  }
}
