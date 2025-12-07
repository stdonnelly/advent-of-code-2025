package io.github.stdonnelly.adventofcode.day02;

/// Add together all *invalid* IDs in a given set of ranges
/// 
/// Definition of invalid: Exactly a sequence of digits that are repeated twice.
/// E.g. `123123` or `4545`, but not `1123123`.
/// 
/// Notes:
/// 
/// * Ranges are given as `start-end` *inclusively*. E.g. `11-22`.
/// * Leading zeros don't count. `0101` is **not** considered invalid.
public class App
{
    private static final String IN_FILE_NAME = "input.txt";

    public static void main( String[] args )
    {
        final var part1Answer = part1();
        System.out.println("Part 1: " + part1Answer);

        final var part2Answer = part2();
        System.out.println("Part 2: " + part2Answer);
    }

    static long part1() {
        return -1;
    }

    static long part2() {
        return -1;
    }
}
