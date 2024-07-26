package io.samjingwen.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

  @Test
  void testGetters() {
    // given
    Position testSubject = new Position(6, 9);

    // when
    int x = testSubject.getX();
    int y = testSubject.getY();

    // then
    assertEquals(6, x);
    assertEquals(9, y);
  }
}
