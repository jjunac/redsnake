package net.taken.arcanum.parser;

import com.sun.istack.internal.NotNull;
import net.taken.arcanum.domain.ArcaInteger;
import net.taken.arcanum.domain.ArcaList;
import net.taken.arcanum.domain.ArcaObject;
import net.taken.arcanum.domain.ArcaString;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static net.taken.arcanum.parser.ArcanumParser.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static net.taken.arcanum.parser.TestUtils.*;



class ArcanumVisitorTest {

    private ArcanumVisitor visitor;

    @BeforeEach
    void setUp() {
        visitor = new ArcanumVisitor();
    }

    @Test
    void shouldReturnVariableValueWhenVisitVarDesignator() {
        VarDesignatorContext ctx = mock(VarDesignatorContext.class);
        VarContext testvar = mockContext(VarContext.class, "testvar");
        when(ctx.var()).thenReturn(testvar);
        visitor.variables.put(new ArcaString("testvar"), new ArcaInteger(223));
        assertEquals(new ArcaInteger(223), visitor.visitVarDesignator(ctx));
    }

    @Test
    void shouldCallFunctionWhenVisitVarDesignatorAndVarDoesntExist() {
        VarDesignatorContext ctx = mock(VarDesignatorContext.class);
        VarContext testvar = mockContext(VarContext.class, "testFunction");
        when(ctx.var()).thenReturn(testvar);
        visitor.functions.put(new ArcaString("testFunction"), mockArcaFunction(new ArcaInteger(22)));
        assertEquals(new ArcaInteger(22), visitor.visitVarDesignator(ctx));
    }

    @Test
    void shouldCallFunctionWhenVisitCallWithParams() {
        Function<ArcaList, ArcaObject> fct = mockArcaFunction(new ArcaInteger(42));
        visitor.functions.put(new ArcaString("unitTestFunction"), fct);
        CallWithParamsContext ctx = mock(CallWithParamsContext.class);
        ctx.fct = mockContext(VarContext.class, "unitTestFunction");
        ctx.args = mockNode(ParamsContext.class, new ArcaList());
        assertEquals(new ArcaInteger(42), visitor.visitCallWithParams(ctx));
    }

    @Test
    void shouldCallFunctionWhenVisitCallWithoutParams() {
        Function<ArcaList, ArcaObject> fct = mockArcaFunction(new ArcaInteger(107));
        visitor.functions.put(new ArcaString("unitTestFunction"), fct);
        CallWithoutParamsContext ctx = mock(CallWithoutParamsContext.class);
        ctx.fct = mockContext(VarContext.class, "unitTestFunction");
        assertEquals(new ArcaInteger(107), visitor.visitCallWithoutParams(ctx));
    }

    @Test
    void shouldCallFunctionWhenVisitVarDesignatorButVarDoesntExist() {

        ArcanumParser parser = initParser("-(2)**2");
        ArcaInteger actual = (ArcaInteger) visitor.visit(parser.expr());
        assertEquals(new ArcaInteger(-4), actual);
    }

    @Test
    void shouldReturnListWhenVisitParams() {
        ParamsContext ctx = mock(ParamsContext.class);
        List<ExprContext> expr = new LinkedList<>();
        expr.add(mockNode(ExprContext.class, new ArcaInteger(24)));
        expr.add(mockNode(ExprContext.class, new ArcaString("yHBpQI")));
        expr.add(mockNode(ExprContext.class, new ArcaList(new ArcaInteger(881))));
        when(ctx.expr()).thenReturn(expr);
        assertEquals(new ArcaList(new ArcaInteger(24), new ArcaString("yHBpQI"), new ArcaList(new ArcaInteger(881))),
                visitor.visitParams(ctx));
    }

    @Test
    void shouldReturnIDValueWhenVisitVar() {
        VarContext varContext = mockContext(VarContext.class, "testvar");
        assertEquals(new ArcaString("testvar"), visitor.visitVar(varContext));
    }

    @Test
    void shouldCallRightVisitorWhenCallVisit() {
        ExpressionVisitor expressionVisitor = mock(ExpressionVisitor.class);
        when(expressionVisitor.visitBinaryExpr(any(BinaryExprContext.class))).thenReturn(new ArcaInteger(481));
        visitor.registerVisitor(expressionVisitor, BinaryExprContext.class);
        BinaryExprContext ctx = new BinaryExprContext(mock(ExprContext.class));
        assertEquals(new ArcaInteger(481), visitor.visit(ctx));
    }
}