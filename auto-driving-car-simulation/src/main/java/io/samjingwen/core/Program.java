package io.samjingwen.core;

import static io.samjingwen.content.MessageConstants.*;
import static java.util.stream.Collectors.joining;

import io.samjingwen.content.MessageConstants;
import io.samjingwen.input.InputListener;
import io.samjingwen.util.Pair;
import io.samjingwen.validation.Parser;
import java.util.List;
import java.util.Map;

public class Program {

  private final Parser parser;
  private final InputListener inputListener;

  public Program(Parser parser, InputListener inputListener) {
    this.parser = parser;
    this.inputListener = inputListener;
  }

  public void run() {
    while (true) {
      System.out.printf(MessageConstants.WELCOME_MESSAGE);
      System.out.println();

      Field field = inputListener.handleInput(parser::parseFieldSize, WELCOME_INSTRUCTION);
      System.out.println();
      System.out.printf(MessageConstants.SIMULATION_FIELD_SUCCESS, field.x(), field.y());
      System.out.println();

      Configuration configuration = new Configuration(field);

      while (true) {
        if (!configuration.getCars().isEmpty()) {
          printCarInitialInformation(configuration);
        }

        Integer selectedOption =
            inputListener.handleInput(parser::parseSelectedOption, START_INSTRUCTION);

        if (selectedOption == 1) {
          String carName = inputListener.handleInput(parser::parseCarName, ENTER_CAR_NAME);

          Pair<Position, Direction> carPositionDirection =
              inputListener.handleInput(
                  parser::parseCarPosition, ENTER_CAR_INITIAL_POSITION, carName);
          Position position = carPositionDirection.getLeft();
          Direction direction = carPositionDirection.getRight();

          List<Command> commands =
              inputListener.handleInput(parser::parseCarCommands, ENTER_CAR_COMMANDS, carName);

          configuration.add(new Car(carName, position, direction), commands);

        } else if (selectedOption == 2) {
          Simulation simulation = new Simulation(configuration);
          simulation.start();

          printCarInitialInformation(configuration);
          printSimulationResult(simulation);

          break;
        }
      }

      System.out.println();
      int selectedOption =
          inputListener.handleInput(parser::parseSelectedOption, POST_SIMULATION_MESSAGE);
      if (selectedOption == 2) {
        break;
      }
    }

    System.out.printf(GOODBYE_MESSAGE);
  }

  private void printCarInitialInformation(Configuration configuration) {
    List<Car> startCars = configuration.getCars();
    List<List<Command>> carCommands = configuration.getCarCommands();

    System.out.printf(LIST_OF_CARS);
    for (int i = 0; i < startCars.size(); i++) {
      Car car = startCars.get(i);
      String commands = carCommands.get(i).stream().map(Enum::name).collect(joining());
      System.out.printf(
          START_CAR_INFORMATION,
          car.getName(),
          car.getPosition().getX(),
          car.getPosition().getY(),
          car.getDirection().name(),
          commands);
    }

    System.out.println();
  }

  private void printSimulationResult(Simulation simulation) {
    List<Car> endCars = simulation.getCars();
    Map<Integer, Map<Position, List<Car>>> crashedTable = simulation.getCrashedTable();
    Map<Car, Integer> crashedCarsToStep = simulation.getCrashedCarsToStep();

    System.out.printf(SIMULATION_RESULT);

    for (Car endCar : endCars) {
      if (crashedCarsToStep.containsKey(endCar)) {
        Integer step = crashedCarsToStep.get(endCar);
        List<Car> crashedCars = crashedTable.get(step).get(endCar.getPosition());
        String crashedCarsStr =
            crashedCars.stream()
                .filter(car -> !car.equals(endCar))
                .map(Car::getName)
                .collect(joining(", "));
        System.out.printf(
            CRASHED_CAR_INFORMATION,
            endCar.getName(),
            crashedCarsStr,
            endCar.getPosition().getX(),
            endCar.getPosition().getY(),
            step);

      } else {
        System.out.printf(
            END_CAR_INFORMATION,
            endCar.getName(),
            endCar.getPosition().getX(),
            endCar.getPosition().getY(),
            endCar.getDirection().name());
      }
    }
  }
}
