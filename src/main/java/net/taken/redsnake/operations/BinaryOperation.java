package net.taken.redsnake.operations;

import net.taken.redsnake.lang.RedsObject;

import java.util.function.BiFunction;

public class BinaryOperation<T extends RedsObject, U extends RedsObject, R extends RedsObject> {

    private final BiFunction<T, U, R> function;

    public BinaryOperation(BiFunction<T, U, R> function) {
        this.function = function;
    }

    public R execute(T arg1, U arg2) {
        return function.apply(arg1, arg2);
    }

}
