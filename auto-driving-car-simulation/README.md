# Auto Driving Car Simulation

## Project Description
Auto Driving Car Simulation is a command-line application developed in Java. The project simulates the movement of a car on a grid field based on user input commands. This project demonstrates core concepts of object-oriented programming and command-line interface parsing.

## Environment Setup
To run this project, you need to set up your environment as follows:

- **Operating System**: The project is compatible with macOS and Linux.
- **Java Development Kit (JDK)**: Version 21 or higher.
- **Gradle**: Version 8.5 or higher


## Installation Instructions
To set up and run this project on your local machine, follow these steps:


1. **Build the project using Gradle**:
    ```bash
    gradle build
    ```


## Usage Instructions
Once you have the project set up, you can run the application using the following command:

```bash
java -jar build/libs/gic-tha.jar
```

The application will prompt you to input commands for the simulation.


## Code Structure
The project follows a structured approach, organizing code into various packages based on functionality:

- **content**: Holds constant values used in the application.
   - `MessageConstants.java`: Contains message constants used throughout the application.

- **core**: Includes the core logic and domain models.
   - `Car.java`: Represents the car object.
   - `Command.java`: Represents commands that control the car.
   - `Configuration.java`: Configuration settings for the simulation.
   - `Direction.java`: Enum for car directions.
   - `Field.java`: Represents the grid field.
   - `Position.java`: Represents the position of the car on the grid.
   - `Program.java`: Main entry point of the application. Handles the program logic.
   - `Simulation.java`: Handles the simulation logic.

- **input**: Manages user input handling.
   - `InputListener.java`: Listens for and processes user inputs.

- **util**: Provides utility classes.
   - `Failure.java`: Represents a failed result.
   - `Pair.java`: Utility class for handling pairs of objects.
   - `Result.java`: Represents a result, either success or failure.
   - `Success.java`: Represents a successful result.

- **validation**: Contains validation logic.
   - `Error.java`: Handles error messages.
   - `Parser.java`: Validates and parses input data.

- **test**: Holds test cases to ensure code quality and functionality.
   - `SimulationTest.java`: Tests for the `Simulation` class.
   - `CarTest.java`: Tests for the `Car` class.
   - `PositionTest.java`: Tests for the `Position` class.
   - `ParserTest.java`: Tests for the `Parser` class.

## Assumptions Made
During the development of this project, the following assumptions were made:

1. **Grid Boundaries**: The grid is finite, and the car cannot move outside the boundaries of the grid. Commands that would cause the car to move outside the grid are ignored.
2. **Command Format**: The input commands are provided in a specific format and are case-sensitive. Commands are expected to be well-formed and valid.
3. **Error Handling**: Basic error handling is in place for invalid commands or inputs. Detailed error reporting is not implemented.
4. **Initial Position**: The initial position of the car is within the boundaries of the grid.
5. **Sequential Commands**: Commands are processed sequentially, one at a time.
6. **Collision Reporting**: When cars collide, the collision report will only include the cars involved at that specific moment, and the reports for previous collisions remain unchanged. For example, if car A and car B collide at step 7, the report will mention only car A and car B. If later on, car C collides with the already impacted car A and car B, car C's collision report will mention car A and car B, while the report for car A and car B will remain unchanged.


## License
This project is licensed under the GNU General Public License v3.0 - see the [LICENSE](LICENSE) file for details.

## Contact Information
For any inquiries or issues, please contact Sam Jing Wen at samjingwen@u.nus.edu
