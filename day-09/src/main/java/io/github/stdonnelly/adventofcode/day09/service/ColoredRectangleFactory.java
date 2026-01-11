package io.github.stdonnelly.adventofcode.day09.service;

import io.github.stdonnelly.adventofcode.day09.model.Line;
import io.github.stdonnelly.adventofcode.day09.model.Point2d;
import io.github.stdonnelly.adventofcode.day09.model.Rectangle;
import java.util.List;

public class ColoredRectangleFactory extends RectangleFactory {
  private final List<Line> lineList;

  public ColoredRectangleFactory(List<Point2d> pointList) {
    super(pointList);

    this.lineList = Line.ofPoints(pointList);
  }

  @Override
  public List<Rectangle> getRectangles() {
    return super.getRectangles().stream().filter(this::isRectangleValid).toList();
  }

  /// Check if the rectangle is completely filled with red or green
  public boolean isRectangleValid(Rectangle rectangle) {
    return !rectangle.intersectsAny(lineList);
  }
}
