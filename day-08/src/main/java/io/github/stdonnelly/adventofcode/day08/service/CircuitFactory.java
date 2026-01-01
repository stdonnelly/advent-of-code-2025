package io.github.stdonnelly.adventofcode.day08.service;

import io.github.stdonnelly.adventofcode.day08.model.CircuitSet;
import io.github.stdonnelly.adventofcode.day08.model.Point3d;
import io.github.stdonnelly.adventofcode.day08.model.Point3dConnection;
import java.util.List;

/// Create circuits based on shortest-distance connections
public class CircuitFactory {
  private final List<Point3d> points;
  private final ConnectionFinder connectionFinder;

  public CircuitFactory(final List<Point3d> points) {
    this.points = points;
    this.connectionFinder = new ConnectionFinder(points);
  }

  /// Get the list of circuits based on the shortest connections
  ///
  /// @param connectionCount The number of connections to make
  /// @return The final state of the circuit set after `connectionCount` shortest connections
  public CircuitSet getCircuits(int connectionCount) {
    // Get the shortest connections
    final List<Point3dConnection> connections =
        connectionFinder.getShortestConnections(connectionCount);

    // Generate the initial set of circuits
    final CircuitSet circuitSet = new CircuitSet(points);

    connections.forEach(circuitSet::mergeCircuits);

    return circuitSet;
  }

  /// Finds the first connection that completes the tree of points if connections are made shortest
  /// first
  ///
  /// @throws IllegalStateException If the connections cannot form a circuit. This should be
  /// impossible because the [ConnectionFinder] should contain the connections in the complete
  /// graph.
  public Point3dConnection findLongestConnectionNeededForTree() {
    // Get all of the connections in order of shortest-first
    final List<Point3dConnection> connections = connectionFinder.getAllConnections();

    // Generate the initial set of circuits
    final CircuitSet circuitSet = new CircuitSet(points);

    // Merge until the circuit set it just one big circuit
    for (Point3dConnection connection : connections) {
      circuitSet.mergeCircuits(connection);

      if (circuitSet.size() == 1) {
        return connection;
      }
    }

    throw new IllegalStateException("Unable to generate spanning tree");
  }
}
