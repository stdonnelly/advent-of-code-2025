package io.github.stdonnelly.adventofcode.day08.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/// Tests for the methods of [Point3d]
///
/// The data for the magnitude tests are based on examples in
/// [Pythagorean Quadruple](https://mathworld.wolfram.com/PythagoreanQuadruple.html)
class Point3dTest {
  @Test
  void testParse() {
    assertEquals(new Point3d(1, 2, 3), Point3d.parse("1,2,3"));
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ';',
      textBlock =
          """
          1,2,2;3
          2,3,6;7
          4,4,7;9
          """)
  void testGetMagnitude(String pointString, double expected) {
    assertEquals(expected, Point3d.parse(pointString).getMagnitude());
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ';',
      textBlock =
          """
          1,2,3;1,2,3;0,0,0
          1,2,3;2,4,6;1,2,3
          21,14,19;37,44,14;16,30,-5
          """)
  void testGetDifferenceTo(String point1String, String point2String, String expectedDifference) {
    assertEquals(
        Point3d.parse(expectedDifference),
        Point3d.parse(point1String).differenceTo(Point3d.parse(point2String)));
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ';',
      textBlock =
          """
          1,2,2;0,0,0;3
          1,-2,3;3,-5,9;7
          """)
  void testGetDistanceTo(String point1String, String point2String, double expected) {
    assertEquals(expected, Point3d.parse(point1String).distanceTo(Point3d.parse(point2String)));
  }
}
