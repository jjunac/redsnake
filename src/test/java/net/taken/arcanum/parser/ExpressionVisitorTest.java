package net.taken.arcanum.parser;

import net.taken.arcanum.domain.ArcaInteger;
import net.taken.arcanum.domain.ArcaString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.parser.ArcanumParser.*;
import static net.taken.arcanum.parser.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionVisitorTest {

    private ExpressionVisitor visitor;

    @BeforeEach
    void setUp() {
        visitor = new ExpressionVisitor();
    }

    @Test
    void shouldReturnIntValueWhenVisitInt() {
        ArcanumParser.IntContext intContext = mockContext(ArcanumParser.IntContext.class, "42");
        assertEquals(new ArcaInteger(42), visitor.visitInt(intContext));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryExprPower() {
        ArcanumParser.BinaryExprContext ctx = mockBinaryExpr(2, 4, POW);
        assertEquals(new ArcaInteger(16), visitor.visitBinaryExpr(ctx));
    }

    @Test
    void shouldBeRightAssociativeWhenVisitBinaryExprPow() {
        ArcanumParser parser = initParser("2**3**2");
        ArcaInteger actual = (ArcaInteger) visitor.visit(parser.expr());
        assertEquals(new ArcaInteger(512), actual);
    }

    @Test
    void shouldReturnCorrectResultWhenVisitUnaryExprUnaryMinus() {
        ArcanumParser.UnaryExprContext ctx = mockUnaryExpr(2, MINUS);
        assertEquals(new ArcaInteger(-2), visitor.visitUnaryExpr(ctx));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryExprMult() {
        ArcanumParser.BinaryExprContext ctx = mockBinaryExpr(4, 2, MULT);
        assertEquals(new ArcaInteger(8), visitor.visitBinaryExpr(ctx));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryExprDiv() {
        ArcanumParser.BinaryExprContext ctx = mockBinaryExpr(4, 2, DIV);
        assertEquals(new ArcaInteger(2), visitor.visitBinaryExpr(ctx));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryExprMod() {
        BinaryExprContext ctx = mockBinaryExpr(4, 3, MOD);
        assertEquals(new ArcaInteger(1), visitor.visitBinaryExpr(ctx));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryPlus() {
        BinaryExprContext ctx = mockBinaryExpr(4, 3, PLUS);
        assertEquals(new ArcaInteger(7), visitor.visitBinaryExpr(ctx));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryMinus() {
        BinaryExprContext ctx = mockBinaryExpr(4, 3, MINUS);
        assertEquals(new ArcaInteger(1), visitor.visitBinaryExpr(ctx));
    }


    @Test
    void shouldStoreVariableWhenVisitAssignment() {
        ArcanumParser parser = initParser("a = 771");
        visitor.visit(parser.expr());
        assertEquals(new ArcaInteger(771), visitor.variables.get(new ArcaString("a")));
    }

    @Test
    void shouldPrioritizePlusAndMinusOverEquals() {
        ArcanumParser parser = initParser("a = 5+2-1");
        visitor.visit(parser.expr());
        assertEquals(new ArcaInteger(6), visitor.variables.get(new ArcaString("a")));
    }

    @Test
    void shouldPrioritizeMultiplyDivideAndModuloOverPlusAndMinus() {
        ArcanumParser parser = initParser("2+2*3-4/2+5%3");
        ArcaInteger actual = (ArcaInteger) visitor.visit(parser.expr());
        assertEquals(new ArcaInteger(8), actual);
    }

    @Test
    void shouldPrioritizeUnaryMinusOverMultiplyDivideAndModulo() {
        ArcanumParser parser = initParser("2*-1/-1*(1%-(-2))");
        ArcaInteger actual = (ArcaInteger) visitor.visit(parser.expr());
        assertEquals(new ArcaInteger(2), actual);
    }

    @Test
    void shouldPrioritizePowerOverUnaryMinus() {
        ArcanumParser parser = initParser("-(2)**2");
        ArcaInteger actual = (ArcaInteger) visitor.visit(parser.expr());
        assertEquals(new ArcaInteger(-4), actual);
    }


}