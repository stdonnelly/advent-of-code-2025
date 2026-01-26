package io.github.stdonnelly.adventofcode.day10.error;

/// Represents that an integer cannot be divided by a number that is not a multiple
public class NonIntegerDivisionException extends Exception {
  public NonIntegerDivisionException() {}

  public NonIntegerDivisionException(String message) {
    super(message);
  }

  public NonIntegerDivisionException(Throwable cause) {
    super(cause);
  }

  public NonIntegerDivisionException(String message, Throwable cause) {
    super(message, cause);
  }

  public NonIntegerDivisionException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
