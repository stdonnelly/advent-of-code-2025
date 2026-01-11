package io.github.stdonnelly.adventofcode.day09.service;

import io.github.stdonnelly.adventofcode.day09.model.Floor;
import io.github.stdonnelly.adventofcode.day09.model.Point2d;
import io.github.stdonnelly.adventofcode.day09.model.Rectangle;
import java.util.List;

public class ColoredRectangleFactory extends RectangleFactory {
  private final Floor floor;

  public ColoredRectangleFactory(List<Point2d> pointList) {
    super(pointList);

    floor = getFloorWithinBounds(pointList);
    floor.drawFilledShape(pointList);
  }

  @Override
  public List<Rectangle> getRectangles() {
    return super.getRectangles().stream().filter(this::isRectangleValid).toList();
  }

  /// Check if the rectangle is completely filled with red or green
  public boolean isRectangleValid(Rectangle rectangle) {
    return true;
  }

  /// Get a floor that can contain the points list
  private static Floor getFloorWithinBounds(List<Point2d> pointsList) {
    long maxX = 0;
    long maxY = 0;

    for (int i = 0; i < pointsList.size(); i++) {
      if (pointsList.get(i).x() + 1 > maxX) {
        maxX = pointsList.get(i).x() + 1;
      }
      if (pointsList.get(i).y() + 1 > maxY) {
        maxY = pointsList.get(i).y() + 1;
      }
    }

    return Floor.ofSize(Math.toIntExact(maxX), Math.toIntExact(maxY));
  }
}
