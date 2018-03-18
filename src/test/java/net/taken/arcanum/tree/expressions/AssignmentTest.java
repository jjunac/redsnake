package net.taken.arcanum.tree.expressions;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.tree.Statement;
import net.taken.arcanum.tree.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.tree.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

class AssignmentTest {

    private ArcaEnvironment env;

    @BeforeEach
    void setUp() {
        env = new ArcaEnvironment();
    }

    public static Assignment createAssignment(String name, int value) {
        Variable var = mockVariable(name);
        Expression expr = mockExpression(value);
        return new Assignment(var, expr);
    }

    @Test
    void shouldStoreVariableAndReturnValueWhenAssignNewVariable() {
        Assignment assignment = createAssignment("ctrl", 250);
        assertEquals(new ArcaInteger(250), assignment.execute(env));
        assertEquals(new ArcaInteger(250), env.getVariable("ctrl"));
    }

    @Test
    void shouldUpdateValueWhenAssignExistingVariable() {
        env.putVariable("durra", new ArcaInteger(317));
        Assignment assignment = createAssignment("durra", 834);
        assignment.execute(env);
        assertEquals(new ArcaInteger(834), env.getVariable("durra"));
    }

    @Test
    void shouldStoreVariableWhenParseAssignment() {
        Statement stmt = parseStatement("a = 771");
        stmt.execute(env);
        assertEquals(new ArcaInteger(771), env.getVariable("a"));
    }

    @Test
    void shouldPrioritizePlusAndMinusOverEquals() {
        Statement stmt = parseStatement("a = 5+2-1");
        stmt.execute(env);
        assertEquals(new ArcaInteger(6), env.getVariable("a"));
    }

}
