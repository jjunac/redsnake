package net.taken.redsnake.tree.statements.expressions.literals;

import net.taken.redsnake.interpretor.RedsEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BooleanLiteralTest {

    private RedsEnvironment env;
    private BooleanLiteral trueNode;
    private BooleanLiteral falseNode;

    @BeforeEach
    void setUp() {
        env = new RedsEnvironment();
        trueNode = new BooleanLiteral("true");
        falseNode = new BooleanLiteral("false");
    }

    @Test
    void shouldReturnTrueWhenExecutingTrueNode() {
        env = new RedsEnvironment();
        assertTrue(trueNode.execute(env).getValue().getValue());
    }

    @Test
    void shouldReturnFalseWhenExecutingFalseNode() {
        assertFalse(falseNode.execute(env).getValue().getValue());
    }
}
