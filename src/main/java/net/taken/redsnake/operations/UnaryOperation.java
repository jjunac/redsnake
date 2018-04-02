package net.taken.redsnake.operations;

import net.taken.redsnake.lang.RedsObject;

import java.util.function.BiFunction;
import java.util.function.Function;

public class UnaryOperation<T extends RedsObject, R extends RedsObject> {

    private final Function<T, R> function;

    public UnaryOperation(Function<T, R> function) {
        this.function = function;
    }

    public R execute(T arg) {
        return function.apply(arg);
    }

}
