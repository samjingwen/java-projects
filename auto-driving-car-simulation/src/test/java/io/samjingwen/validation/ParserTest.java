package io.samjingwen.validation;

import static io.samjingwen.core.Command.*;
import static io.samjingwen.validation.Error.INVALID_INPUT;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

import io.samjingwen.core.Command;
import io.samjingwen.core.Direction;
import io.samjingwen.core.Field;
import io.samjingwen.core.Position;
import io.samjingwen.util.Pair;
import io.samjingwen.util.Result;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParserTest {

  Parser parser;

  @BeforeEach
  void setUp() {
    parser = new Parser();
  }

  @ParameterizedTest
  @MethodSource("testParseFieldSizeSuccessParameters")
  void testParseFieldSizeSuccess(String input, Result<Field> expected) {
    // when
    Result<Field> result = parser.parseFieldSize(input);

    // then
    assertEquals(expected, result);
  }

  private static Stream<Arguments> testParseFieldSizeSuccessParameters() {
    return Stream.of(
        Arguments.of("7 8", Result.success(new Field(7, 8))),
        Arguments.of(" 10 9 ", Result.success(new Field(10, 9))));
  }

  @ParameterizedTest
  @MethodSource("testParseFieldSizeFailureParameters")
  void testParseFieldSizeFailure(String input) {
    // when
    Result<Field> result = parser.parseFieldSize(input);

    // then
    assertEquals(Result.failure(Set.of(INVALID_INPUT)), result);
  }

  private static Stream<Arguments> testParseFieldSizeFailureParameters() {
    return Stream.of(Arguments.of("10 G"), Arguments.of(" 10 10 W"));
  }

  @ParameterizedTest
  @MethodSource("testParseSelectedOptionSuccessParameters")
  void testParseSelectedOptionSuccess(String input, Result<Integer> expected) {
    // when
    Result<Integer> result = parser.parseSelectedOption(input);

    // then
    assertEquals(expected, result);
  }

  private static Stream<Arguments> testParseSelectedOptionSuccessParameters() {
    return Stream.of(
        Arguments.of("1", Result.success(1)),
        Arguments.of("  1  ", Result.success(1)),
        Arguments.of("2", Result.success(2)),
        Arguments.of(" 2 ", Result.success(2)));
  }

  @ParameterizedTest
  @MethodSource("testParseSelectedOptionFailureParameters")
  void testParseSelectedOptionFailure(String input) {
    // when
    Result<Integer> result = parser.parseSelectedOption(input);

    // then
    assertEquals(Result.failure(Set.of(INVALID_INPUT)), result);
  }

  private static Stream<Arguments> testParseSelectedOptionFailureParameters() {
    return Stream.of(Arguments.of("A"), Arguments.of("B"), Arguments.of("3"));
  }

  @ParameterizedTest
  @MethodSource("testParseCarNameSuccessParameters")
  void testParseCarNameSuccess(String input, Result<String> expected) {
    // when
    Result<String> result = parser.parseCarName(input);

    // then
    assertEquals(expected, result);
  }

  private static Stream<Arguments> testParseCarNameSuccessParameters() {
    return Stream.of(
        Arguments.of("A", Result.success("A")),
        Arguments.of("B", Result.success("B")),
        Arguments.of("A ", Result.success("A ")),
        Arguments.of("B ", Result.success("B ")));
  }

  @ParameterizedTest
  @MethodSource("testParseCarNameFailureParameters")
  void testParseCarNameFailure(String input) {
    // when
    Result<String> result = parser.parseCarName(input);

    // then
    assertEquals(Result.failure(Set.of(INVALID_INPUT)), result);
  }

  private static Stream<String> testParseCarNameFailureParameters() {
    return Stream.of("", null);
  }

  @ParameterizedTest
  @MethodSource("testParseCarPositionSuccessParameters")
  void testParseCarPositionSuccess(String input, Result<Pair<Position, Direction>> expected) {
    // when
    Result<Pair<Position, Direction>> result = parser.parseCarPosition(input);

    // then
    assertEquals(expected, result);
  }

  private static Stream<Arguments> testParseCarPositionSuccessParameters() {
    return Stream.of(
        Arguments.of("1 2 S", Result.success(Pair.of(new Position(1, 2), Direction.S))),
        Arguments.of("10 9 W  ", Result.success(Pair.of(new Position(10, 9), Direction.W))));
  }

  @ParameterizedTest
  @MethodSource("testParseCarPositionFailureParameters")
  void testParseCarPositionFailure(String input) {
    // when
    Result<Pair<Position, Direction>> result = parser.parseCarPosition(input);

    // then
    assertEquals(Result.failure(Set.of(INVALID_INPUT)), result);
  }

  private static Stream<Arguments> testParseCarPositionFailureParameters() {
    return Stream.of(
        Arguments.of("1 2 G"),
        Arguments.of("10 A W"),
        Arguments.of("10 10 10 10"),
        Arguments.of("10 "));
  }

  @ParameterizedTest
  @MethodSource("testParseCarCommandsSuccessParameters")
  void testParseCarCommandsSuccess(String input, Result<List<Command>> expected) {
    // when
    Result<List<Command>> result = parser.parseCarCommands(input);

    // then
    assertEquals(expected, result);
  }

  private static Stream<Arguments> testParseCarCommandsSuccessParameters() {
    return Stream.of(
        Arguments.of("", Result.success(emptyList())),
        Arguments.of("FFRFFFFRRL", Result.success(List.of(F, F, R, F, F, F, F, R, R, L))),
        Arguments.of("FFLFFFFFFF   ", Result.success(List.of(F, F, L, F, F, F, F, F, F, F))));
  }

  @ParameterizedTest
  @MethodSource("testParseCarCommandsFailureParameters")
  void testParseCarCommandsFailure(String input) {
    // when
    Result<List<Command>> result = parser.parseCarCommands(input);

    // then
    assertEquals(Result.failure(Set.of(INVALID_INPUT)), result);
  }

  private static Stream<Arguments> testParseCarCommandsFailureParameters() {
    return Stream.of(
        Arguments.of("fffrlrl"),
        Arguments.of("FFLG  "),
        Arguments.of(" FFFLRLRLFF1  "),
        Arguments.of("101010FFRL"));
  }
}
