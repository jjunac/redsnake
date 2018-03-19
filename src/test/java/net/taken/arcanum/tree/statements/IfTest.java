package net.taken.arcanum.tree.statements;

import com.google.common.collect.ImmutableList;
import net.taken.arcanum.lang.ArcaBoolean;
import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaNull;
import net.taken.arcanum.tree.Variable;
import net.taken.arcanum.tree.statements.expressions.Assignment;
import net.taken.arcanum.tree.statements.expressions.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static net.taken.arcanum.tree.TestUtils.mockExpression;
import static net.taken.arcanum.tree.TestUtils.mockVariable;
import static org.junit.jupiter.api.Assertions.*;

class IfTest {

    private ArcaEnvironment env;

    @BeforeEach
    void setUp() {
        env = new ArcaEnvironment();
    }

    private static If createIfWithAssignments(String variableName, boolean condition, int trueValue, int falseValue) {
        Variable var = mockVariable(variableName);
        Expression cond = mockExpression(new ArcaBoolean(condition));
        StatementList thenBody = new StatementList(ImmutableList.of(new Assignment(mockVariable(variableName), mockExpression(trueValue))));
        StatementList elseBody = new StatementList(ImmutableList.of(new Assignment(mockVariable(variableName), mockExpression(falseValue))));
        return new If(cond, thenBody, elseBody);
    }

    @Test
    void shouldExecuteTheGoodBlockWhenExecutingIfWithTrue() {
        If anIf = createIfWithAssignments("a", true, 1, 2);
        env.putVariable("a", new ArcaInteger(0));
        anIf.execute(env);
        assertEquals(new ArcaInteger(1), env.getVariable("a"));
    }
}
