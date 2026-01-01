package io.github.stdonnelly.adventofcode.day09.loader;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day09.model.Point2d;

public class Point2dLoader extends InputLoader<Point2d> {
  public Point2dLoader(String inFileName) {
    super(inFileName);
  }

  @Override
  public Point2d parseOne(String input) {
    return Point2d.parse(input);
  }
}
