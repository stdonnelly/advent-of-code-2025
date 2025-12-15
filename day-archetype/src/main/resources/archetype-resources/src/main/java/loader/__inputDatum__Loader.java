package ${package}.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import ${package}.model.${inputDatum};
import io.github.stdonnelly.adventofcode.common.loader.InputLoader;

public class ${inputDatum}Loader extends InputLoader<${inputDatum}> {
  public ${inputDatum}Loader(String inFileName) {
    super(inFileName);
  }

  @Override
  public ${inputDatum} parseOne(String input) {
    return ${inputDatum}.parse(input);
  }
}
