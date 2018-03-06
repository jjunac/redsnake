package net.taken.arcanum.parser.visitors;

import com.sun.istack.internal.NotNull;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaList;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.parser.ArcanumLexer;
import net.taken.arcanum.parser.ArcanumParser;
import org.antlr.v4.runtime.*;

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

    public static ArcanumParser.BinaryExprContext mockBinaryExpr(int l, int r, int operator) {
        ArcanumParser.BinaryExprContext ctx = mock(ArcanumParser.BinaryExprContext.class);
        ctx.l = mockNode(ArcanumParser.IntContext.class, new ArcaInteger(l));
        ctx.r = mockNode(ArcanumParser.IntContext.class, new ArcaInteger(r));
        ctx.op = mockToken(operator);
        return ctx;
    }

    public static ArcanumParser.UnaryExprContext mockUnaryExpr(int e, int operator) {
        ArcanumParser.UnaryExprContext ctx = mock(ArcanumParser.UnaryExprContext.class);
        ctx.e = mockNode(ArcanumParser.IntContext.class, new ArcaInteger(e));
        ctx.op = mockToken(operator);
        return ctx;
    }

    public static Function<ArcaList, ArcaObject> mockArcaFunction(ArcaObject value) {
        Function<ArcaList, ArcaObject> fct = (Function<ArcaList, ArcaObject>) mock(Function.class);
        when(fct.apply(any(ArcaList.class))).thenReturn(value);
        return fct;
    }

    public static ArcanumParser initParser(@NotNull String s) {
        return initParser(new String[]{s});
    }

    public static ArcanumParser initParser(@NotNull String... s) {
        StringBuilder str = new StringBuilder();
        Arrays.stream(s).forEach(st -> str.append(st).append(System.lineSeparator()));
        CharStream inputStream = CharStreams.fromString(str.toString());
        ArcanumLexer arcanumLexer = new ArcanumLexer(inputStream);
        TokenStream commonTokenStream = new CommonTokenStream(arcanumLexer);
        return new ArcanumParser(commonTokenStream);
    }


}