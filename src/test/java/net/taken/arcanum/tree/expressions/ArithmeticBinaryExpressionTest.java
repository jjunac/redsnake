package net.taken.arcanum.tree.expressions;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.tree.TestUtils.mockExpression;
import static net.taken.arcanum.tree.TestUtils.parseProgram;
import static net.taken.arcanum.tree.expressions.ArithmeticBinaryExpression.Type.*;
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
        ArcaObject actual = parseProgram("2**3**2").execute(env);
        assertEquals(new ArcaInteger(512), actual);
    }

    @Test
    void shouldPrioritizeMultiplyDivideAndModuloOverPlusAndMinus() {
        Program program = parseProgram("2+2*3-4/2+5%3");
        assertEquals(new ArcaInteger(8), program.execute(env));
    }

    @Test
    void shouldPrioritizeUnaryMinusOverMultiplyDivideAndModulo() {
        Program program = parseProgram("2*-1/-1*(1%-(-2))");
        assertEquals(new ArcaInteger(2), program.execute(env));
    }

    @Test
    void shouldPrioritizePowerOverUnaryMinus() {
        Program program = parseProgram("-(2)**2");
        assertEquals(new ArcaInteger(-4), program.execute(env));
    }

}
