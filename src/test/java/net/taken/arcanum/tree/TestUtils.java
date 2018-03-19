package net.taken.arcanum.tree;

import com.sun.istack.internal.NotNull;
import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.parser.ArcanumLexer;
import net.taken.arcanum.parser.ArcanumParser;
import net.taken.arcanum.parser.visitors.ASTBuilder;
import net.taken.arcanum.tree.statements.Statement;
import net.taken.arcanum.tree.statements.expressions.Assignment;
import net.taken.arcanum.tree.statements.expressions.Expression;
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
        return mockExpression(new ArcaInteger(value));
    }

    public static Expression mockExpression(ArcaObject value) {
        Expression e = mock(Expression.class);
        when(e.execute(any())).thenReturn(value);
        return e;
    }

    public static Variable mockVariable(String name) {
        Variable var = mock(Variable.class);
        when(var.execute(any())).thenReturn(name);
        return var;
    }

    public static Call mockCall(ArcaObject value) {
        Call call = mock(Call.class);
        when(call.execute(any())).thenReturn(value);
        return call;
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

    public static Statement parseStatement(@NotNull String s) {
        CharStream inputStream = CharStreams.fromString(s);
        ArcanumLexer arcanumLexer = new ArcanumLexer(inputStream);
        TokenStream commonTokenStream = new CommonTokenStream(arcanumLexer);
        ArcanumParser parser = new ArcanumParser(commonTokenStream);
        ParseTree t = parser.expression();
        return (Expression) new ASTBuilder().visit(t);
    }

}
