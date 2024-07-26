package io.samjingwen.core;

public class Position {

  private int x;
  private int y;

  public static Position newInstance(Position position) {
    return new Position(position.getX(), position.getY());
  }

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Position position)) return false;

    return x == position.x && y == position.y;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }

  @Override
  public String toString() {
    return "Position{" + "x=" + x + ", y=" + y + '}';
  }
}
