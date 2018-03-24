package net.taken.redsnake.tree;

import com.sun.istack.internal.NotNull;
import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.parser.RedsnakeLexer;
import net.taken.redsnake.parser.RedsnakeParser;
import net.taken.redsnake.parser.visitors.ASTBuilder;
import net.taken.redsnake.tree.statements.Statement;
import net.taken.redsnake.tree.statements.expressions.Expression;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestUtils {

    private TestUtils() {
    }

    public static Expression mockExpression(int value) {
        return mockExpression(new RedsInteger(value));
    }

    public static Expression mockExpression(RedsObject value) {
        Expression e = mock(Expression.class);
        when(e.execute(any())).thenReturn(value);
        return e;
    }

    public static Variable mockVariable(String name) {
        Variable var = mock(Variable.class);
        when(var.execute(any())).thenReturn(name);
        return var;
    }

    public static Call mockCall(RedsObject value) {
        Call call = mock(Call.class);
        when(call.execute(any())).thenReturn(value);
        return call;
    }

    public static Program parseProgram(@NotNull String... s) {
        StringBuilder str = new StringBuilder();
        Arrays.stream(s).forEach(st -> str.append(st).append(System.lineSeparator()));
        CharStream inputStream = CharStreams.fromString(str.toString());
        RedsnakeLexer redsnakeLexer = new RedsnakeLexer(inputStream);
        TokenStream commonTokenStream = new CommonTokenStream(redsnakeLexer);
        RedsnakeParser parser = new RedsnakeParser(commonTokenStream);
        ParseTree t = parser.program();
        return (Program) new ASTBuilder().visit(t);
    }

    public static Statement parseStatement(@NotNull String s) {
        CharStream inputStream = CharStreams.fromString(s);
        RedsnakeLexer redsnakeLexer = new RedsnakeLexer(inputStream);
        TokenStream commonTokenStream = new CommonTokenStream(redsnakeLexer);
        RedsnakeParser parser = new RedsnakeParser(commonTokenStream);
        ParseTree t = parser.expression();
        return (Expression) new ASTBuilder().visit(t);
    }

}
