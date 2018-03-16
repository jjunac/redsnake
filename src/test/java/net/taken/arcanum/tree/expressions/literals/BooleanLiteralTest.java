package net.taken.arcanum.tree.expressions.literals;

import net.taken.arcanum.lang.ArcaEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BooleanLiteralTest {

    private BooleanLiteral trueNode;
    private BooleanLiteral falseNode;

    @BeforeEach
    void setUp() {
        trueNode = new BooleanLiteral("true");
        falseNode = new BooleanLiteral("false");
    }

    @Test
    void shouldReturnTrueWhenExecutingTrueNode() {
        assertTrue(trueNode.execute(new ArcaEnvironment()).getValue());
    }
}
