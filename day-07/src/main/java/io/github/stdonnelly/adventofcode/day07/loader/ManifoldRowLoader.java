package io.github.stdonnelly.adventofcode.day07.loader;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day07.model.ManifoldRow;

public class ManifoldRowLoader extends InputLoader<ManifoldRow> {
  public ManifoldRowLoader(String inFileName) {
    super(inFileName);
  }

  @Override
  public ManifoldRow parseOne(String input) {
    return ManifoldRow.parse(input);
  }
}
