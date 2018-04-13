package net.taken.redsnake.operations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.reflect.Type;

import java.util.function.Function;

@EqualsAndHashCode
public class UnaryOperation<U extends RedsObject, R extends RedsObject> {

    @Getter
    private final Type<U> typeArg;
    @Getter
    private final Type<R> typeRes;
    private final Function<U, R> operation;

    public UnaryOperation(Type<U> typeArg, Type<R> typeRes, Function<U, R> operation) {
        this.typeArg = typeArg;
        this.typeRes = typeRes;
        this.operation = operation;
    }

    public Value<R> apply(Value<U> arg) {
        return Value.of(typeRes, operation.apply(arg.getValue()));
    }

}
