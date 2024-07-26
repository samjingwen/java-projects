package io.samjingwen.util;

import java.util.Objects;

public final class Pair<T, U> {
  private final T left;
  private final U right;

  private Pair(T left, U right) {
    this.left = left;
    this.right = right;
  }

  public static <T, U> Pair<T, U> of(T left, U right) {
    return new Pair<>(left, right);
  }

  public T getLeft() {
    return left;
  }

  public U getRight() {
    return right;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Pair<?, ?> pair)) return false;

    return Objects.equals(left, pair.left) && Objects.equals(right, pair.right);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(left);
    result = 31 * result + Objects.hashCode(right);
    return result;
  }

  @Override
  public String toString() {
    return "Pair{" + "left=" + left + ", right=" + right + '}';
  }
}
