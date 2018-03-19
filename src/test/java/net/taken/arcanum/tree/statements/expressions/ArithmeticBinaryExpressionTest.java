package net.taken.arcanum.tree.statements.expressions;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.tree.statements.Statement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.tree.TestUtils.mockExpression;
import static net.taken.arcanum.tree.TestUtils.parseStatement;
import static net.taken.arcanum.tree.statements.expressions.ArithmeticBinaryExpression.Type.*;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticBinaryExpressionTest {

    private ArcaEnvironment env;

    @BeforeEach
    void setUp() {
        env = new ArcaEnvironment();
    }

    public static ArithmeticBinaryExpression createBinaryExpression(ArithmeticBinaryExpression.Type operator, int l, int r) {
        Expression left = mockExpression(l);
        Expression right = mockExpression(r);
        return new ArithmeticBinaryExpression(operator, left, right);
    }

    @Test
    void shouldReturnCorrectResultWhenExecuteBinaryExprPower() {
        ArithmeticBinaryExpression abe = createBinaryExpression(POWER, 2, 4);
        assertEquals(new ArcaInteger(16), abe.execute(env));
    }

    @Test
    void shouldReturnCorrectResultWhenExecuteBinaryExprMult() {
        ArithmeticBinaryExpression abe = createBinaryExpression(MULTIPLY, 4, 2);
        assertEquals(new ArcaInteger(8), abe.execute(env));
    }

    @Test
    void shouldReturnCorrectResultWhenExecuteBinaryExprDiv() {
        ArithmeticBinaryExpression abe = createBinaryExpression(DIVIDE, 4, 2);
        assertEquals(new ArcaInteger(2), abe.execute(env));
    }

    @Test
    void shouldReturnCorrectResultWhenExecuteBinaryExprMod() {
        ArithmeticBinaryExpression abe = createBinaryExpression(MODULUS, 4, 3);
        assertEquals(new ArcaInteger(1), abe.execute(env));
    }

    @Test
    void shouldReturnCorrectResultWhenExecuteBinaryPlus() {
        ArithmeticBinaryExpression abe = createBinaryExpression(ADD, 4, 3);
        assertEquals(new ArcaInteger(7), abe.execute(env));
    }

    @Test
    void shouldReturnCorrectResultWhenExecuteBinaryMinus() {
        ArithmeticBinaryExpression abe = createBinaryExpression(SUBTRACT, 4, 3);
        assertEquals(new ArcaInteger(1), abe.execute(env));
    }

    @Test
    void shouldBeRightAssociativeWhenExecuteBinaryExprPow() {
        Statement stmt = parseStatement("2**3**2");
        assertEquals(new ArcaInteger(512), stmt.execute(env));
    }

    @Test
    void shouldPrioritizeMultiplyDivideAndModuloOverPlusAndMinus() {
        Statement stmt = parseStatement("2+2*3-4/2+5%3");
        assertEquals(new ArcaInteger(8), stmt.execute(env));
    }

    @Test
    void shouldPrioritizeUnaryMinusOverMultiplyDivideAndModulo() {
        Statement stmt = parseStatement("2*-1/-1*(1%-(-2))");
        assertEquals(new ArcaInteger(2), stmt.execute(env));
    }

    @Test
    void shouldPrioritizePowerOverUnaryMinus() {
        Statement stmt = parseStatement("-(2)**2");
        assertEquals(new ArcaInteger(-4), stmt.execute(env));
    }

}
