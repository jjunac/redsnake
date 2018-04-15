package net.taken.redsnake.tree;

import net.taken.redsnake.interpretor.RedsEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VariableTest {

    private RedsEnvironment env;

    @BeforeEach
    void setUp() {
        env = new RedsEnvironment();
    }

    @Test
    void shouldReturnIDValueWhenExecuteVariable() {
        Variable var = new Variable("bulimiac");
        assertEquals("bulimiac", var.execute(env));
    }
}
