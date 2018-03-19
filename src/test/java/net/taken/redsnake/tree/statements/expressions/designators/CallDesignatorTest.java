package net.taken.redsnake.tree.statements.expressions.designators;

import net.taken.redsnake.lang.RedsEnvironment;
import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.tree.Call;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.redsnake.tree.TestUtils.mockCall;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CallDesignatorTest {

    private RedsEnvironment env;

    @BeforeEach
    void setUp() {
        env = new RedsEnvironment();
    }

    public static CallDesignator createCallDesignator(RedsObject value) {
        Call call = mockCall(value);
        return new CallDesignator(call);
    }

    @Test
    void shouldReturnCallValueWhenExecuteCallDesignator() {
        CallDesignator callDesignator = createCallDesignator(new RedsInteger(211));
        assertEquals(new RedsInteger(211), callDesignator.execute(env));
    }
}
