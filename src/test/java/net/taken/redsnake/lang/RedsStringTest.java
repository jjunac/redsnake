package net.taken.redsnake.lang;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RedsStringTest {

    RedsString s1;
    RedsString s2;

    @BeforeEach
    void setUp() {
        s1 = new RedsString("k1fIk");
        s2 = new RedsString("m8Suj");
    }

    @Test
    void shouldReturnRightResultWhenConcatenateStrings() {
        assertEquals(new RedsString("k1fIkm8Suj"), s1.plus(s2));
    }

    @Test
    void shouldReturnRightResultWhenConcatenateStringAndInt() {
        assertEquals(new RedsString("k1fIk399"), s1.plus(new RedsInteger(399)));
    }

    @Test
    void shouldReturnRightResultWhenMultiplyStringWithInteger() {
        assertEquals(new RedsString("k1fIkk1fIkk1fIk"), s1.multiply(new RedsInteger(3)));
    }

    @Test
    void shouldReturnRightResultWhenMultiplyStringWithIntegerString() {
        assertEquals(new RedsString("k1fIkk1fIk"), s1.multiply(new RedsString("2")));
    }
}
