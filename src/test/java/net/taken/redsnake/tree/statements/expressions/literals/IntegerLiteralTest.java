package net.taken.redsnake.tree.statements.expressions.literals;

import net.taken.redsnake.lang.RedsEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerLiteralTest {

    private RedsEnvironment env;
    private IntegerLiteral intNode1;
    private IntegerLiteral intNode2;

    @BeforeEach
    void setUp() {
        env = new RedsEnvironment();
        intNode1 = new IntegerLiteral("255");
        intNode2 = new IntegerLiteral("127");
    }

    @Test
    void shouldReturnIntValueWhenExecuteIntegerLiteral() {
        assertEquals(255, intNode1.execute(env).getValue());
        assertEquals(127, intNode2.execute(env).getValue());
    }
}
