package io.samjingwen.core;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
  N(0, 1),
  S(0, -1),
  E(1, 0),
  W(-1, 0),
  ;

  private static final Map<Direction, Direction> leftMap = new HashMap<>();
  private static final Map<Direction, Direction> rightMap = new HashMap<>();

  private final int dx;
  private final int dy;

  static {
    leftMap.put(N, W);
    leftMap.put(S, E);
    leftMap.put(E, N);
    leftMap.put(W, S);

    rightMap.put(N, E);
    rightMap.put(S, W);
    rightMap.put(E, S);
    rightMap.put(W, N);
  }

  Direction(int dx, int dy) {
    this.dx = dx;
    this.dy = dy;
  }

  public int getDx() {
    return dx;
  }

  public int getDy() {
    return dy;
  }

  public Direction rotateLeft() {
    return leftMap.get(this);
  }

  public Direction rotateRight() {
    return rightMap.get(this);
  }
}
