module io.github.stdonnelly.adventofcode.day03.test {
    requires io.github.stdonnelly.adventofcode.day03;

    requires org.junit.jupiter;

    opens io.github.stdonnelly.adventofcode.day03.test to org.junit.platform.commons;
    opens io.github.stdonnelly.adventofcode.day03.test.service to org.junit.platform.commons;
}
