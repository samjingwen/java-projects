package io.samjingwen;

import io.samjingwen.core.Program;
import io.samjingwen.input.InputListener;
import io.samjingwen.validation.Parser;

public class Application {

  public static void main(String[] args) {
    Program program = new Program(new Parser(), new InputListener());
    program.run();
  }
}
