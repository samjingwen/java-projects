package io.samjingwen.core;

import java.util.Objects;

public class Car {

  private String name;
  private Position position;
  private Direction direction;

  public static Car newInstance(Car car) {
    return new Car(car.name, Position.newInstance(car.position), car.direction);
  }

  public Car(String name, Position position, Direction direction) {
    this.name = name;
    this.position = position;
    this.direction = direction;
  }

  public void move(Field field, Command command) {
    switch (command) {
      case F -> {
        int x = position.getX() + direction.getDx();
        int y = position.getY() + direction.getDy();

        if (x >= 0 && x < field.x() && y >= 0 && y < field.y()) {
          position.setX(x);
          position.setY(y);
        }
      }
      case L -> setDirection(this.direction.rotateLeft());
      case R -> setDirection(this.direction.rotateRight());
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Car car)) return false;

    return Objects.equals(name, car.name)
        && Objects.equals(position, car.position)
        && direction == car.direction;
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(name);
    result = 31 * result + Objects.hashCode(position);
    result = 31 * result + Objects.hashCode(direction);
    return result;
  }

  @Override
  public String toString() {
    return "Car{"
        + "name='"
        + name
        + '\''
        + ", position="
        + position
        + ", direction="
        + direction
        + '}';
  }
}
