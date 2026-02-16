package io.github.stdonnelly.adventofcode.day12.loader;

import io.github.stdonnelly.adventofcode.common.loader.InputLoader;
import io.github.stdonnelly.adventofcode.day12.model.Item;

public class ItemLoader extends InputLoader<Item> {
  public ItemLoader(String inFileName) {
    super(inFileName);
  }

  @Override
  public Item parseOne(String input) {
    return Item.parse(input);
  }
}
