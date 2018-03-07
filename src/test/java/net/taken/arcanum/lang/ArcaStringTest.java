package net.taken.arcanum.lang;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArcaStringTest {

    ArcaString s1;
    ArcaString s2;

    @BeforeEach
    void setUp() {
        s1 = new ArcaString("k1fIk");
        s2 = new ArcaString("m8Suj");
    }

    @Test
    void shouldReturnRightResultWhenConcatenateStrings() {
        assertEquals(new ArcaString("k1fIkm8Suj"), s1.plus(s2));
    }

    @Test
    void shouldReturnRightResultWhenConcatenateStringAndInt() {
        assertEquals(new ArcaString("k1fIk399"), s1.plus(new ArcaInteger(399)));
    }

    @Test
    void shouldReturnRightResultWhenMultiplyStringWithInteger() {
        assertEquals(new ArcaString("k1fIkk1fIkk1fIk"), s1.multiply(new ArcaInteger(3)));
    }

    @Test
    void shouldReturnRightResultWhenMultiplyStringWithIntegerString() {
        assertEquals(new ArcaString("k1fIkk1fIk"), s1.multiply(new ArcaString("2")));
    }
}
