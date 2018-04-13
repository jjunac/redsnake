package net.taken.redsnake.tree;

import net.taken.redsnake.interpretor.RedsEnvironment;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsInteger;
import net.taken.redsnake.tree.statements.expressions.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.taken.redsnake.tree.TestUtils.mockVariable;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CallTest {

    private RedsEnvironment env;

    @BeforeEach
    void setUp() {
        env = new RedsEnvironment();
    }

    private static Call createCall(String name, int... parameters) {
        Variable var = mockVariable(name);
        List<Expression> params = Arrays.stream(parameters).mapToObj(TestUtils::mockExpression).collect(Collectors.toList());
        return new Call(var, params);
    }

    @Test
    void shouldReturnCallValueWhenExecuteCallWithoutParams() {
        Call call = createCall("carded");
        env.putFunction("carded", (x) -> Value.ofInteger(500));
        assertEquals(new RedsInteger(500), call.execute(env).getValue());
    }

    @Test
    void shouldReturnCallValueWhenExecuteCallWithParams() {
        Call call = createCall("zymogene", 383);
        env.putFunction("zymogene", (x) -> Value.of(new RedsInteger(576).plus(x.get(0).getValue())));
        assertEquals(new RedsInteger(576 + 383), call.execute(env).getValue());
    }
}
