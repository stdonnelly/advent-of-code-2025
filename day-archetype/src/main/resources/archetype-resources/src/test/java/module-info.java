module ${package}.test {
    requires org.junit.jupiter;
    requires ${package};

    exports ${package}.test;

    opens ${package}.test to org.junit.platform.commons;
}
