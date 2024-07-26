package io.samjingwen.validation;

import static io.samjingwen.validation.Error.*;

import io.samjingwen.core.Command;
import io.samjingwen.core.Direction;
import io.samjingwen.core.Field;
import io.samjingwen.core.Position;
import io.samjingwen.util.Pair;
import io.samjingwen.util.Result;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {

  public Result<Field> parseFieldSize(String input) {
    Set<Error> errors = new HashSet<>();

    try {
      String[] coordinates = input.trim().split(" ");
      if (coordinates.length != 2) {
        errors.add(INVALID_INPUT);
      } else {
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        return Result.success(new Field(x, y));
      }
    } catch (NumberFormatException e) {
      errors.add(INVALID_INPUT);
    }

    return Result.failure(errors);
  }

  public Result<Integer> parseSelectedOption(String input) {
    Set<Error> errors = new HashSet<>();

    try {
      int option = Integer.parseInt(input.trim());
      if (option == 1 || option == 2) {
        return Result.success(option);
      }
      errors.add(INVALID_INPUT);
    } catch (Exception e) {
      errors.add(INVALID_INPUT);
    }

    return Result.failure(errors);
  }

  public Result<String> parseCarName(String input) {
    Set<Error> errors = new HashSet<>();

    if (input != null && !input.isEmpty()) {
      return Result.success(input);
    }
    errors.add(INVALID_INPUT);

    return Result.failure(errors);
  }

  public Result<Pair<Position, Direction>> parseCarPosition(String input) {
    Set<Error> errors = new HashSet<>();

    try {
      String[] strings = input.trim().split(" ");
      if (strings.length != 3) {
        errors.add(INVALID_INPUT);
      } else {
        int x = Integer.parseInt(strings[0]);
        int y = Integer.parseInt(strings[1]);
        Direction direction = Direction.valueOf(strings[2]);
        return Result.success(Pair.of(new Position(x, y), direction));
      }

    } catch (IllegalArgumentException e) {
      errors.add(INVALID_INPUT);
    }

    return Result.failure(errors);
  }

  public Result<List<Command>> parseCarCommands(String input) {
    Set<Error> errors = new HashSet<>();

    try {
      List<Command> commands = new ArrayList<>();
      char[] chars = input.trim().toCharArray();
      for (char ch : chars) {
        commands.add(Command.valueOf(String.valueOf(ch)));
      }
      return Result.success(commands);

    } catch (IllegalArgumentException e) {
      errors.add(INVALID_INPUT);
    }

    return Result.failure(errors);
  }
}
