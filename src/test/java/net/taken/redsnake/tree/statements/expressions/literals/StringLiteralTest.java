package net.taken.redsnake.tree.statements.expressions.literals;

import net.taken.redsnake.interpretor.RedsEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringLiteralTest {

    private RedsEnvironment env;
    private StringLiteral stringNode1;
    private StringLiteral stringNode2;

    @BeforeEach
    void setUp() {
        env = new RedsEnvironment();
        stringNode1 = new StringLiteral("Slaker");
        stringNode2 = new StringLiteral("Unicell");
    }

    @Test
    void shouldReturnIntValueWhenExecuteIntegerLiteral() {
        assertEquals("Slaker", stringNode1.execute(env).getValue().getValue());
        assertEquals("Unicell", stringNode2.execute(env).getValue().getValue());
    }

}
