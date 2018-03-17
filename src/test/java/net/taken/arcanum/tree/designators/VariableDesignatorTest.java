package net.taken.arcanum.tree.designators;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaString;
import net.taken.arcanum.parser.ArcanumParser;
import net.taken.arcanum.tree.TestUtils;
import net.taken.arcanum.tree.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.tree.TestUtils.mockArcaFunction;
import static net.taken.arcanum.tree.TestUtils.mockVariable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    void shouldReturnVariableValueWhenExecuteVar() {
        VariableDesignator var = createVariableDesignator("testvar");
        env.putVariable("testvar", new ArcaInteger(223));
        assertEquals(new ArcaInteger(223), var.execute(env));
    }

    @Test
    void shouldCallFunctionWhenVisitVarDesignatorAndVarDoesntExist() {
        VariableDesignator var = createVariableDesignator("testFunction");
        env.putFunction("testFunction", list -> new ArcaInteger(22));
        assertEquals(new ArcaInteger(22), var.execute(env));
    }

}
