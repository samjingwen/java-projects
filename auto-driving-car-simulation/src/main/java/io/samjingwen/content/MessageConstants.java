package io.samjingwen.content;

public final class MessageConstants {

  public static final String WELCOME_MESSAGE = "Welcome to Auto Driving Car Simulation!\n";

  public static final String WELCOME_INSTRUCTION =
      "Please enter the width and height of the simulation field in x y format:\n";

  public static final String SIMULATION_FIELD_SUCCESS = "You have created a field of %d x %d.\n";

  public static final String START_INSTRUCTION =
      "Please choose from the following options:\n[1] Add a car to field\n[2] Run simulation\n";

  public static final String ENTER_CAR_NAME = "Please enter the name of the car:\n";

  public static final String ENTER_CAR_INITIAL_POSITION =
      "Please enter initial position of car %s in x y Direction format:\n";

  public static final String ENTER_CAR_COMMANDS = "Please enter the commands for car %s:\n";

  public static final String LIST_OF_CARS = "Your current list of cars are:\n";

  public static final String START_CAR_INFORMATION = "- %s, (%d, %d) %s, %s\n";

  public static final String SIMULATION_RESULT = "After simulation, the result is:\n";

  public static final String END_CAR_INFORMATION = "- %s, (%d, %d) %s\n";

  public static final String CRASHED_CAR_INFORMATION =
      "- %s, collides with %s at (%d, %d) at step %d\n";

  public static final String POST_SIMULATION_MESSAGE = "Please choose from the following options:\n[1] Start over\n[2] Exit\n";

  public static final String GOODBYE_MESSAGE = "Thank you for running the simulation. Goodbye!\n";

  private MessageConstants() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }
}
