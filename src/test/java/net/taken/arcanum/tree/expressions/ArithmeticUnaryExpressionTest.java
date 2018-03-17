package net.taken.arcanum.tree.expressions;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.tree.TestUtils.mockExpression;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticUnaryExpressionTest {

    private ArcaEnvironment env;

    @BeforeEach
    void setUp() {
        env = new ArcaEnvironment();
    }

    public static ArithmeticUnaryExpression createUnaryExpression(ArithmeticUnaryExpression.Type operator, int e) {
        Expression expr = mockExpression(e);
        return new ArithmeticUnaryExpression(operator, expr);
    }

    @Test
    void shouldReturnCorrectResultWhenVisitUnaryExprUnaryMinus() {
        ArithmeticUnaryExpression aue = createUnaryExpression(ArithmeticUnaryExpression.Type.MINUS, 2);
        assertEquals(new ArcaInteger(-2), aue.execute(env));
    }

}
