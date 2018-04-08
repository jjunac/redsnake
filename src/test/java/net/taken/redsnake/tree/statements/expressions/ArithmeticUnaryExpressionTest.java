package net.taken.redsnake.tree.statements.expressions;

import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.lang.RedsInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.redsnake.tree.TestUtils.mockExpression;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticUnaryExpressionTest {

    private RedsEnvironment env;

    @BeforeEach
    void setUp() {
        env = new RedsEnvironment();
    }

    public static ArithmeticUnaryExpression createUnaryExpression(ArithmeticUnaryExpression.Type operator, int e) {
        Expression expr = mockExpression(e);
        return new ArithmeticUnaryExpression(operator, expr);
    }

    @Test
    void shouldReturnCorrectResultWhenExecuteUnaryExprUnaryMinus() {
        ArithmeticUnaryExpression aue = createUnaryExpression(ArithmeticUnaryExpression.Type.MINUS, 2);
        assertEquals(new RedsInteger(-2), aue.execute(env));
    }

}
