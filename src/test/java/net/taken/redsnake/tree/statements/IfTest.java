package net.taken.redsnake.tree.statements;

import com.google.common.collect.ImmutableList;
import net.taken.redsnake.lang.RedsBoolean;
import net.taken.redsnake.lang.RedsEnvironment;
import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.tree.Variable;
import net.taken.redsnake.tree.statements.If;
import net.taken.redsnake.tree.statements.StatementList;
import net.taken.redsnake.tree.statements.expressions.Assignment;
import net.taken.redsnake.tree.statements.expressions.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.redsnake.tree.TestUtils.mockExpression;
import static net.taken.redsnake.tree.TestUtils.mockVariable;
import static org.junit.jupiter.api.Assertions.assertEquals;


class IfTest {

    private RedsEnvironment env;

    @BeforeEach
    void setUp() {
        env = new RedsEnvironment();
    }

    private static If createIfWithAssignments(String variableName, boolean condition, int trueValue, int falseValue) {
        Variable var = mockVariable(variableName);
        Expression cond = mockExpression(new RedsBoolean(condition));
        StatementList thenBody = new StatementList(ImmutableList.of(new Assignment(mockVariable(variableName), mockExpression(trueValue))));
        StatementList elseBody = new StatementList(ImmutableList.of(new Assignment(mockVariable(variableName), mockExpression(falseValue))));
        return new If(cond, thenBody, elseBody);
    }

    @Test
    void shouldExecuteTheGoodBlockWhenExecutingIfWithTrue() {
        If anIf = createIfWithAssignments("a", true, 1, 2);
        env.putVariable("a", new RedsInteger(0));
        anIf.execute(env);
        assertEquals(new RedsInteger(1), env.getVariable("a"));
    }
}
