package net.taken.redsnake.tree.expressions;

import net.taken.redsnake.lang.RedsEnvironment;
import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.tree.Statement;
import net.taken.redsnake.tree.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.redsnake.tree.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

class AssignmentTest {

    private RedsEnvironment env;

    @BeforeEach
    void setUp() {
        env = new RedsEnvironment();
    }

    public static Assignment createAssignment(String name, int value) {
        Variable var = mockVariable(name);
        Expression expr = mockExpression(value);
        return new Assignment(var, expr);
    }

    @Test
    void shouldStoreVariableAndReturnValueWhenAssignNewVariable() {
        Assignment assignment = createAssignment("ctrl", 250);
        assertEquals(new RedsInteger(250), assignment.execute(env));
        assertEquals(new RedsInteger(250), env.getVariable("ctrl"));
    }

    @Test
    void shouldUpdateValueWhenAssignExistingVariable() {
        env.putVariable("durra", new RedsInteger(317));
        Assignment assignment = createAssignment("durra", 834);
        assignment.execute(env);
        assertEquals(new RedsInteger(834), env.getVariable("durra"));
    }

    @Test
    void shouldStoreVariableWhenParseAssignment() {
        Statement stmt = parseStatement("a = 771");
        stmt.execute(env);
        assertEquals(new RedsInteger(771), env.getVariable("a"));
    }

    @Test
    void shouldPrioritizePlusAndMinusOverEquals() {
        Statement stmt = parseStatement("a = 5+2-1");
        stmt.execute(env);
        assertEquals(new RedsInteger(6), env.getVariable("a"));
    }

}
