package net.taken.arcanum.lang;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArcaIntegerTest {

    ArcaInteger i1;
    ArcaInteger i2;

    @BeforeEach
    void setUp() {
        i1 = new ArcaInteger(275);
        i2 = new ArcaInteger(531);
    }

    @Test
    void shouldReturnRightResultWhenDoingAddition() {
        assertEquals(new ArcaInteger(806), i1.plus(i2));
    }

    @Test
    void shouldReturnRightResultWhenDoingSubtraction() {
        assertEquals(new ArcaInteger(-256), i1.minus(i2));
    }

    @Test
    void shouldReturnRightResultWhenDoingMultiplication() {
        assertEquals(new ArcaInteger(146025), i1.multiply(i2));
    }

    @Test
    void shouldReturnRightResultWhenDoingStableDivision() {
        assertEquals(new ArcaInteger(11), i1.divide(new ArcaInteger(25)));
    }

    @Test
    void shouldReturnRightResultWhenDoingModulo() {
        assertEquals(new ArcaInteger(256), i2.modulo(i1));
    }

    @Test
    void shouldReturnRightResultWhenDoingPower() {
        assertEquals(new ArcaInteger(75625), i1.power(new ArcaInteger(2)));
    }

    @Test
    void shouldReturnRightResultWhenDoingInversion() {
        assertEquals(new ArcaInteger(-275), i1.uminus());
    }

}
