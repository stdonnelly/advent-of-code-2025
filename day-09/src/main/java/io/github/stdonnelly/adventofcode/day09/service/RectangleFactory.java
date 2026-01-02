package io.github.stdonnelly.adventofcode.day09.service;

import io.github.stdonnelly.adventofcode.day09.model.Point2d;
import io.github.stdonnelly.adventofcode.day09.model.Rectangle;
import java.util.ArrayList;
import java.util.List;

/// A class that generates all [Rectangle]s that can be made with a given set of [Point2d]
public class RectangleFactory {
  private final List<Point2d> pointList;

  /// @param pointList The list of points to be used to generate the rectangles
  public RectangleFactory(List<Point2d> pointList) {
    this.pointList = pointList;
  }

  /// Get all rectangles represented by pairs of points in `pointList`
  public List<Rectangle> getRectangles() {
    List<Rectangle> rectangles = new ArrayList<>();
    for (int i = 0; i < pointList.size(); i++) {
      Point2d point1 = pointList.get(i);
      for (int j = i + 1; j < pointList.size(); j++) {
        rectangles.add(new Rectangle(point1, pointList.get(j)));
      }
    }
    return rectangles;
  }
}
