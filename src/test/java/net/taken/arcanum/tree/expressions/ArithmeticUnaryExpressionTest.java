package net.taken.arcanum.tree.expressions;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.parser.ArcanumParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.tree.TestUtils.mockUnaryExpr;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticUnaryExpressionTest {

    private ArcaEnvironment env;

    @BeforeEach
    void setUp() {
        env = new ArcaEnvironment();
    }

    @Test
    void shouldReturnCorrectResultWhenVisitUnaryExprUnaryMinus() {
        ArithmeticUnaryExpression aue = mockUnaryExpr(ArithmeticUnaryExpression.Type.MINUS, 2);
        assertEquals(new ArcaInteger(-2), aue.execute(env));
    }

}
