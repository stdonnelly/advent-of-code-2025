package io.github.stdonnelly.adventofcode.day11.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/// A network of devices
public record Network(Map<String, NetworkDevice> devices) {
  public Network {
    Objects.requireNonNull(devices);
  }

  /// Construct a new instance from the list of DTOs
  public static Network of(List<NetworkDeviceDto> networkDeviceDtos) {
    return new Network(
            // Map.copyOf() to make the map unmodifiable
            Map.copyOf(
                networkDeviceDtos.stream()
                    // First, map to NetworkDevice
                    .map(NetworkDevice::fromDto)
                    // Then, collect in a HashMap
                    .collect(
                        HashMap::new,
                        (Map<String, NetworkDevice> map, NetworkDevice device) ->
                            map.put(device.getName(), device),
                        Map::putAll)))
        .buildDeviceTree();
  }

  /// Count paths from the device named `from` to the device named `to`
  ///
  /// If `intermediates` is present, paths will only be counted if the path contains all
  /// required intermediate devices. This will not require that the intermediates are contiguous
  /// or in any order.
  ///
  /// @param from The name of the starting point. Must correspond to a known device.
  /// @param to The name of the ending point. Does not necessarily need to correspond to a known
  /// device
  /// @param intermediates The names of the required intermediate devices
  /// @return The number of distinct paths between the given devices
  public long countPaths(final String from, final String to, String... intermediates) {
    // Null checks
    final NetworkDevice start =
        Objects.requireNonNull(
            devices.get(from),
            () -> String.format("Starting point '%s' not found in device map", from));
    if (Objects.isNull(intermediates)) {
      intermediates = new String[] {};
    }

    // Map network devices
    Set<NetworkDevice> intermediateDevices =
        Arrays.stream(intermediates).map(devices::get).collect(Collectors.toUnmodifiableSet());

    // Defer to NetworkDevice#countPaths(...)
    return start.countPaths(to, intermediateDevices);
  }

  private Network buildDeviceTree() {
    devices.values().forEach(device -> device.setOutputDevices(devices));
    return this;
  }
}
