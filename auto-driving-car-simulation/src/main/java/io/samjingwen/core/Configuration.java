package io.samjingwen.core;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

  private final Field field;
  private final List<Car> cars;
  private final List<List<Command>> carCommands;

  public Configuration(Field field) {
    this.field = field;
    this.cars = new ArrayList<>();
    this.carCommands = new ArrayList<>();
  }

  public Configuration(Field field, List<Car> cars, List<List<Command>> carCommands) {
    this.field = field;
    this.cars = cars;
    this.carCommands = carCommands;
  }

  public void add(Car car, List<Command> commands) {
    cars.add(car);
    carCommands.add(commands);
  }

  public Field getField() {
    return field;
  }

  public List<Car> getCars() {
    return cars.stream().map(Car::newInstance).toList();
  }

  public List<List<Command>> getCarCommands() {
    return carCommands;
  }
}
