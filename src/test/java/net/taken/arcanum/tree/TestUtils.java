package net.taken.arcanum.tree;

import com.sun.istack.internal.NotNull;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaList;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.parser.ArcanumLexer;
import net.taken.arcanum.parser.ArcanumParser;
import net.taken.arcanum.parser.visitors.ASTBuilder;
import net.taken.arcanum.tree.expressions.ArithmeticBinaryExpression;
import net.taken.arcanum.tree.expressions.ArithmeticUnaryExpression;
import net.taken.arcanum.tree.expressions.Expression;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Arrays;
import java.util.function.Function;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestUtils {

    private TestUtils() {
    }

    public static <U extends ParserRuleContext> U mockContext(Class<U> type, String value) {
        U ctx = mock(type);
        doReturn(value).when(ctx).getText();
        return ctx;
    }

    public static <T extends RuleContext, V> T mockNode(Class<T> type, V visitResult) {
        T mock = mock(type);
        doReturn(visitResult).when(mock).accept(any());
        return mock;
    }

    public static Token mockToken(int token) {
        Token mock = mock(Token.class);
        when(mock.getType()).thenReturn(token);
        return mock;
    }

    public static ArithmeticBinaryExpression mockBinaryExpr(ArithmeticBinaryExpression.Type operator, int l, int r) {
        Expression left = mock(Expression.class);
        when(left.execute(any())).thenReturn(new ArcaInteger(l));
        Expression right = mock(Expression.class);
        when(right.execute(any())).thenReturn(new ArcaInteger(r));
        return new ArithmeticBinaryExpression(operator, left, right);
    }

    public static ArithmeticUnaryExpression mockUnaryExpr(ArithmeticUnaryExpression.Type operator, int e) {
        Expression expr = mock(Expression.class);
        when(expr.execute(any())).thenReturn(new ArcaInteger(e));
        return new ArithmeticUnaryExpression(operator, expr);
    }

    public static Function<ArcaList, ArcaObject> mockArcaFunction(ArcaObject value) {
        Function<ArcaList, ArcaObject> fct = (Function<ArcaList, ArcaObject>) mock(Function.class);
        when(fct.apply(any(ArcaList.class))).thenReturn(value);
        return fct;
    }

    public static Program parseProgram(@NotNull String... s) {
        StringBuilder str = new StringBuilder();
        Arrays.stream(s).forEach(st -> str.append(st).append(System.lineSeparator()));
        CharStream inputStream = CharStreams.fromString(str.toString());
        ArcanumLexer arcanumLexer = new ArcanumLexer(inputStream);
        TokenStream commonTokenStream = new CommonTokenStream(arcanumLexer);
        ArcanumParser parser = new ArcanumParser(commonTokenStream);
        ParseTree t = parser.program();
        return (Program) new ASTBuilder().visit(t);
    }


}
