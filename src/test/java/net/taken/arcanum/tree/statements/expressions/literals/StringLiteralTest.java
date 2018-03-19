package net.taken.arcanum.tree.statements.expressions.literals;

import net.taken.arcanum.lang.ArcaEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringLiteralTest {

    private ArcaEnvironment env;
    private StringLiteral stringNode1;
    private StringLiteral stringNode2;

    @BeforeEach
    void setUp() {
        env = new ArcaEnvironment();
        stringNode1 = new StringLiteral("Slaker");
        stringNode2 = new StringLiteral("Unicell");
    }

    @Test
    void shouldReturnIntValueWhenExecuteIntegerLiteral() {
        assertEquals("Slaker", stringNode1.execute(env).getValue());
        assertEquals("Unicell", stringNode2.execute(env).getValue());
    }

}
