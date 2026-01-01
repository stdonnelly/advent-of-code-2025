package io.github.stdonnelly.adventofcode.day08.service;

import io.github.stdonnelly.adventofcode.day08.model.Point3d;
import io.github.stdonnelly.adventofcode.day08.model.Point3dConnection;
import java.util.ArrayList;
import java.util.List;

/// Service class to find the shortest connections that can be made
public class ConnectionFinder {
  private final List<Point3d> points;
  private final List<Point3dConnection> connections;

  public ConnectionFinder(List<Point3d> points) {
    this.points = List.copyOf(points);
    this.connections = buildConnections(this.points);
  }

  /// Get the shortest `count` connections
  ///
  /// @param count The number of connections to return. If this is less than the number of
  /// connections, all connections will be returned.
  public List<Point3dConnection> getShortestConnections(final int count) {
    if (count >= connections.size()) {
      return connections;
    } else {
      return connections.subList(0, count);
    }
  }

  /// Build the connections sorted list
  private static List<Point3dConnection> buildConnections(final List<Point3d> points) {
    // Create an array list first. This will be saved as an unmodifiable list later
    List<Point3dConnection> mutableConnections = new ArrayList<>();

    // Get each set of points
    for (int i = 0; i < points.size(); i++) {
      for (int j = i + 1; j < points.size(); j++) {
        mutableConnections.add(new Point3dConnection(points.get(i), points.get(j)));
      }
    }

    // Sort and return
    mutableConnections.sort(Point3dConnection::compareTo);
    return List.copyOf(mutableConnections);
  }
}
