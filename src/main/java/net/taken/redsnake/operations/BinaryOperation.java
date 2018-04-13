package net.taken.redsnake.operations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.taken.redsnake.interpretor.Value;
import net.taken.redsnake.lang.RedsObject;
import net.taken.redsnake.reflect.Type;

import java.util.function.BiFunction;

@EqualsAndHashCode
public class BinaryOperation<U extends RedsObject, V extends RedsObject, R extends RedsObject> {

    @Getter
    private final Type<U> typeArg1;
    @Getter
    private final Type<V> typeArg2;
    @Getter
    private final Type<R> typeRes;
    private final BiFunction<U, V, R> function;

    public BinaryOperation(Type<U> typeArg1, Type<V> typeArg2, Type<R> typeRes, BiFunction<U, V, R> function) {
        this.typeArg1 = typeArg1;
        this.typeArg2 = typeArg2;
        this.typeRes = typeRes;
        this.function = function;
    }

    public Value<R> apply(Value<U> arg1, Value<V> arg2) {
        return Value.of(typeRes, function.apply(arg1.getValue(), arg2.getValue()));
    }

}
