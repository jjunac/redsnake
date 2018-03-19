package net.taken.arcanum.tree.statements.expressions.literals;

import net.taken.arcanum.lang.ArcaEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BooleanLiteralTest {

    private ArcaEnvironment env;
    private BooleanLiteral trueNode;
    private BooleanLiteral falseNode;

    @BeforeEach
    void setUp() {
        env = new ArcaEnvironment();
        trueNode = new BooleanLiteral("true");
        falseNode = new BooleanLiteral("false");
    }

    @Test
    void shouldReturnTrueWhenExecutingTrueNode() {
        env = new ArcaEnvironment();
        assertTrue(trueNode.execute(env).getValue());
    }

    @Test
    void shouldReturnFalseWhenExecutingFalseNode() {
        assertFalse(falseNode.execute(env).getValue());
    }
}
