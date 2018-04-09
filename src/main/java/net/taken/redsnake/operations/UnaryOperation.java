package net.taken.redsnake.operations;

import lombok.EqualsAndHashCode;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.reflect.Type;

import java.util.Objects;
import java.util.function.Function;

@EqualsAndHashCode
public class UnaryOperation<T1 extends Type<>, T2 extends RedsObject, U extends Value<T1>, R extends Value<T2>> {

    private final T1 typeArg;
    private final T2 typeRes;
    private final Function<U, R> operation;

    public UnaryOperation(T1 typeArg, T2 typeRes, Function<U, R> operation) {
        this.typeArg = typeArg;
        this.typeRes = typeRes;
        this.operation = operation;
    }

    public R apply(U arg) {
        return operation.apply(arg.getValue());
    }

}
