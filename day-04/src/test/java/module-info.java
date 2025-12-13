module io.github.stdonnelly.adventofcode.day04.test {
    requires org.junit.jupiter;
    requires io.github.stdonnelly.adventofcode.day04;

    exports io.github.stdonnelly.adventofcode.day04.test;

    opens io.github.stdonnelly.adventofcode.day04.test to org.junit.platform.commons;
}
