package io.samjingwen.core;

import java.util.*;

public class Simulation {

  private final Field field;
  private final List<Car> cars;
  private final List<List<Command>> commandsOfCars;

  private final Map<Integer, Map<Position, List<Car>>> crashedTable = new HashMap<>();
  private final Map<Car, Integer> crashedCarsToStep = new HashMap<>();

  private boolean hasStarted = false;

  public Simulation(Configuration configuration) {
    this.field = configuration.getField();
    this.cars = configuration.getCars();
    this.commandsOfCars = configuration.getCarCommands();
  }

  public void start() {
    if (hasStarted) {
      return;
    }

    int step = 0;

    while (true) {
      Map<Position, List<Car>> positionToCars = new HashMap<>();
      for (Car car : cars) {
        positionToCars.putIfAbsent(car.getPosition(), new ArrayList<>());
        positionToCars.get(car.getPosition()).add(car);
      }

      for (Map.Entry<Position, List<Car>> entry : positionToCars.entrySet()) {
        Position position = entry.getKey();
        List<Car> cars = entry.getValue();
        if (cars.size() <= 1) {
          continue;
        }

        boolean shouldUpdate = false;
        for (Car car : cars) {
          if (!crashedCarsToStep.containsKey(car)) {
            shouldUpdate = true;
            crashedCarsToStep.put(car, step);
          }
        }

        if (shouldUpdate) {
          crashedTable.putIfAbsent(step, new HashMap<>());
          crashedTable.get(step).put(position, cars);
        }
      }

      boolean hasRemainingCommands = false;

      for (int i = 0; i < cars.size(); i++) {
        Car car = cars.get(i);
        List<Command> commands = commandsOfCars.get(i);

        if (crashedCarsToStep.containsKey(car)) {
          continue;
        }

        if (step < commands.size()) {
          hasRemainingCommands = true;

          Command command = commands.get(step);
          car.move(field, command);
        }
      }

      if (!hasRemainingCommands) {
        break;
      }

      step++;
    }

    hasStarted = true;
  }

  public List<Car> getCars() {
    return cars;
  }

  public Map<Integer, Map<Position, List<Car>>> getCrashedTable() {
    return crashedTable;
  }

  public Map<Car, Integer> getCrashedCarsToStep() {
    return crashedCarsToStep;
  }
}
