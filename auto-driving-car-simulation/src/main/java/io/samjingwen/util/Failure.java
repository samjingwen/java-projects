package io.samjingwen.util;

import io.samjingwen.validation.Error;

import java.util.Set;

public record Failure<T>(Set<Error> errors) implements Result<T> {

  @Override
  public T get() {
      throw new RuntimeException("Invalid invocation");
  }

  @Override
  public Set<Error> getErrors() {
    return this.errors;
  }
}
