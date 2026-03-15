package io.github.stdonnelly.adventofcode.day11.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/// A cache that takes both the destination device name and the required intermediates
///
/// This is needed because the number of valid paths from a device may depend on the intermediates
/// that were already found.
public class PathCountCache {
  private Map<Key, Long> caches = new HashMap<>();

  /// Put a cache entry
  public void put(String destination, Set<NetworkDevice> requiredIntermediates, long pathCount) {
    caches.put(new Key(destination, requiredIntermediates), pathCount);
  }

  /// Get a cache entry
  public long get(String destination, Set<NetworkDevice> requiredIntermediates) {
    return caches.getOrDefault(new Key(destination, requiredIntermediates), -1L);
  }

  private static record Key(String destination, Set<NetworkDevice> requiredIntermediates) {}
}
