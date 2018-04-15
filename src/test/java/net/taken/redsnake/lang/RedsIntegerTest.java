package net.taken.redsnake.lang;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RedsIntegerTest {

    RedsInteger i1;
    RedsInteger i2;

    @BeforeEach
    void setUp() {
        i1 = new RedsInteger(275);
        i2 = new RedsInteger(531);
    }

    @Test
    void shouldReturnRightResultWhenDoingAddition() {
        assertEquals(new RedsInteger(806), i1.plus(i2));
    }

    @Test
    void shouldReturnRightResultWhenDoingSubtraction() {
        assertEquals(new RedsInteger(-256), i1.minus(i2));
    }

    @Test
    void shouldReturnRightResultWhenDoingMultiplication() {
        assertEquals(new RedsInteger(146025), i1.multiply(i2));
    }

    @Test
    void shouldReturnRightResultWhenDoingStableDivision() {
        assertEquals(new RedsInteger(11), i1.divide(new RedsInteger(25)));
    }

    @Test
    void shouldReturnRightResultWhenDoingModulo() {
        assertEquals(new RedsInteger(256), i2.modulo(i1));
    }

    @Test
    void shouldReturnRightResultWhenDoingPower() {
        assertEquals(new RedsInteger(75625), i1.power(new RedsInteger(2)));
    }

    @Test
    void shouldReturnRightResultWhenDoingInversion() {
        assertEquals(new RedsInteger(-275), i1.uminus());
    }

}
