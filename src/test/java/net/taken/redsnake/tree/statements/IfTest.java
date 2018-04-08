package net.taken.redsnake.tree.statements;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.lang.RedsBoolean;
import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.tree.Program;
import net.taken.redsnake.tree.Variable;
import net.taken.redsnake.tree.statements.expressions.Assignment;
import net.taken.redsnake.tree.statements.expressions.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static net.taken.redsnake.tree.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class IfTest {

    private RedsEnvironment env;

    @BeforeEach
    void setUp() {
        env = new RedsEnvironment();
    }

    private static If createIfWithAssignments(String variableName, boolean condition, int trueValue) {
        Variable var = mockVariable(variableName);
        Expression cond = mockExpression(new RedsBoolean(condition));
        StatementList thenBody = new StatementList(ImmutableList.of(new Assignment(mockVariable(variableName), mockExpression(trueValue))));
        return new If(cond, thenBody, Optional.empty());
    }

    private static If createIfWithAssignments(String variableName, boolean condition, int trueValue, int falseValue) {
        Variable var = mockVariable(variableName);
        Expression cond = mockExpression(new RedsBoolean(condition));
        StatementList thenBody = new StatementList(ImmutableList.of(new Assignment(mockVariable(variableName), mockExpression(trueValue))));
        StatementList elseBody = new StatementList(ImmutableList.of(new Assignment(mockVariable(variableName), mockExpression(falseValue))));
        return new If(cond, thenBody, elseBody);
    }

    @Test
    void shouldExecuteTheGoodBlockWhenExecutingIfWithTrueAndElseClause() {
        If anIf = createIfWithAssignments("a", true, 1, 2);
        env.putVariable("a", new RedsInteger(0));
        anIf.execute(env);
        assertEquals(new RedsInteger(1), env.getVariable("a"));
    }

    @Test
    void shouldNotExecuteThenBlockWhenExecutingIfWithFalseAndNoElseClause() {
        If anIf = createIfWithAssignments("a", false, 1);
        env.putVariable("a", new RedsInteger(0));
        anIf.execute(env);
        assertEquals(new RedsInteger(0), env.getVariable("a"));
    }

    @Test
    void shouldNotExecuteElseBlockWhenExecutingIfWithFalseAndElseClause() {
        If anIf = createIfWithAssignments("a", false, 1, 2);
        env.putVariable("a", new RedsInteger(0));
        anIf.execute(env);
        assertEquals(new RedsInteger(2), env.getVariable("a"));
    }

    @Test
    void shouldExecuteThenBlockWhenExecutingIfWithParenthesisAndCurlyBracketsAndNotInline() {
        Program program = parseProgram("a = 0\n" +
            "if(true) {\n" +
            "    a = 1\n" +
            "} else {\n" +
            "    a = 2\n" +
            "}");
        program.execute(env);
        assertEquals(new RedsInteger(1), env.getVariable("a"));
    }

    @Test
    void shouldExecuteElseBlockWhenExecutingIfWithoutParenthesisAndWithoutCurlyBracketsAndInline() {
        Program program = parseProgram("a = 0\n" +
            "if false a = 1 else a = 2");
        program.execute(env);
        assertEquals(new RedsInteger(2), env.getVariable("a"));
    }

}
