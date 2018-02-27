package net.taken.arcanum.parser;

import com.sun.istack.internal.NotNull;
import net.taken.arcanum.domain.ArcaInteger;
import net.taken.arcanum.domain.ArcaList;
import net.taken.arcanum.domain.ArcaObject;
import net.taken.arcanum.domain.ArcaString;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static net.taken.arcanum.parser.ArcanumParser.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArcanumVisitorTest {

    private ArcanumVisitor visitor;

    @BeforeEach
    void setUp() {
        visitor = new ArcanumVisitor();
    }

    private <U extends ParserRuleContext> U mockContext(Class<U> type, String value) {
        U ctx = mock(type);
        doReturn(value).when(ctx).getText();
        return ctx;
    }

    private <T extends RuleContext, V> T mockNode(Class<T> type, V visitResult) {
        T mock = mock(type);
        doReturn(visitResult).when(mock).accept(visitor);
        return mock;
    }

    private Token mockToken(int token) {
        Token mock = mock(Token.class);
        when(mock.getType()).thenReturn(token);
        return mock;
    }

    private BinaryExprContext mockBinaryExpr(int l, int r, int operator) {
        BinaryExprContext ctx = mock(BinaryExprContext.class);
        ctx.l = mockNode(IntContext.class, new ArcaInteger(l));
        ctx.r = mockNode(IntContext.class, new ArcaInteger(r));
        ctx.op = mockToken(operator);
        return ctx;
    }

    private UnaryExprContext mockUnaryExpr(int e, int operator) {
        UnaryExprContext ctx = mock(UnaryExprContext.class);
        ctx.e = mockNode(IntContext.class, new ArcaInteger(e));
        ctx.op = mockToken(operator);
        return ctx;
    }

    private ArcanumParser initParser(@NotNull String s) {
        CharStream inputStream = CharStreams.fromString(s);
        ArcanumLexer arcanumLexer = new ArcanumLexer(inputStream);
        TokenStream commonTokenStream = new CommonTokenStream(arcanumLexer);
        return new ArcanumParser(commonTokenStream);
    }

    @Test
    void shouldReturnIntValueWhenVisitInt() {
        IntContext intContext = mockContext(IntContext.class, "42");
        assertEquals(new ArcaInteger(42), visitor.visitInt(intContext));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryExprPower() {
        BinaryExprContext ctx = mockBinaryExpr(2, 4, POW);
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
        UnaryExprContext ctx = mockUnaryExpr(2, MINUS);
        assertEquals(new ArcaInteger(-2), visitor.visitUnaryExpr(ctx));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryExprMult() {
        BinaryExprContext ctx = mockBinaryExpr(4, 2, MULT);
        assertEquals(new ArcaInteger(8), visitor.visitBinaryExpr(ctx));
    }

    @Test
    void shouldReturnCorrectResultWhenVisitBinaryExprDiv() {
        BinaryExprContext ctx = mockBinaryExpr(4, 2, DIV);
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

    @Test
    void shouldCallFunctionWhenCallKernelFunction() {
        Function<ArcaList, ArcaObject> fct = (Function<ArcaList, ArcaObject>) mock(Function.class);
        when(fct.apply(any(ArcaList.class))).thenReturn(new ArcaInteger(42));
        visitor.functions.put(new ArcaString("unitTestFunction"), fct);
        CallContext ctx = mock(CallContext.class);
        ctx.fct = mockContext(VarContext.class, "unitTestFunction");
        ctx.args = mockNode(ParamsContext.class, new ArcaList());
        assertEquals(new ArcaInteger(42), visitor.visitCall(ctx));
    }

    @Test
    void shouldReturnListWhenVisitParams() {
        ParamsContext ctx = mock(ParamsContext.class);
        when(ctx.expr()).thenReturn()
    }

    @Test
    void shouldReturnIDValueWhenVisitVar() {
        VarContext varContext = mockContext(VarContext.class, "testvar");
        assertEquals(new ArcaString("testvar"), visitor.visitVar(varContext));
    }
}