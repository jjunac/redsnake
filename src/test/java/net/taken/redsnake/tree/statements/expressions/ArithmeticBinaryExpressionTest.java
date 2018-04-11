package net.taken.redsnake.tree.statements.expressions;

import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.tree.statements.Statement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.redsnake.tree.TestUtils.mockExpression;
import static net.taken.redsnake.tree.TestUtils.parseStatement;
import static net.taken.redsnake.tree.statements.expressions.ArithmeticBinaryExpression.Type.*;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticBinaryExpressionTest {

    private RedsEnvironment env;

    @BeforeEach
    void setUp() {
        env = new RedsEnvironment();
    }

    public static ArithmeticBinaryExpression createBinaryExpression(ArithmeticBinaryExpression.Type operator, int l, int r) {
        Expression left = mockExpression(l);
        Expression right = mockExpression(r);
        return new ArithmeticBinaryExpression(operator, left, right);
    }

    @Test
    void shouldReturnCorrectResultWhenExecuteBinaryExprPowerOnInteger() {
        ArithmeticBinaryExpression abe = createBinaryExpression(POWER, 2, 4);
        assertEquals(new RedsInteger(16), abe.execute(env).getValue());
    }

    @Test
    void shouldReturnCorrectResultWhenExecuteBinaryExprMultOnInteger() {
        ArithmeticBinaryExpression abe = createBinaryExpression(MULTIPLY, 4, 2);
        assertEquals(new RedsInteger(8), abe.execute(env).getValue());
    }

    @Test
    void shouldReturnCorrectResultWhenExecuteBinaryExprDivOnInteger() {
        ArithmeticBinaryExpression abe = createBinaryExpression(DIVIDE, 4, 2);
        assertEquals(new RedsInteger(2), abe.execute(env).getValue());
    }

    @Test
    void shouldReturnCorrectResultWhenExecuteBinaryExprModOnInteger() {
        ArithmeticBinaryExpression abe = createBinaryExpression(MODULUS, 4, 3);
        assertEquals(new RedsInteger(1), abe.execute(env).getValue());
    }

    @Test
    void shouldReturnCorrectResultWhenExecuteBinaryPlusOnInteger() {
        ArithmeticBinaryExpression abe = createBinaryExpression(ADD, 4, 3);
        assertEquals(new RedsInteger(7), abe.execute(env).getValue());
    }

    @Test
    void shouldReturnCorrectResultWhenExecuteBinaryMinusOnInteger() {
        ArithmeticBinaryExpression abe = createBinaryExpression(SUBTRACT, 4, 3);
        assertEquals(new RedsInteger(1), abe.execute(env).getValue());
    }

    @Test
    void shouldBeRightAssociativeWhenExecuteBinaryExprPow() {
        Statement stmt = parseStatement("2**3**2");
        assertEquals(new RedsInteger(512), stmt.execute(env).getValue());
    }

    @Test
    void shouldPrioritizeMultiplyDivideAndModuloOverPlusAndMinus() {
        Statement stmt = parseStatement("2+2*3-4/2+5%3");
        assertEquals(new RedsInteger(8), stmt.execute(env).getValue());
    }

    @Test
    void shouldPrioritizeUnaryMinusOverMultiplyDivideAndModulo() {
        Statement stmt = parseStatement("2*-1/-1*(1%-(-2))");
        assertEquals(new RedsInteger(2), stmt.execute(env).getValue());
    }

    @Test
    void shouldPrioritizePowerOverUnaryMinus() {
        Statement stmt = parseStatement("-(2)**2");
        assertEquals(new RedsInteger(-4), stmt.execute(env).getValue());
    }

}
