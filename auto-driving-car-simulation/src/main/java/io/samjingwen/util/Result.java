package io.samjingwen.util;

import io.samjingwen.validation.Error;

import java.util.Set;

public interface Result<T> {

  T get();

  Set<Error> getErrors();

  static <T> Result<T> success(T value) {
    return new Success<>(value);
  }

  static <T> Result<T> failure(Set<Error> errors) {
    return new Failure<>(errors);
  }

  default boolean isSuccess() {
    return getErrors().isEmpty();
  }

  default boolean isFailure() {
    return !isSuccess();
  }
}
