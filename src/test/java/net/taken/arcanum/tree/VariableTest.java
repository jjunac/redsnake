package net.taken.arcanum.tree;

import net.taken.arcanum.lang.ArcaEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

    private ArcaEnvironment env;

    @BeforeEach
    void setUp() {
        env = new ArcaEnvironment();
    }

    @Test
    void shouldReturnIDValueWhenExecuteVariable() {
        Variable var = new Variable("bulimiac");
        assertEquals("bulimiac", var.execute(env));
    }
}
