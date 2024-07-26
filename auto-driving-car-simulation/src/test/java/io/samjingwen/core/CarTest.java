package io.samjingwen.core;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.samjingwen.core.Command.*;
import static io.samjingwen.core.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {

  @ParameterizedTest
  @MethodSource("testMoveParameters")
  void testMove(
      Car testSubject,
      Field field,
      Command command,
      Position expectedPosition,
      Direction expectedDirection) {
    // when
    testSubject.move(field, command);

    // then
    assertEquals(expectedPosition, testSubject.getPosition());
    assertEquals(expectedDirection, testSubject.getDirection());
  }

  private static Stream<Arguments> testMoveParameters() {
    return Stream.of(
        // out of grid
        Arguments.of(new Car("A", new Position(0, 0), N), new Field(1, 1), F, new Position(0, 0), N),
        Arguments.of(new Car("A", new Position(0, 0), S), new Field(1, 1), F, new Position(0, 0), S),
        Arguments.of(new Car("A", new Position(0, 0), E), new Field(1, 1), F, new Position(0, 0), E),
        Arguments.of(new Car("A", new Position(0, 0), W), new Field(1, 1), F, new Position(0, 0), W),
        // forward
        Arguments.of(new Car("A", new Position(1, 1), N), new Field(3, 3), F, new Position(1, 2), N),
        Arguments.of(new Car("A", new Position(1, 1), S), new Field(3, 3), F, new Position(1, 0), S),
        Arguments.of(new Car("A", new Position(1, 1), E), new Field(3, 3), F, new Position(2, 1), E),
        Arguments.of(new Car("A", new Position(1, 1), W), new Field(3, 3), F, new Position(0, 1), W),
        // rotate left
        Arguments.of(new Car("A", new Position(1, 1), N), new Field(3, 3), L, new Position(1, 1), W),
        Arguments.of(new Car("A", new Position(1, 1), S), new Field(3, 3), L, new Position(1, 1), E),
        Arguments.of(new Car("A", new Position(1, 1), E), new Field(3, 3), L, new Position(1, 1), N),
        Arguments.of(new Car("A", new Position(1, 1), W), new Field(3, 3), L, new Position(1, 1), S),
        // rotate right
        Arguments.of(new Car("A", new Position(1, 1), N), new Field(3, 3), R, new Position(1, 1), E),
        Arguments.of(new Car("A", new Position(1, 1), S), new Field(3, 3), R, new Position(1, 1), W),
        Arguments.of(new Car("A", new Position(1, 1), E), new Field(3, 3), R, new Position(1, 1), S),
        Arguments.of(new Car("A", new Position(1, 1), W), new Field(3, 3), R, new Position(1, 1), N));
  }
}
