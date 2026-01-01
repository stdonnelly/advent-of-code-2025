package io.github.stdonnelly.adventofcode.day08.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/// Represents a modifiable set of circuits
///
/// The circuits can be merged
public class CircuitSet {
  private final Set<Set<Point3d>> circuits;

  /// Create an instance where each point is initially its singleton circuit
  ///
  /// @param points The initial set of points
  public CircuitSet(final Collection<Point3d> points) {
    circuits = points.stream().map(Set::of).collect(Collectors.toCollection(HashSet::new));
  }

  /// Merge the circuit containing `connection.point1` with the circuit containing
  // `connection.point2`
  public void mergeCircuits(final Point3dConnection connection) {
    // Get the points
    final Point3d point1 = connection.point1();
    final Point3d point2 = connection.point2();

    // Merge the sets containing point1 with the sets containing point2 if they are not already
    // merged

    // Start by finding the circuits that already contain the given point
    final Set<Point3d> circuitContainingPoint1 =
        circuits.stream().filter(circuit -> circuit.contains(point1)).findAny().orElseThrow();
    final Set<Point3d> circuitContainingPoint2 =
        circuits.stream().filter(circuit -> circuit.contains(point2)).findAny().orElseThrow();

    // If they are not the same circuit, merge them.
    // We don't need to do anything if they are already in the same circuit.
    if (circuitContainingPoint1 != circuitContainingPoint2) {
      Set<Point3d> newCircuit =
          Stream.concat(circuitContainingPoint1.stream(), circuitContainingPoint2.stream())
              .collect(Collectors.toUnmodifiableSet());

      // Sanity check to make sure the circuits were actually removed
      if (!circuits.remove(circuitContainingPoint1) || !circuits.remove(circuitContainingPoint2)) {
        throw new IllegalStateException("Failed to remove circuits from circuit set");
      }

      // Add the new one back
      circuits.add(newCircuit);
    }
  }

  /// The entire circuit set
  public Set<Set<Point3d>> getCircuits() {
    return circuits;
  }

  /// Get the largest `circuitCount` circuits, ordered largest-first
  ///
  /// @param circuitCount The maximum number of elements to return
  /// @return A List of circuits, ordered largest to smallest, with a size that is at most
  /// `circuitCount`
  public List<Set<Point3d>> getLargestCircuits(final int circuitCount) {
    return circuits.stream()
        .sorted(Comparator.<Set<Point3d>>comparingInt(Set::size).reversed())
        .limit(circuitCount)
        .toList();
  }

  /// @see Set#size()
  public int size() {
    return circuits.size();
  }
}
