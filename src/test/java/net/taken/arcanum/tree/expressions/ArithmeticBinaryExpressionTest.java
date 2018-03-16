package net.taken.arcanum.tree.expressions;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.parser.ArcanumParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.tree.TestUtils.mockBinaryExpr;
import static net.taken.arcanum.tree.expressions.ArithmeticBinaryExpression.Type.*;
import static net.taken.arcanum.tree.TestUtils.mockUnaryExpr;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticBinaryExpressionTest {

    private ArcaEnvironment env;

    @BeforeEach
    void setUp() {
        env = new ArcaEnvironment();
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryExprPower() {
        ArithmeticBinaryExpression abe = mockBinaryExpr(POWER, 2, 4);
        assertEquals(new ArcaInteger(16), abe.execute(env));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryExprMult() {
        ArithmeticBinaryExpression abe = mockBinaryExpr(MULTIPLY, 4, 2);
        assertEquals(new ArcaInteger(8), abe.execute(env));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryExprDiv() {
        ArithmeticBinaryExpression abe = mockBinaryExpr(DIVIDE, 4, 2);
        assertEquals(new ArcaInteger(2), abe.execute(env));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryExprMod() {
        ArithmeticBinaryExpression abe = mockBinaryExpr(MODULUS, 4, 3);
        assertEquals(new ArcaInteger(1), abe.execute(env));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryPlus() {
        ArithmeticBinaryExpression abe = mockBinaryExpr(ADD, 4, 3);
        assertEquals(new ArcaInteger(7), abe.execute(env));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryMinus() {
        ArithmeticBinaryExpression abe = mockBinaryExpr(SUBTRACT, 4, 3);
        assertEquals(new ArcaInteger(1), abe.execute(env));
    }

}
