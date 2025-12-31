package io.github.stdonnelly.adventofcode.day08.loader;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day08.model.Point3d;

public class Point3dLoader extends InputLoader<Point3d> {
  public Point3dLoader(String inFileName) {
    super(inFileName);
  }

  @Override
  public Point3d parseOne(String input) {
    return Point3d.parse(input);
  }
}
