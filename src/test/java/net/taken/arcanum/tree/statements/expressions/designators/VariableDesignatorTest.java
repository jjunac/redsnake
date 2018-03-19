package net.taken.arcanum.tree.statements.expressions.designators;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.tree.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.tree.TestUtils.mockVariable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class VariableDesignatorTest {

    private ArcaEnvironment env;

    @BeforeEach
    void setUp() {
        env = new ArcaEnvironment();
    }

    public static VariableDesignator createVariableDesignator(String name) {
        Variable var = mockVariable(name);
        return new VariableDesignator(var);
    }

    @Test
    void shouldReturnVariableValueWhenExecuteVarDesignator() {
        VariableDesignator var = createVariableDesignator("testvar");
        env.putVariable("testvar", new ArcaInteger(223));
        assertEquals(new ArcaInteger(223), var.execute(env));
    }

    @Test
    void shouldReturnVariableValueWhenExecuteVarDesignatorAndFunctionExists() {
        VariableDesignator var = createVariableDesignator("testvar");
        env.putVariable("testvar", new ArcaInteger(96));
        env.putFunction("testvar", list -> new ArcaInteger(957));
        assertEquals(new ArcaInteger(96), var.execute(env));
    }

    @Test
    void shouldCallFunctionWhenExecuteVarDesignatorAndVarDoesntExist() {
        VariableDesignator var = createVariableDesignator("testFunction");
        env.putFunction("testFunction", list -> new ArcaInteger(22));
        assertEquals(new ArcaInteger(22), var.execute(env));
    }

}
