package io.github.stdonnelly.adventofcode.day11.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/// A device on the network
///
/// This differs from [NetworkDeviceDto] because this class stores references to other devices
/// instead of just the names.
public class NetworkDevice {

  // #region Fields
  private final String name;
  private List<String> outputDeviceNames;
  private List<NetworkDevice> outputDevices;

  // #endregion

  // #region Constructors and factory methods

  /// Constructor
  ///
  /// @param name The name of this device
  public NetworkDevice(String name) {
    this.name = name;
  }

  public static NetworkDevice fromDto(NetworkDeviceDto dto) {
    return new NetworkDevice(dto.name()).setOutputDeviceNames(dto.outputDevices());
  }

  // #endregion

  // #region Public methods

  /// Check if one of the direct outputs is `output`
  public boolean hasOutput(String output) {
    return outputDeviceNames.contains(output);
  }

  /// Count the number of paths to the device named `to`
  ///
  /// If `intermediates` is present, paths will only be counted if the path contains all
  /// required intermediate devices. This will not require that the intermediates are contiguous
  /// or in any order.
  ///
  /// @param to The name of the device to count paths to
  /// @param intermediates The required intermediate devices
  /// @return The number of paths from `this` to `to`
  /// @throws java.lang.StackOverflowError If this device is part of a cyclic graph.
  public long countPaths(final String to, Set<NetworkDevice> intermediates) {
    long pathCount = 0L;

    // If this is one of the required intermediates, temporarily remove from the intermediate set
    if (intermediates.contains(this)) {
      // Use a stream with a filter to avoid modifying the original object
      intermediates =
          intermediates.stream()
              .filter(Predicate.not(this::equals))
              .collect(Collectors.toUnmodifiableSet());
    }

    // +1 if `to` is a direct descendant and there are no missing intermediates
    if (this.hasOutput(to)) {
      pathCount++;
    }

    // Recursive step
    for (NetworkDevice child : outputDevices) {
      pathCount += child.countPaths(to, intermediates);
    }

    return pathCount;
  }

  // #endregion

  // #region Accessors

  /// @return The names of the output devices
  public List<String> getOutputDeviceNames() {
    return outputDeviceNames;
  }

  /// @param The names of the output devices
  /// @return This instance
  public NetworkDevice setOutputDeviceNames(final List<String> outputDeviceNames) {
    this.outputDeviceNames = outputDeviceNames;
    return this;
  }

  /// Set the `outputDeviceNames` field based on the values in `outputDevices`
  ///
  /// If `outputDevices` is `null`, then `outputDeviceNames` will be set to `null`.
  ///
  /// @return This instance
  public NetworkDevice setOutputDeviceNames() {
    if (Objects.isNull(outputDevices)) {
      outputDeviceNames = null;
    } else {
      outputDeviceNames = outputDevices.stream().map(NetworkDevice::getName).toList();
    }
    return this;
  }

  /// @return The output devices
  public List<NetworkDevice> getOutputDevices() {
    return outputDevices;
  }

  /// @param The output devices
  /// @return This instance
  public NetworkDevice setOutputDevices(final List<NetworkDevice> outputDevices) {
    this.outputDevices = outputDevices;
    return this;
  }

  /// Set the `outputDevices` field based on the values in `outputDeviceNames`
  ///
  /// If `outputDeviceNames` is `null`, then `outputDevices` will be set to `null`. If `deviceMap`
  /// is `null` and `outputDeviceNames` is not `null`, a [NullPointerException] will be thrown.
  ///
  /// @param deviceMap A Map of the known device names to the referenced device
  /// @return This instance
  public NetworkDevice setOutputDevices(final Map<String, NetworkDevice> deviceMap) {
    if (Objects.isNull(outputDeviceNames)) {
      outputDevices = null;
    } else {
      outputDevices =
          outputDeviceNames.stream().map(deviceMap::get).filter(Objects::nonNull).toList();
    }
    return this;
  }

  /// Get the name of this device
  ///
  /// It may be necessary to know the name of this device when traversing this as a graph
  public String getName() {
    return name;
  }

  // #endregion
}
