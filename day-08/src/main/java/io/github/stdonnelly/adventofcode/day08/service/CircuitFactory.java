package io.github.stdonnelly.adventofcode.day08.service;

import io.github.stdonnelly.adventofcode.day08.model.Point3d;
import io.github.stdonnelly.adventofcode.day08.model.Point3dConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
  /// @return A set of sets containing circuits
  public Set<Set<Point3d>> getCircuits(int connectionCount) {
    // Get the shortest connections
    final List<Point3dConnection> connections =
        connectionFinder.getShortestConnections(connectionCount);

    // Create a modifiable set of sets
    // The inner sets are not modifiable because
    final Set<Set<Point3d>> circuitSet =
        points.stream().map(Set::of).collect(Collectors.toCollection(HashSet::new));

    // Generate the circuits
    for (final Point3dConnection connection : connections) {
      // Get the points
      final Point3d point1 = connection.point1();
      final Point3d point2 = connection.point2();

      // Merge the sets containing point1 with the sets containing point2 if they are not already
      // merged

      // Start by finding the circuits that already contain the given point
      final Set<Point3d> circuitContainingPoint1 =
          circuitSet.stream().filter(circuit -> circuit.contains(point1)).findAny().orElseThrow();
      final Set<Point3d> circuitContainingPoint2 =
          circuitSet.stream().filter(circuit -> circuit.contains(point2)).findAny().orElseThrow();

      // If they are not the same circuit, merge them.
      // We don't need to do anything if they are already in the same circuit.
      if (circuitContainingPoint1 != circuitContainingPoint2) {
        Set<Point3d> newCircuit =
            Stream.concat(circuitContainingPoint1.stream(), circuitContainingPoint2.stream())
                .collect(Collectors.toUnmodifiableSet());

        // Sanity check to make sure the circuits were actually removed
        if (!circuitSet.remove(circuitContainingPoint1)
            || !circuitSet.remove(circuitContainingPoint2)) {
          throw new IllegalStateException("Failed to remove circuits from circuit set");
        }

        // Add the new one back
        circuitSet.add(newCircuit);
      }
    }

    return circuitSet;
  }
}
