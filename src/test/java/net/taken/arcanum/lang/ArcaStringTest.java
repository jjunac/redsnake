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
}
