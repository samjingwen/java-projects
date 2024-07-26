package io.samjingwen.util;

import static java.util.Collections.emptySet;

import io.samjingwen.validation.Error;
import java.util.Set;

public record Success<T>(T value) implements Result<T> {

  @Override
  public T get() {
    return this.value;
  }

  @Override
  public Set<Error> getErrors() {
    return emptySet();
  }
}
