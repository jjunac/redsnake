package net.taken.redsnake.tree.statements.expressions.designators;

import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.tree.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.redsnake.tree.TestUtils.mockVariable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class VariableDesignatorTest {

    private RedsEnvironment env;

    @BeforeEach
    void setUp() {
        env = new RedsEnvironment();
    }

    public static VariableDesignator createVariableDesignator(String name) {
        Variable var = mockVariable(name);
        return new VariableDesignator(var);
    }

    @Test
    void shouldReturnVariableValueWhenExecuteVarDesignator() {
        VariableDesignator var = createVariableDesignator("testvar");
        env.putVariable("testvar", new RedsInteger(223));
        assertEquals(new RedsInteger(223), var.execute(env));
    }

    @Test
    void shouldReturnVariableValueWhenExecuteVarDesignatorAndFunctionExists() {
        VariableDesignator var = createVariableDesignator("testvar");
        env.putVariable("testvar", new RedsInteger(96));
        env.putFunction("testvar", list -> new RedsInteger(957));
        assertEquals(new RedsInteger(96), var.execute(env));
    }

    @Test
    void shouldCallFunctionWhenExecuteVarDesignatorAndVarDoesntExist() {
        VariableDesignator var = createVariableDesignator("testFunction");
        env.putFunction("testFunction", list -> new RedsInteger(22));
        assertEquals(new RedsInteger(22), var.execute(env));
    }

}
