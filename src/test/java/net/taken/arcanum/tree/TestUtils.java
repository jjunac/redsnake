package net.taken.arcanum.tree;

import com.sun.istack.internal.NotNull;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaList;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.parser.ArcanumLexer;
import net.taken.arcanum.parser.ArcanumParser;
import net.taken.arcanum.parser.visitors.ASTBuilder;
import net.taken.arcanum.tree.designators.VariableDesignator;
import net.taken.arcanum.tree.expressions.ArithmeticBinaryExpression;
import net.taken.arcanum.tree.expressions.ArithmeticUnaryExpression;
import net.taken.arcanum.tree.expressions.Assignment;
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

    public static Expression mockExpression(int value) {
        Expression left = mock(Expression.class);
        when(left.execute(any())).thenReturn(new ArcaInteger(value));
        return left;
    }

    public static Variable mockVariable(String name) {
        Variable var = mock(Variable.class);
        when(var.execute(any())).thenReturn(name);
        return var;
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
