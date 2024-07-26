package io.samjingwen.input;

import io.samjingwen.util.Result;
import java.util.Scanner;
import java.util.function.Function;

public class InputListener {

  private final Scanner scanner;

  public InputListener() {
    scanner = new Scanner(System.in);
  }

  public <T> T handleInput(Function<String, Result<T>> fn, String message, Object... params) {
    while (true) {
      System.out.printf(message, params);
      String line = scanner.nextLine();
      Result<T> result = fn.apply(line);
      if (result.isSuccess()) {
        return result.get();
      }
    }
  }
}
