package net.taken.redsnake.operations;

import net.taken.redsnake.lang.RedsObject;

import java.util.Objects;
import java.util.function.Function;


public class UnaryOperation<T extends RedsObject, R extends RedsObject> {

    private Function<T, R> operation;

    public UnaryOperation(Function<T, R> operation) {
        this.operation = operation;
    }

    public R apply(T arg) {
        return operation.apply(arg);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnaryOperation<?, ?> that = (UnaryOperation<?, ?>) o;
        return Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation);
    }
}
