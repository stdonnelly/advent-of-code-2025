package io.github.stdonnelly.adventofcode.day11.loader;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day11.model.NetworkDeviceDto;

public class NetworkDeviceDtoLoader extends InputLoader<NetworkDeviceDto> {
  public NetworkDeviceDtoLoader(String inFileName) {
    super(inFileName);
  }

  @Override
  public NetworkDeviceDto parseOne(String input) {
    return NetworkDeviceDto.parse(input);
  }
}
