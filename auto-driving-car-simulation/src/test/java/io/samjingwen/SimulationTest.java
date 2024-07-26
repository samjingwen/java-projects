package io.samjingwen;

import static io.samjingwen.core.Command.*;
import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.assertThat;

import io.samjingwen.core.Car;
import io.samjingwen.core.Configuration;
import io.samjingwen.core.Direction;
import io.samjingwen.core.Field;
import io.samjingwen.core.Position;
import io.samjingwen.core.Simulation;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SimulationTest {

  @ParameterizedTest
  @MethodSource("testStartParameters")
  void testStart(
      Simulation testSubject,
      List<Car> expectedCars,
      Map<Integer, Map<Position, List<Car>>> expectedCrashedTable,
      Map<Car, Integer> expectedCrashedCarToStep) {

    // when
    testSubject.start();

    // then
    assertThat(testSubject.getCars()).isEqualTo(expectedCars);
    assertThat(testSubject.getCrashedTable()).isEqualTo(expectedCrashedTable);
    assertThat(testSubject.getCrashedCarsToStep()).isEqualTo(expectedCrashedCarToStep);
  }

  private static Stream<Arguments> testStartParameters() {
    return Stream.of(
        // one car
        Arguments.of(
            new Simulation(
                new Configuration(
                    new Field(10, 10),
                    List.of(new Car("A", new Position(1, 2), Direction.N)),
                    List.of(List.of(F, F, R, F, F, F, F, R, R, L)))),
            List.of(new Car("A", new Position(5, 4), Direction.S)),
            emptyMap(),
            emptyMap()),
        // two cars crashed
        Arguments.of(
            new Simulation(
                new Configuration(
                    new Field(10, 10),
                    List.of(
                        new Car("A", new Position(1, 2), Direction.N),
                        new Car("B", new Position(7, 8), Direction.W)),
                    List.of(
                        List.of(F, F, R, F, F, F, F, R, R, L),
                        List.of(F, F, L, F, F, F, F, F, F, F)))),
            List.of(
                new Car("A", new Position(5, 4), Direction.E),
                new Car("B", new Position(5, 4), Direction.S)),
            Map.of(
                7,
                Map.of(
                    new Position(5, 4),
                    List.of(
                        new Car("A", new Position(5, 4), Direction.E),
                        new Car("B", new Position(5, 4), Direction.S)))),
            Map.of(
                new Car("A", new Position(5, 4), Direction.E), 7,
                new Car("B", new Position(5, 4), Direction.S), 7)),
        // three cars crashed at the same step
        Arguments.of(
            new Simulation(
                new Configuration(
                    new Field(10, 10),
                    List.of(
                        new Car("A", new Position(1, 2), Direction.N),
                        new Car("B", new Position(7, 8), Direction.W),
                        new Car("C", new Position(5, 0), Direction.S)),
                    List.of(
                        List.of(F, F, R, F, F, F, F, R, R, L),
                        List.of(F, F, L, F, F, F, F, F, F, F),
                        List.of(F, R, R, F, F, F, F, L, R, F, F)))),
            List.of(
                new Car("A", new Position(5, 4), Direction.E),
                new Car("B", new Position(5, 4), Direction.S),
                new Car("C", new Position(5, 4), Direction.N)),
            Map.of(
                7,
                Map.of(
                    new Position(5, 4),
                    List.of(
                        new Car("A", new Position(5, 4), Direction.E),
                        new Car("B", new Position(5, 4), Direction.S),
                        new Car("C", new Position(5, 4), Direction.N)))),
            Map.of(
                new Car("A", new Position(5, 4), Direction.E), 7,
                new Car("B", new Position(5, 4), Direction.S), 7,
                new Car("C", new Position(5, 4), Direction.N), 7)),
        // two cars crashed at step 7, third car crashed at step 14
        Arguments.of(
            new Simulation(
                new Configuration(
                    new Field(10, 10),
                    List.of(
                        new Car("A", new Position(1, 2), Direction.N),
                        new Car("B", new Position(7, 8), Direction.W),
                        new Car("C", new Position(5, 0), Direction.S)),
                    List.of(
                        List.of(F, F, R, F, F, F, F, R, R, L),
                        List.of(F, F, L, F, F, F, F, F, F, F),
                        List.of(F, R, R, F, F, F, L, F, F, R, F, R, F, F, L, R, F, R)))),
            List.of(
                new Car("A", new Position(5, 4), Direction.E),
                new Car("B", new Position(5, 4), Direction.S),
                new Car("C", new Position(5, 4), Direction.E)),
            Map.of(
                7,
                Map.of(
                    new Position(5, 4),
                    List.of(
                        new Car("A", new Position(5, 4), Direction.E),
                        new Car("B", new Position(5, 4), Direction.S))),
                14,
                Map.of(
                    new Position(5, 4),
                    List.of(
                        new Car("A", new Position(5, 4), Direction.E),
                        new Car("B", new Position(5, 4), Direction.S),
                        new Car("C", new Position(5, 4), Direction.E)))),
            Map.of(
                new Car("A", new Position(5, 4), Direction.E), 7,
                new Car("B", new Position(5, 4), Direction.S), 7,
                new Car("C", new Position(5, 4), Direction.E), 14)),
        // two of four cars crashed at two different positions at step 7
        Arguments.of(
            new Simulation(
                new Configuration(
                    new Field(10, 10),
                    List.of(
                        new Car("A", new Position(1, 2), Direction.N),
                        new Car("B", new Position(7, 8), Direction.W),
                        new Car("C", new Position(0, 0), Direction.E),
                        new Car("D", new Position(5, 0), Direction.S)),
                    List.of(
                        List.of(F, F, R, F, F, F, F, R, R, L),
                        List.of(F, F, L, F, F, F, F, F, F, F),
                        List.of(L, L, L, L, L, L, L, L, L, L),
                        List.of(F, R, F, F, F, F, F, F, F)))),
            List.of(
                new Car("A", new Position(5, 4), Direction.E),
                new Car("B", new Position(5, 4), Direction.S),
                new Car("C", new Position(0, 0), Direction.S),
                new Car("D", new Position(0, 0), Direction.W)),
            Map.of(
                7,
                Map.of(
                    new Position(5, 4),
                    List.of(
                        new Car("A", new Position(5, 4), Direction.E),
                        new Car("B", new Position(5, 4), Direction.S)),
                    new Position(0, 0),
                    List.of(
                        new Car("C", new Position(0, 0), Direction.S),
                        new Car("D", new Position(0, 0), Direction.W)))),
            Map.of(
                new Car("A", new Position(5, 4), Direction.E), 7,
                new Car("B", new Position(5, 4), Direction.S), 7,
                new Car("C", new Position(0, 0), Direction.S), 7,
                new Car("D", new Position(0, 0), Direction.W), 7)));
  }
}
