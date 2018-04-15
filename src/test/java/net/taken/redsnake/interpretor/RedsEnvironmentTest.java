package net.taken.redsnake.interpretor;

import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.lang.RedsString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.redsnake.operations.OperatorType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RedsEnvironmentTest {

    private RedsEnvironment env;

    @BeforeEach
    void setUp() {
        env = new RedsEnvironment();
    }

    @Test
    void shouldReturnCorrectResultWhenExecutingBuiltinOperation() {
        // TODO Value factory
        // =============
        // INTEGER
        // =============
        assertEquals(new RedsInteger(16), env.resolveBinaryOperation(D_STAR, RedsInteger.TYPE, RedsInteger.TYPE).get()
            .apply(new Value<>(RedsInteger.TYPE, new RedsInteger(4)), new Value<>(RedsInteger.TYPE, new RedsInteger(2))).getValue());
        assertEquals(new RedsInteger(-124), env.resolveUnaryOperation(MINUS, RedsInteger.TYPE).get()
            .apply(new Value<>(RedsInteger.TYPE, new RedsInteger(124))).getValue());
        assertEquals(new RedsInteger(21), env.resolveBinaryOperation(STAR, RedsInteger.TYPE, RedsInteger.TYPE).get()
            .apply(new Value<>(RedsInteger.TYPE, new RedsInteger(3)), new Value<>(RedsInteger.TYPE, new RedsInteger(7))).getValue());
        assertEquals(new RedsInteger(5), env.resolveBinaryOperation(SLASH, RedsInteger.TYPE, RedsInteger.TYPE).get()
            .apply(new Value<>(RedsInteger.TYPE, new RedsInteger(10)), new Value<>(RedsInteger.TYPE, new RedsInteger(2))).getValue());
        assertEquals(new RedsInteger(2), env.resolveBinaryOperation(PERCENT, RedsInteger.TYPE, RedsInteger.TYPE).get()
            .apply(new Value<>(RedsInteger.TYPE, new RedsInteger(5)), new Value<>(RedsInteger.TYPE, new RedsInteger(3))).getValue());
        assertEquals(new RedsInteger(6), env.resolveBinaryOperation(PLUS, RedsInteger.TYPE, RedsInteger.TYPE).get()
            .apply(new Value<>(RedsInteger.TYPE, new RedsInteger(4)), new Value<>(RedsInteger.TYPE, new RedsInteger(2))).getValue());
        assertEquals(new RedsInteger(3), env.resolveBinaryOperation(MINUS, RedsInteger.TYPE, RedsInteger.TYPE).get()
            .apply(new Value<>(RedsInteger.TYPE, new RedsInteger(10)), new Value<>(RedsInteger.TYPE, new RedsInteger(7))).getValue());
        // =============
        // STRING
        // =============
        assertEquals(new RedsString("abcabcabc"), env.resolveBinaryOperation(STAR, RedsString.TYPE, RedsInteger.TYPE).get()
            .apply(new Value<>(RedsString.TYPE, new RedsString("abc")), new Value<>(RedsInteger.TYPE, new RedsInteger(3))).getValue());
        assertEquals(new RedsString("Vizirs"), env.resolveBinaryOperation(PLUS, RedsString.TYPE, RedsString.TYPE).get()
            .apply(new Value<>(RedsString.TYPE, new RedsString("Viz")), new Value<>(RedsString.TYPE, new RedsString("irs"))).getValue());
    }
}
