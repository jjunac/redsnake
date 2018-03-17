package net.taken.arcanum.tree.expressions;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.tree.Program;
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
        Assignment assignment = createAssignment("vnZz3g5T", 250);
        assertEquals(new ArcaInteger(250), assignment.execute(env));
        assertEquals(new ArcaInteger(250), env.getVariable("vnZz3g5T"));
    }

    @Test
    void shouldUpdateValueWhenAssignExistingVariable() {
        env.putVariable("rtyur", new ArcaInteger(317));
        Assignment assignment = createAssignment("rtyur", 834);
        assignment.execute(env);
        assertEquals(new ArcaInteger(834), env.getVariable("rtyur"));
    }

    @Test
    void shouldStoreVariableWhenParseAssignment() {
        Program program = parseProgram("a = 771");
        program.execute(env);
        assertEquals(new ArcaInteger(771), env.getVariable("a"));
    }

    @Test
    void shouldPrioritizePlusAndMinusOverEquals() {
        Program program = parseProgram("a = 5+2-1");
        program.execute(env);
        assertEquals(new ArcaInteger(6), env.getVariable("a"));
    }

}
