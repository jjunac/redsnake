package net.taken.arcanum.tree.statements.expressions.designators;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.lang.ArcaObject;
import net.taken.arcanum.tree.Call;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.taken.arcanum.tree.TestUtils.mockCall;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CallDesignatorTest {

    private ArcaEnvironment env;

    @BeforeEach
    void setUp() {
        env = new ArcaEnvironment();
    }

    public static CallDesignator createCallDesignator(ArcaObject value) {
        Call call = mockCall(value);
        return new CallDesignator(call);
    }

    @Test
    void shouldReturnCallValueWhenExecuteCallDesignator() {
        CallDesignator callDesignator = createCallDesignator(new ArcaInteger(211));
        assertEquals(new ArcaInteger(211), callDesignator.execute(env));
    }
}
