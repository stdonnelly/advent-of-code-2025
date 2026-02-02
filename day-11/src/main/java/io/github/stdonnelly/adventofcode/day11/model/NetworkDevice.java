package io.github.stdonnelly.adventofcode.day11.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/// A device on the network
///
/// This differs from [NetworkDeviceDto] because this class stores references to other devices
/// instead of just the names.
public class NetworkDevice {

  // #region Fields
  private List<String> outputDeviceNames;
  private List<NetworkDevice> outputDevices;
  private final String name;

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
