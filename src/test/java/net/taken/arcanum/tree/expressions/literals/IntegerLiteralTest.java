package net.taken.arcanum.tree.expressions.literals;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerLiteralTest {

    private ArcaEnvironment env;
    private IntegerLiteral intNode1;
    private IntegerLiteral intNode2;

    @BeforeEach
    void setUp() {
        env = new ArcaEnvironment();
        intNode1 = new IntegerLiteral("255");
        intNode2 = new IntegerLiteral("127");
    }

    @Test
    void shouldReturnIntValueWhenExecuteIntegerLiteral() {
        assertEquals(255, intNode1.execute(env).getValue());
        assertEquals(127, intNode1.execute(env).getValue());
    }
}
