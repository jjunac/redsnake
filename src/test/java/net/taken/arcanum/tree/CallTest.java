package net.taken.arcanum.tree;

import net.taken.arcanum.lang.ArcaEnvironment;
import net.taken.arcanum.lang.ArcaInteger;
import net.taken.arcanum.tree.expressions.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.taken.arcanum.tree.TestUtils.mockExpression;
import static net.taken.arcanum.tree.TestUtils.mockVariable;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CallTest {

    private ArcaEnvironment env;

    @BeforeEach
    void setUp() {
        env = new ArcaEnvironment();
    }

    private static Call createCall(String name, int... parameters) {
        Variable var = mockVariable(name);
        List<Expression> params = Arrays.stream(parameters).mapToObj(TestUtils::mockExpression).collect(Collectors.toList());
        return new Call(var, params);
    }

    @Test
    void shouldReturnCallValueWhenExecuteCallWithoutParams() {
        Call call = createCall("carded");
        env.putFunction("carded", (x) -> new ArcaInteger(500));
        assertEquals(new ArcaInteger(500), call.execute(env));
    }

    @Test
    void shouldReturnCallValueWhenExecuteCallWithParams() {
        Call call = createCall("zymogene", 383);
        env.putFunction("zymogene", (x) -> new ArcaInteger(576).plus(x.get(0)));
        assertEquals(new ArcaInteger(576 + 383), call.execute(env));
    }
}
